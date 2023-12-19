package spring.core.db;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.id.enhanced.SequenceStyleGenerator;

/**
 * Přetížení volby generátoru ID.
 *
 * Hibernate vyžaduje u každé tabulky uvedení způsobu generování ID. Náš cíl je globálně definovat
 * generátor a jeho konfiguraci na jednom místě. V entitách chceme mít pouze @GeneratedValue bez
 * dalších parametrů.
 * <p>
 * Hibernate must be configured with enhanced generators and custom id generator strategy provider:
 * <br> # hibernate.id.new_generator_mappings = true
 * <br> # hibernate.ejb.identifier_generator_strategy_provider = cz.tacr.elza.core.db.IdentifierGeneratorStrategyProvider
 * </p>
 */
public class IdentifierGeneratorStrategyProvider implements org.hibernate.jpa.spi.IdentifierGeneratorStrategyProvider {

    @Override
    public Map<String, Class<?>> getStrategies() {
        Map<String, Class<?>> strategies = new HashMap<>();
        // should be default strategy for javax.persistence.GenerationType.AUTO
        strategies.put(SequenceStyleGenerator.class.getName(), CustomTableGenerator.class);
        return strategies;
    }
}
