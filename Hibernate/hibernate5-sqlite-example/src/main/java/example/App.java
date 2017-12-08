package example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import example.model.Person;

/*
 * http://www.boraji.com/hibernate-5-one-to-one-association-example
 */
public class App {
    Session session = null;
    private final String[][] persons = {
        {"1", "Matthew"}, {"2", "Mark"}, {"3", "Luke"}, {"4", "John"}};

    /**
     * Creating a session
     * @return org.hibernate.Session
     */
    private Session createHibernateSession() {
        try {
            Map<String, String> settings = new HashMap<String, String>();
            settings.put("hibernate.connection.driver_class", "org.sqlite.JDBC");
            settings.put("hibernate.connection.url", "jdbc:sqlite:mysqlite.db");
            settings.put("hibernate.connection.username", "");
            settings.put("hibernate.connection.password", "");
            settings.put("hibernate.show_sql", "true");
            settings.put("hibernate.hbm2ddl.auto", "update");

            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .applySettings(settings)
                    .build();

            MetadataSources sources = new MetadataSources(registry)
                    .addAnnotatedClass(Person.class);

            Metadata metadata = sources
                    .getMetadataBuilder()
                    .build();

            SessionFactory sessionFactory = metadata
                    .getSessionFactoryBuilder()
                    .build();

            session = sessionFactory.openSession();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        System.out.println("Session created successfully.");
        return session;
    }

    /**
     * Adding records to the table
     */
    private void recordsAdd() {
        try {
            System.out.println("Adding to the table of database:");
            Transaction tx = session.beginTransaction();
            for (int i = 0; i < persons.length; i++)
                session.save(new Person(
                        Integer.valueOf(persons[i][0]),
                        persons[i][1]));
            tx.commit();
            System.out.println("Records added.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reading records
     */
    private void recordsRead() {
        System.out.println("\nReading records from table:");
        String query = "select p from " + Person.class.getSimpleName() + " p";

        @SuppressWarnings("unchecked")
        List<Person> list = (List<Person>)session.createQuery(query).list();
        System.out.println(list);
    }

    /**
     * Seeking record
     */
    private void recordFind(final int id) {
        System.out.println("\nReading record by ID:");
        Person person = (Person) session.load(Person.class, id);
        System.out.println(person);
    }

    /**
     * Constructor
     */
    public App() {
        session = createHibernateSession();
        if (session != null) {
            recordsAdd();
            recordsRead();
            recordFind(Integer.valueOf(persons[1][0]));
            if (session.isOpen())
                session.close();
        }
    }

    public static void main(String[] args) {
        new App();
        System.exit(0);
    }
}