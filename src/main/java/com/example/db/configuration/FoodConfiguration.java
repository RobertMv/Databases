package com.example.db.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * A configuration class for connecting multiple databases.
 * Everything that related to food, e.g. dish(soup, ice cream etc.), product(milk, potato etc.)
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.db.repository.food",
        entityManagerFactoryRef = "foodEntityManagerFactory",
        transactionManagerRef = "foodTransactionManager"
)
//@PropertySource({"classpath:application.properties"})
//@EnableJpaRepositories(
//        basePackages = {"com.example.db.entity.food",
//        "com.example.db.repository.food",
//        "com.example.db.service.food.impl"},
//        entityManagerFactoryRef = "db2EntityManager",
//        transactionManagerRef = "db2TransactionManager"
//)
public class FoodConfiguration {

    @Bean
    @ConfigurationProperties("spring.food")
    public DataSourceProperties foodDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.food.configuration")
    public DataSource foodDataSource() {
        return foodDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "foodEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean foodEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(foodDataSource())
                .packages("com.example.db.entity.food")
                .build();
    }

    @Bean
    public PlatformTransactionManager foodTransactionManager(
            final @Qualifier("foodEntityManagerFactory")
                    LocalContainerEntityManagerFactoryBean foodEntityManagerFactory) {
        return new JpaTransactionManager(
                Objects.requireNonNull(foodEntityManagerFactory.getObject()));
    }
//    private final Environment env;
//
//    public FoodConfiguration(Environment env) {
//        this.env = env;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean db2EntityManager() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(db2Datasource());
//        em.setPackagesToScan("com.orioninc.springapp.entity.users",
//                "com.orioninc.springapp.repository.users",
//                "com.orioninc.springapp.service.users.Impl");
//        em.setPersistenceUnitName("db2EntityManager");
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
//        properties.put("spring.jpa.database-platform", env.getProperty("spring.jpa.database-platform"));
//        em.setJpaPropertyMap(properties);
//        return em;
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "db2")
//    public DataSource db2Datasource() {
//        return DataSourceBuilder.create().driverClassName(
//                env.getProperty("spring.driver-class-name"))
//                .url(env.getProperty("db2.url"))
//                .username(env.getProperty("db2.username"))
//                .password(env.getProperty("db2.password"))
//                .build();
//    }
//
//    @Bean
//    public PlatformTransactionManager db2TransactionManager() {
//
//        JpaTransactionManager transactionManager
//                = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(
//                db2EntityManager().getObject());
//        return transactionManager;
//    }
}