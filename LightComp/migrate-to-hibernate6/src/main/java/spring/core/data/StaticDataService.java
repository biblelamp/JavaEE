package spring.core.data;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import spring.common.db.HibernateUtils;
import spring.repository.BookRepository;

import javax.persistence.EntityManager;
import javax.transaction.Synchronization;
import javax.transaction.Transactional;

/**
 * Service for static data
 *
 * Service returns StaticDataProvider with valid data for given transaction
 */
@Service
public class StaticDataService {

	/**
	 * Thread specific providers
	 */
    //private final ThreadLocal<StaticDataProvider> threadSpecificProvider = new ThreadLocal<StaticDataProvider>();

    /**
     * Set of transactions which should refresh StaticDataProvider after commit
     */
    private final Set<Transaction> modificationTransactions = new HashSet<>();

    //private StaticDataProvider activeProvider;

    @Value("${version:0.0.0}")
    private String appVersion;

    /* managed components */

    private final EntityManager em;

    /* repository with package visibility for initialization */

    final BookRepository bookRepository;

    @Autowired
    public StaticDataService(final EntityManager em,
                             final BookRepository bookRepository) {
        this.em = em;
        this.bookRepository = bookRepository;
    }

    /**
     * Must be called during application startup within transaction. Initialize type enums and
     * static data provider.
     */
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void init() {
        // prepare active provider
        //activeProvider = createProvider();

        // init interceptor
        StaticDataTransactionInterceptor.INSTANCE.begin(this);

        // register provider for current transaction/thread
        //threadSpecificProvider.set(activeProvider);
    }

    /**
     * Switch data provider for current transaction
     */
//    public StaticDataProvider refreshForCurrentThread() {
//        StaticDataProvider provider = activeProvider;
//        // update data provider for thread
//        threadSpecificProvider.set(provider);
//    	return provider;
//    }

    public void reloadOnCommit() {
        //Transaction tx = getCurrentActiveTransaction(); //TODO hibernate search 6
    	Transaction tx = HibernateUtils.getCurrentTransaction(em);
        reloadOnCommit(tx);
    }

    public void reloadOnCommit(Transaction tx) {
        checkActiveTransaction(tx);

        synchronized(modificationTransactions) {
        	modificationTransactions.add(tx);
        }
    }

//    public StaticDataProvider getData() {
//        StaticDataProvider provider = threadSpecificProvider.get();
//        if (provider == null) {
//            throw new IllegalStateException("Static data provider for transaction not found");
//        }
//        return provider;
//    }

    public String getAppVersion() {
        return appVersion;
    }

    /**
     * Called when new transaction if registered
     * @param tx
     */
    void registerTransaction(Transaction tx) {
        checkActiveTransaction(tx);

        //StaticDataProvider provider = activeProvider;
        // some provider have to exists
        //Validate.notNull(provider);

        //threadSpecificProvider.set(provider);
	}

    void beforeTransactionCommit(Transaction tx) {
        //checkActiveTransaction(tx);

        synchronized(modificationTransactions) {
        	// check for modified transaction
        	if (!modificationTransactions.contains(tx)) {
        		return;
        	}
        	modificationTransactions.remove(tx);
        }

        // prepare modified provider in current transaction
        try {
            //StaticDataProvider modifiedProvider = createProvider();

            tx.registerSynchronization(new Synchronization(){
                @Override
                public void beforeCompletion() {
                    // nop
                }

                @Override
                public void afterCompletion(int status) {
                    // set new provider if committed
                    if(status==javax.transaction.Status.STATUS_COMMITTED) {
                        //activeProvider = modifiedProvider;
                    }
                }
            });
        } catch (Throwable t) {
            tx.markRollbackOnly();
            throw t;
        }
	}

    /**
     * Create new provider and load all data from DB
     *
     * @return
     */
//    public StaticDataProvider createProvider() {
//        StaticDataProvider provider = new StaticDataProvider();
//        provider.init(this);
//        return provider;
//    }

    private static void checkActiveTransaction(Transaction tx) {
        if (!tx.isActive()) {
            throw new IllegalStateException("Inactive transaction");
        }
    }
}
