package pl.sp6pat.ham.hf33wosp.repositories;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "pl.sp6pat.ham.hf33wosp.repositories.cl",
        entityManagerFactoryRef = "mariadbEntityManagerFactory",
        transactionManagerRef = "mariadbTransactionManager"
)
public class ClDbConfig {

    @Primary
    @Bean(name = "mariadbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.cl")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "mariadbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("mariadbDataSource") DataSource dataSource) {

        return builder
                .dataSource(dataSource)
                .packages("pl.sp6pat.ham.hf33wosp.repositories.cl")
                .persistenceUnit("cl")
                .build();
    }

    @Primary
    @Bean(name = "mariadbTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("mariadbEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}

