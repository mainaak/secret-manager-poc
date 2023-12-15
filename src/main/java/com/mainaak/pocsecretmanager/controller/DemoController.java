package com.mainaak.pocsecretmanager.controller;

import com.amazonaws.secretsmanager.caching.SecretCache;
import com.amazonaws.secretsmanager.caching.SecretCacheConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mainaak.pocsecretmanager.model.SecretManagerPoc;
import com.mainaak.pocsecretmanager.model.SecretManagerProperties;
import com.mainaak.pocsecretmanager.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@RestController
@RequestMapping("api/v1/demo")
public class DemoController {

    private final DemoService demoService;
    private final SecretManagerProperties secretManagerProperties;
    private final DataSource dataSource;
    private final ObjectMapper objectMapper;

    @Autowired
    public DemoController(DemoService demoService, SecretManagerProperties secretManagerProperties, @Qualifier("dataSource") DataSource dataSource, ObjectMapper objectMapper) {
        this.demoService = demoService;
        this.secretManagerProperties = secretManagerProperties;
        this.dataSource = dataSource;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public int save(@RequestBody SecretManagerPoc secretManagerPoc) {
        return demoService.insert(secretManagerPoc);
    }

    @GetMapping("{id}")
    public SecretManagerPoc get(@PathVariable int id) {
        return demoService.get(id);
    }

    @GetMapping
    public List<SecretManagerPoc> getAll() {
        return demoService.getAll();
    }

    @GetMapping(value = "properties")
    public Object getSecretManagerProperties() throws CloneNotSupportedException {
        return secretManagerProperties.makeACopy();
    }

    @Value(value = "${secret.demoProp}")
    private String username;

    @GetMapping(value = "value")
    public String getValue() {
        return username;
    }

    @GetMapping("password")
    public String getDataSourceProps() {
        return dataSource.toString();
    }


//    poolling time

}
