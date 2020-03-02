package com.example.embeddedmysql;

import static com.wix.mysql.distribution.Version.v5_7_19;
import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

// @ActiveProfiles("test")
public class EmbeddedDatabaseTest {

    @PersistenceContext
    private EntityManager entityManager;

    private static EmbeddedMysql embeddedMysql;

    @BeforeAll
    public static void init() throws SQLException, LiquibaseException {
        MysqldConfig config = MysqldConfig.aMysqldConfig(v5_7_19)
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

    }

    @AfterAll
    public static void stop() {
        if (null != embeddedMysql) {
            embeddedMysql.stop();
        }
    }

    @Test
    public void testSelect() throws SQLException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("embedded-mysql");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNativeQuery("SELECT 1");
        assertEquals(BigInteger.valueOf(1L), query.getSingleResult());
    }

}
