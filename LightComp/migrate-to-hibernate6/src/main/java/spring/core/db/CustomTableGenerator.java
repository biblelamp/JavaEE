package spring.core.db;

import java.util.Properties;

import org.hibernate.MappingException;
import org.hibernate.id.enhanced.TableGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

/**
 * Generátor ID pro hibernate podle specifikace.
 *
 * <p>
 * Pro aktivaci je nutno použít {@link IdentifierGeneratorStrategyProvider}.
 * </p>
 */
public class CustomTableGenerator extends TableGenerator {

	private static final long serialVersionUID = 1L;

	public static final String DEFAULT_INCREMENT_SIZE = "20";

    @Override
    public void configure(final Type type, final Properties params, final ServiceRegistry serviceRegistry) throws MappingException {
        params.setProperty(TABLE_PARAM, "db_hibernate_sequences");
        params.setProperty(SEGMENT_VALUE_PARAM, params.getProperty("target_table") + "|" + params.getProperty("target_column"));
        params.setProperty(INCREMENT_PARAM, DEFAULT_INCREMENT_SIZE);
        // params.setProperty(OPT_PARAM, "pooled");

        super.configure(type, params, serviceRegistry);
    }
}
