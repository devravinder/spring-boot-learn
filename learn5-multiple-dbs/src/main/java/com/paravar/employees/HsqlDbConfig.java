package com.paravar.employees;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = HsqlDbConfig.PACKAGE_NAME,
        entityManagerFactoryRef = HsqlDbConfig.ENTITY_MANAGER_FACTORY_REF,
        transactionManagerRef = HsqlDbConfig.TRANSACTION_MANAGER_REF
)
@AllArgsConstructor
@Slf4j
public class HsqlDbConfig {

    public static final String PACKAGE_NAME =  "com.paravar.employees";
    public static final String DATASOURCE_PREFIX = "spring.datasource.hsqldb";
    public static final String PERSISTENCE_UNIT_ID ="hsqldb";
    public static final String ENTITY_MANAGER_FACTORY_REF = "hsqldbEntityManagerFactory"; // bean & method name
    public static final String TRANSACTION_MANAGER_REF = "hsqldbTransactionManager"; // bean & method name



    @Bean
    @ConfigurationProperties(prefix = HsqlDbConfig.DATASOURCE_PREFIX)
    public DataSource hsqldbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = DATASOURCE_PREFIX + ".jpa")
    public JpaProperties hsqldbJpaProperties() {
        return new JpaProperties();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean hsqldbEntityManagerFactory(
            @Qualifier("hsqldbDataSource") DataSource hsqldbDataSource, @Qualifier("hsqldbJpaProperties") JpaProperties hsqldbJpaProperties) {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(hsqldbJpaProperties.isShowSql()); // Set show-sql explicitly

        EntityManagerFactoryBuilder builder = new EntityManagerFactoryBuilder(
                vendorAdapter, hsqldbJpaProperties.getProperties(), null);

        return builder
                .dataSource(hsqldbDataSource)
                .packages(HsqlDbConfig.PACKAGE_NAME)
                .persistenceUnit(HsqlDbConfig.PERSISTENCE_UNIT_ID) // uniqueId
                .properties(hsqldbJpaProperties.getProperties())
                .build();
    }
    @Bean
    public PlatformTransactionManager hsqldbTransactionManager(@Qualifier(HsqlDbConfig.ENTITY_MANAGER_FACTORY_REF) LocalContainerEntityManagerFactoryBean hsqldbEntityManagerFactory) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(hsqldbEntityManagerFactory.getObject());
        return transactionManager;
    }



}
