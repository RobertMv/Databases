package com.example.db.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * A configuration class for connecting multiple databases.
 * Everything that related to staff, e.g. restaurant, employees
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.db.repository.staff",
        entityManagerFactoryRef = "staffEntityManagerFactory",
        transactionManagerRef = "staffTransactionManager"
)
public class StaffConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.staff")
    public DataSourceProperties staffDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.staff.configuration")
    public DataSource staffDataSource(){
        return staffDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean (name = "staffEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean staffEntityManagerFactory(EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(staffDataSource())
                .packages("com.example.db.entity.staff")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager staffTransactionManager(
            final @Qualifier("staffEntityManagerFactory")
                    LocalContainerEntityManagerFactoryBean staffEntityManagerFactory) {
        return new JpaTransactionManager(
                Objects.requireNonNull(staffEntityManagerFactory.getObject()));
    }
}
