package spring.common.db;

import java.io.Serializable;

import org.apache.commons.lang3.Validate;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.EntityManager;

/**
 * Helper class for Hibernate.
 */
public class HibernateUtils {

    /**
     * Unproxy given object. If object is uninitialized Hibernate proxy then entity
     * will be loaded from database.
     *
     * @param object
     *            POJO or Hibernate proxy
     *
     * @return Returns POJO or initialized entity, can be null when object was null.
     */
    @SuppressWarnings("unchecked")
    public static <T> T unproxy(Object object) {
        if (object == null) {
            return null;
        }

        if (object instanceof HibernateProxy) {
            object = ((HibernateProxy)object).getHibernateLazyInitializer().getImplementation();
        }

        return (T) object;
    }

    /**
     * Unproxy initialized object.
     *
     * @param object
     *            POJO or Hibernate proxy
     *
     * @return Returns initialized entity.
     * @throws RuntimeException
     *             When object was null or uninitialized Hibernate proxy.
     */
    @SuppressWarnings("unchecked")
    public static <T> T unproxyInitialized(Object object) {
        if (!Hibernate.isInitialized(object)) {
            throw new IllegalStateException("Unitialized entity, class = " + object.getClass().getName());
        }
        if (object instanceof HibernateProxy) {
            object = ((HibernateProxy)object).getHibernateLazyInitializer().getImplementation();
        }
        return (T) object;
    }

    /**
     * Unwraps Hibernate session from given entity manager.
     */
    public static Session getCurrentSession(EntityManager em) {
        return em.unwrap(Session.class);
    }

    /**
     * Returns currently running transaction for given entity manager.
     *
     * @throws RuntimeException
     *             When given entity manager does not have active transaction.
     */
    public static Transaction getCurrentTransaction(EntityManager em) {
        SharedSessionContractImplementor ssci = em.unwrap(SharedSessionContractImplementor.class);
        Validate.isTrue(ssci.isTransactionInProgress());
        Transaction tx = ssci.accessTransaction();
        Validate.isTrue(tx.isActive());
        return tx;
    }

    /**
     * Method testing if given object is initialized.
     *
     * @param object
     *            POJO or Hibernate proxy
     * @return True when object is POJO or it is Hibernate proxy and internal
     *         LazyInitializer is initialized.
     * @throws RuntimeException
     *             When object is null.
     */
    public static boolean isInitialized(Object object) {
        Validate.notNull(object);
        return Hibernate.isInitialized(object);
    }

    /**
     * Return the persistent instance with the given identifier, assuming that the
     * instance exists. This method might return a proxied instance.
     *
     * @param detachIfNotLoaded
     *            When true and entity is not loaded in persistent context returned
     *            reference (proxy) will be detached.
     */
    public static <E> E getEntityRef(Serializable entityId, Class<E> entityClass, Session session,
                                     boolean detachIfNotLoaded) {
        // retrieve entity or create proxy
        E entity = session.byId(entityClass).getReference(entityId);
        // L1 cache access
        if (detachIfNotLoaded && !isInitialized(entity)) {
            session.evict(entity);
        }
        return entity;
    }
}
