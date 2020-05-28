package activejdbc.examples.simple;

import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.connection_config.DbConfiguration;

public class Application {

    public static void main(String[] args) {

        DbConfiguration config = new DbConfiguration();
        config.loadConfiguration("/database.properties");

        Base.open();
        Base.close();
    }
}
