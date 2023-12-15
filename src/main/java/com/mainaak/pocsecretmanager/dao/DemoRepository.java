package com.mainaak.pocsecretmanager.dao;

import com.mainaak.pocsecretmanager.model.SecretManagerPoc;

import java.util.List;

public interface DemoRepository {

    int create(SecretManagerPoc secretManagerPoc);
    SecretManagerPoc get(int id);
    List<SecretManagerPoc> getAll();
//    SecretManagerPoc update(int id);
//    boolean delete(int id);

}
