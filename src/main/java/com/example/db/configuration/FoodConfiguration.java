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
}