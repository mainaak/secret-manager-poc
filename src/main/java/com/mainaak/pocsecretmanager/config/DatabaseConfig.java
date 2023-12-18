package com.mainaak.pocsecretmanager.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "datasource")
public class DatabaseConfig {

    private String jdbcUrl;
    private String secretManagerArn;
    private String password;

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public void setSecretManagerArn(String secretManagerArn) {
        this.secretManagerArn = secretManagerArn;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl("jdbc-secretsmanager:postgresql://localhost:5433/mettl_misc_db");
        config.setJdbcUrl(this.jdbcUrl);
//        config.setUsername("postgres-poc");
        config.setUsername(this.secretManagerArn);
//        config.setPassword(this.password);
        config.setDriverClassName("com.amazonaws.secretsmanager.sql.AWSSecretsManagerPostgreSQLDriver");
//        config.setDriverClassName("org.postgresql.Driver");
        config.setMaxLifetime(30000L);
        config.setMaximumPoolSize(1);
        return new HikariDataSource(config);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper;
    }

    /*
    /* @Bean
//    @ConditionalOnExpression("!'${dataSource.username:}'.isEmpty() && !'${dataSource.password:}'.isEmpty()")
    @ConditionalOnExpression("${secretManagerCreds:false}")
    public DataSource dataSource(@Value("${jdbc.driverClassName}") String driverClassName, @Value("$mettl.props{dataSource.url}") String url,
                                 @Value("$mettl.props{dataSource.maxPoolSize:20}") String poolSize, @Value("${dataSource.username:${hikariDataSource.username}}") String username,
                                 @Value("${dataSource.password:${hikariDataSource.password}}") String password) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl(url);
        dataSource.setMaximumPoolSize(Integer.parseInt(poolSize));
        return dataSource;
    }*/

    /*
    @Bean(name = "dataSource")
    @ConditionalOnMissingBean(name = "dataSource")
    public DataSource dataSourceAlternate(@Value("$mettl.props{jdbc.driverClassName}") String driverClassName, @Value("$mettl.props{dataSource.url}") String url,
                                          @Value("$mettl.props{dataSource.maxPoolSize:20}") String poolSize, @Value("$mettl.props{dataSource.username}") String username,
                                          @Value("$mettl.props{dataSource.password}") String password) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl(url);
        dataSource.setMaximumPoolSize(Integer.parseInt(poolSize));
        return dataSource;
    }

     */
}
