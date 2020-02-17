package com.dhruv.demo.persistance;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;


@Slf4j
@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(
        basePackages = "com.dhruv.demo.persistance.repository",
        entityManagerFactoryRef = "coreEntityManager",
        transactionManagerRef = "coreTransactionManager"
)
public class PersistenceConfiguration {

    private static final String SHOW_SQL = "hibernate.show_sql";
    private static final String FORMAT_SQL = "hibernate.format_sql";
    @Value("${persistence.user:#{null}}")
    private String username;
    @Value("${persistence.password:#{null}}")
    private String password;
    @Value("${persistence.databaseName:#{null}}")
    private String database;
    @Value("${persistence.jdbcUrl:#{null}}")
    private String jdbcUrl;
    @Value("${persistence.serverName:#{null}}")
    private String server;
    @Value("${persistence.portNumber:#{null}}")
    private Integer port;
    @Value("${persistence.maximumPoolSize:#{null}}")
    private Integer maxPoolSize;
    @Value("${persistence.connectionTimeout:#{null}}")
    private Long connectionTimeout;

    @Bean
    @Primary
    @Qualifier("applicationDataSource")
    public DataSource applicationDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.addDataSourceProperty("databaseName", database);
        ds.addDataSourceProperty("serverName", server);
        ds.addDataSourceProperty("portNumber", port);
        ds.setJdbcUrl(jdbcUrl);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setConnectionTimeout(connectionTimeout);
        ds.setMaximumPoolSize(maxPoolSize);
        return ds;
    }

    @Bean
    @Primary
    @Qualifier("coreEntityManager")
    public LocalContainerEntityManagerFactoryBean coreEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(applicationDataSource());
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setPackagesToScan("com.dhruv.demo.persistance.entity");
        em.setJpaPropertyMap(hibernateProperties());
        return em;
    }

    public HashMap<String, Object> hibernateProperties() {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put(Environment.SHOW_SQL, true);
        properties.put(Environment.FORMAT_SQL, true);
        return properties;
    }

    @Bean
    @Primary
    @Qualifier("coreTransactionManager")
    public PlatformTransactionManager coreTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(coreEntityManager().getObject());
        return jpaTransactionManager;
    }


    /*
     * To catch Platform specific exceptions and throw them as Spring's unified
     * unchecked exceptions. DAOs need to be marked as @Repository for this to take
     * effect.
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessorBean() {
        return new PersistenceExceptionTranslationPostProcessor();
    }


}


