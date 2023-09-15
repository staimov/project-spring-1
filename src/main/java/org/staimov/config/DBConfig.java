package org.staimov.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DBConfig {
    @Bean
    public LocalSessionFactoryBean sessionFactory(
            DataSource dataSource,
            Properties hibernateProperties) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("org.staimov.domain");
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(DBConstants.DB_DRIVER);
        dataSource.setJdbcUrl(DBConstants.DB_URL);
        dataSource.setUsername(DBConstants.DB_USER);
        dataSource.setPassword(DBConstants.DB_PASS);
        dataSource.setMaximumPoolSize(DBConstants.MAX_POOL_SIZE);
        return dataSource;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, DBConstants.DB_DIALECT);
        properties.put(Environment.HBM2DDL_AUTO, DBConstants.DB_HBM2DDL_AUTO);
        properties.put(Environment.DRIVER, DBConstants.DB_DRIVER);
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory factory) {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(factory);
        return manager;
    }
}
