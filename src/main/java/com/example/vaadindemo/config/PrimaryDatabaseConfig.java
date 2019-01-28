package com.example.vaadindemo.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

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
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "primaryEntityManagerFactory", 
        transactionManagerRef = "primaryTransactionManager",
        basePackages = { "com.example.vaadindemo.repository"} )
@ConfigurationProperties(prefix="primary.datasource")
public class PrimaryDatabaseConfig {
    
    @Primary
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix="primary.datasource")
    public DataSource primaryJdbcDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "primaryEntityManagerFactory")
    @PersistenceContext( unitName = "primary" )
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("primaryDataSource") DataSource dataSource) {
        
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", "create");
        return builder
                .dataSource(dataSource)
                .packages("com.example.vaadindemo.model")
                .persistenceUnit("primary")
                .properties(properties)
                .build();
    }

    @Primary
    @Bean(name = "primaryTransactionManager")
    public JpaTransactionManager transactionManager(
            @Qualifier("primaryEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    /*
    @Primary
    @Bean(name = "primaryH2Server", initMethod = "start", destroyMethod = "stop")
    public Server h2Server(@Qualifier("primaryWebServer") WebServer webServer) throws SQLException {
       
    	return new Server(webServer, new String[] { "-web", "-webAllowOthers", "-webPort", "8082" });
    }
    
    @Primary
    @Bean(name = "primaryWebServer")
    public WebServer h2WebServer() throws SQLException {

    	return new WebServer();
    }
    
    @Primary
    @Bean(name = "primaryH2Url")
    public String h2Url(
    		@Qualifier("primaryH2Server") Server server,
    		@Qualifier("primaryWebServer") WebServer webServer, 
    		@Qualifier("primaryDataSource") DataSource dataSource) throws SQLException {

    	return webServer.addSession(dataSource.getConnection());
    }
    */
} 
