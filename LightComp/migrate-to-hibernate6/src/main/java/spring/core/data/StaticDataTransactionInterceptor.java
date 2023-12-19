package spring.core.data;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;

/**
 * Interceptor for StaticDataService notifications
 * 
 *
 */
public class StaticDataTransactionInterceptor extends EmptyInterceptor {

    public static final StaticDataTransactionInterceptor INSTANCE = new StaticDataTransactionInterceptor();

    private static final long serialVersionUID = 1L;

    private volatile StaticDataService service;

    private StaticDataTransactionInterceptor() {
    }

    void begin(StaticDataService service) {
        this.service = service;
    }

    @Override
    public void afterTransactionBegin(Transaction tx) {
        if (service != null) {
            service.registerTransaction(tx);
        }
    }

    @Override
    public void beforeTransactionCompletion(Transaction tx) {
        if (service != null) {
            service.beforeTransactionCommit(tx);
        }
    }
}
