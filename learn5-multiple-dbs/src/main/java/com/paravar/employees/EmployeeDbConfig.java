package com.paravar.employees;

import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.paravar.employees",
        entityManagerFactoryRef = "employeeEntityManager",
        transactionManagerRef = "employeeTransactionManager"
)
@AllArgsConstructor
public class EmployeeDbConfig {

    private final Environment env;

    @Bean
    @ConfigurationProperties(prefix="spring.datasource.hsqldb")
    public DataSource employeeDataSource() {
        return DataSourceBuilder.create().build();
    }
    @Bean
    public PlatformTransactionManager employeeTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(employeeEntityManager().getObject());
        return transactionManager;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean employeeEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(employeeDataSource());
        em.setPackagesToScan("com.paravar.employees");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.datasource.hsqldb.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.datasource.hsqldb.hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

}
