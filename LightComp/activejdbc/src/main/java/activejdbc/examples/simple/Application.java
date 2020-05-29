package activejdbc.examples.simple;

import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.connection_config.DbConfiguration;

import activejdbc.examples.simple.model.Hello;

public class Application {

    /* After identifying all models, we can process instrumentation by doing one of these two commands:
     1 mvn process-classes
     2 mvn activejdbc-instrumentation:instrument
    */

    public static void main(String[] args) {

        DbConfiguration config = new DbConfiguration();
        config.loadConfiguration("/database.properties");

        Base.open();
        Hello.findAll().dump();
        Base.close();
    }
}
