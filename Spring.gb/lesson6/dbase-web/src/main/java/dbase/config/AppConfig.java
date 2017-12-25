package dbase.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableJpaRepositories("dbase.repository")
@EnableTransactionManagement
@ComponentScan("dbase.service")
public class AppConfig {

    @Bean(name="dataSource")
    public DataSource getDataSource(){

        // источник данных
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        // параметры подключения к базе данных
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:mysqlite.db");
        dataSource.setUsername("");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean(name="entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManager(){
        // класса фабрики реализующей интерфейс FactoryBean<EntityManagerFactory>
        LocalContainerEntityManagerFactoryBean factory =
            new LocalContainerEntityManagerFactoryBean();

        // источник подключения
        factory.setDataSource(getDataSource());

        // адаптера для конкретной реализации JPA, 
        // указывает, какая библиотека будет использоваться в качестве постовщика
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        // указание пакета в котором находятся классы-сущности
        factory.setPackagesToScan("dbase.domain");

        //создание свойств для настройки Hibernate
        Properties jpaProperties = new Properties();

        // диалект базы данных,необходимо для генерации запросов Hibernate к БД
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.SQLiteDialect");

        // максимальная глубины связи(будет рассмотрено в след. уроке)
        jpaProperties.put("hibernate.max_fetch_depth", 3);

        // максимальное число строк, возвращаемых за один запрос из БД
        jpaProperties.put("hibernate.jdbc.fetch_size", 50);

        // максимальное число запросов при использовании пакетных операций
        jpaProperties.put("hibernate.jdbc.batch_size", 10);

        // включает логгирование
        jpaProperties.put("hibernate.show_sql", true);

        factory.setJpaProperties(jpaProperties);
        return factory;
    }

    @Bean(name="transactionManager")
    public JpaTransactionManager transactionManager(
            EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jtm = new JpaTransactionManager();
        jtm.setEntityManagerFactory(entityManagerFactory);
        return jtm;
    }
}