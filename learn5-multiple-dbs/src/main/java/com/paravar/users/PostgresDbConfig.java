package com.paravar.users;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = PostgresDbConfig.PACKAGE_NAME,
        entityManagerFactoryRef = PostgresDbConfig.ENTITY_MANAGER_FACTORY_REF,
        transactionManagerRef = PostgresDbConfig.TRANSACTION_MANAGER_REF
)
@AllArgsConstructor
public class PostgresDbConfig {

    public static final String PACKAGE_NAME =  "com.paravar.users"; // we can read from env
    public static final String DATASOURCE_PREFIX = "spring.datasource.postgres";
    public static final String PERSISTENCE_UNIT_ID ="postgres";
    public static final String ENTITY_MANAGER_FACTORY_REF = "postgresEntityManagerFactory"; // bean & method name
    public static final String TRANSACTION_MANAGER_REF = "postgresTransactionManager"; // bean & method name

    @Primary
    @Bean
    @ConfigurationProperties(prefix = PostgresDbConfig.DATASOURCE_PREFIX)
    public DataSource postgresDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    @ConfigurationProperties(prefix = DATASOURCE_PREFIX + ".jpa")
    public JpaProperties postgresJpaProperties() {
        return new JpaProperties();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(DataSource postgresDataSource, JpaProperties postgresJpaProperties) {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(postgresJpaProperties.isShowSql()); // Set show-sql explicitly

        EntityManagerFactoryBuilder builder = new EntityManagerFactoryBuilder(
                vendorAdapter, postgresJpaProperties.getProperties(), null);

        return builder
                .dataSource(postgresDataSource)
                .packages(PACKAGE_NAME)
                .persistenceUnit(PERSISTENCE_UNIT_ID) // unique id
                .properties(postgresJpaProperties.getProperties()) // Apply JPA properties
                .build();

    }

    @Primary
    @Bean
    public PlatformTransactionManager postgresTransactionManager(LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(postgresEntityManagerFactory.getObject());
        return transactionManager;
    }



}
