package com.mainaak.pocsecretmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@ImportResource({"classpath:application-context.xml"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PocSecretManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PocSecretManagerApplication.class, args);
    }

}
