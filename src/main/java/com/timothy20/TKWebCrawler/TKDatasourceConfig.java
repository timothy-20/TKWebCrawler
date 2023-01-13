package com.timothy20.TKWebCrawler;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories (basePackages = "com.timothy20.TKWebCrawler.repositories")
public class TKDatasourceConfig
{
    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource DataSource()
    {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "jpaProperties")
    @ConfigurationProperties(prefix = "spring.jpa")
    public JpaProperties JpaProperties()
    {
        return new JpaProperties();
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean EntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("dataSource") DataSource primaryDataSource,
                                                                       @Qualifier("jpaProperties") JpaProperties primaryJpaProperties)
    {
        return builder
                .dataSource(primaryDataSource)
                .properties(primaryJpaProperties.getProperties())
                .packages("com.timothy20.TKWebCrawler.entities")
                .persistenceUnit("default")
                .build();
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager TransactionManager(@Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean)
    {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(entityManagerFactoryBean.getObject());

        jpaTransactionManager.setNestedTransactionAllowed(true);

        return jpaTransactionManager;
    }
}
