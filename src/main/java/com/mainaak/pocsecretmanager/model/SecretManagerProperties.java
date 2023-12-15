package com.mainaak.pocsecretmanager.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "secret")
public class SecretManagerProperties {

    private String username;
    private String password;
    private String engine;
    private String host;
    private String port;
    private String dbname;
    private String dbInstanceIdentifier;
    private String demoProp;

    public String getDemoProp() {
        return demoProp;
    }

    public void setDemoProp(String demoProp) {
        this.demoProp = demoProp;
    }

    public SecretManagerProperties() {

    }

    public SecretManagerProperties(String username, String password, String engine, String host, String port, String dbname, String dbInstanceIdentifier) {
        this.username = username;
        this.password = password;
        this.engine = engine;
        this.host = host;
        this.port = port;
        this.dbname = dbname;
        this.dbInstanceIdentifier = dbInstanceIdentifier;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getDbInstanceIdentifier() {
        return dbInstanceIdentifier;
    }

    public void setDbInstanceIdentifier(String dbInstanceIdentifier) {
        this.dbInstanceIdentifier = dbInstanceIdentifier;
    }

    public SecretManagerProperties makeACopy() {
        SecretManagerProperties smp = new SecretManagerProperties();
        smp.setUsername(this.username);
        smp.setPassword(this.password);
        smp.setDbname(this.dbname);
        smp.setPort(this.port);
        smp.setDbInstanceIdentifier(this.dbInstanceIdentifier);
        smp.setEngine(this.engine);
        smp.setHost(this.host);
        return smp;
    }
}
