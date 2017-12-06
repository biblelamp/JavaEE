package example;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import example.model.Person;

public class PersonTest {

    Session session = null;
    private final String[][] persons = {
        {"1", "Matthew"}, {"2", "Mark"}, {"3", "Luke"}, {"4", "John"}};

    /**
     * Creating a session
     * @return org.hibernate.Session
     */
    private Session createHibernateSession() {
        SessionFactory  sessionFactory  = null;
        ServiceRegistry serviceRegistry = null;
        try {
            try {
                Configuration cfg = new Configuration().
                                        addResource("person.hbm.xml").configure();
                serviceRegistry = new StandardServiceRegistryBuilder().
                                      applySettings(cfg.getProperties()).build();
                sessionFactory = cfg.buildSessionFactory(serviceRegistry);
            } catch (Throwable e) {
                System.err.println("Failed to create sessionFactory object." + e);
                throw new ExceptionInInitializerError(e);
            }
            session = sessionFactory.openSession();
            System.out.println("Creating session.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return session;
    }

    /**
     * Adding records to the table
     */
    private void recordsAdd() {
        try {
            System.out.println("Adding to the table of database...");
            Transaction tx = session.beginTransaction();
            for (int i = 0; i < persons.length; i++) {
                Person person = new Person();
                person.setId(Integer.valueOf(persons[i][0]));
                person.setName(persons[i][1]);
                session.save(person);
            }
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
        System.out.println("\nReading records from table");
        String query = "select p from " + Person.class.getSimpleName() + " p";

        @SuppressWarnings("unchecked")
        List<Person> list = (List<Person>)session.createQuery(query).list();
        System.out.println(list);
    }

    /**
     * Seeking record
     */
    private void recordFind(final int id) {
        System.out.println("\nReading record by ID");
        Person person = (Person) session.load(Person.class, id);
        System.out.println(person);
    }

    /**
     * Constructor
     */
    public PersonTest() {
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
        new PersonTest();
        System.exit(0);
    }
}