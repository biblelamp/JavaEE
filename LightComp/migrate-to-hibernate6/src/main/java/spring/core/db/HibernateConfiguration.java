package spring.core.db;

import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import spring.core.data.StaticDataTransactionInterceptor;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "elza.data")
public class HibernateConfiguration implements HibernatePropertiesCustomizer {

    /**
     * Batch size
     *
     * Maximal number of items inside IN clause
     */
    private int batchSize = 1000;

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    @Override
    public void customize(final Map<String, Object> vendorProperties) {
        // register static data interceptor
        vendorProperties.put(org.hibernate.cfg.AvailableSettings.INTERCEPTOR, StaticDataTransactionInterceptor.INSTANCE);
        // use enhanced (modern) generators (in JPA is default true -> safety check)
        vendorProperties.put(org.hibernate.cfg.AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS, Boolean.TRUE);
        // set custom provider for id generator strategy
        vendorProperties.put(org.hibernate.jpa.AvailableSettings.IDENTIFIER_GENERATOR_STRATEGY_PROVIDER,
                spring.core.db.IdentifierGeneratorStrategyProvider.class.getName());
    }
}
