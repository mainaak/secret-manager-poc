package com.mainaak.pocsecretmanager.service;

import com.mainaak.pocsecretmanager.dao.DemoRepository;
import com.mainaak.pocsecretmanager.model.DemoRequest;
import com.mainaak.pocsecretmanager.model.SecretManagerPoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoService {

    private final DemoRepository demoRepository;

    @Autowired
    public DemoService(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    public int insert(SecretManagerPoc secretManagerPoc) {
        return demoRepository.create(secretManagerPoc);
    }

    public SecretManagerPoc get(int id) {
        return demoRepository.get(id);
    }

    public List<SecretManagerPoc> getAll() {
        return demoRepository.getAll();
    }
}
