package com.example.embeddedmysql;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;

import com.example.embeddedmysql.domain.Book;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.Charset;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.distribution.Version;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

// @ActiveProfiles("test")
public class EmbeddedDatabaseTest {

    //@PersistenceContext
    //private EntityManager entityManager;

    private static EmbeddedMysql embeddedMysql;

    private static EntityManager em;

    @BeforeAll
    public static void init() throws SQLException, LiquibaseException {
        MysqldConfig config = MysqldConfig.aMysqldConfig(Version.v8_0_17) // v5_7_19 // v8_0_17
                .withCharset(Charset.UTF8)
                .withPort(3307)
                .withTimeZone(TimeZone.getTimeZone(ZoneId.of("UTC")))
                .withUser("test", "test")
                .build();

        embeddedMysql = EmbeddedMysql.anEmbeddedMysql(config)
                .addSchema("test_database")
                .start();

        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();

        dataSourceBuilder.username(embeddedMysql.getConfig().getUsername());
        dataSourceBuilder.password(embeddedMysql.getConfig().getPassword());
        dataSourceBuilder.driverClassName(com.mysql.cj.jdbc.Driver.class.getName());
        dataSourceBuilder.url("jdbc:mysql://localhost:3307/test_database");

        DataSource dataSource = dataSourceBuilder.build();

        Database database = DatabaseFactory
                .getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(dataSource.getConnection()));

        Liquibase liquibase = new Liquibase("db/db.changelog-master.xml",
                new ClassLoaderResourceAccessor(),
                database);

        liquibase.update(new Contexts());

        // connect database
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("embedded-mysql");
        em = emf.createEntityManager();

    }

    @AfterAll
    public static void stop() {
        if (null != embeddedMysql) {
            embeddedMysql.stop();
        }
    }

    @Test
    public void testSelect() {
        Query query = em.createNativeQuery("SELECT 1");
        assertEquals(BigInteger.valueOf(1L), query.getSingleResult());
    }

    @Test
    public void testAddBook() {
        Book book = new Book("Spring 4 professionals", "Chris Schaefer, Clarence Ho, Rob Harrop");
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();

        Query query = em.createNativeQuery("SELECT * FROM book");
        List<Book> books = query.getResultList();
        assertEquals(1, books.size());
    }

}
