package com.mainaak.pocsecretmanager.dao;

import com.mainaak.pocsecretmanager.model.SecretManagerPoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class DemoRepoImpl implements DemoRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DemoRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int create(SecretManagerPoc secretManagerPoc) {
        final String sql = "INSERT INTO secret_manager_poc (name, contact) VALUES (?, ?) RETURNING id;";
        return (int) jdbcTemplate.queryForObject(sql, new Object[]{secretManagerPoc.getName(), secretManagerPoc.getContact()}, new int[]{Types.VARCHAR, Types.INTEGER}, (RowMapper<Object>) (rs, rowNum) -> rs.getInt("id"));
    }

    @Override
    public SecretManagerPoc get(int id) {
        final String sql = "SELECT * FROM secret_manager_poc WHERE id = ? ;";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new int[]{Types.INTEGER}, ((rs, rowNum) -> {
            SecretManagerPoc secretManagerPoc = new SecretManagerPoc();
            secretManagerPoc.setContact(rs.getObject("contact", Integer.class).longValue());
            secretManagerPoc.setId(rs.getInt("id"));
            secretManagerPoc.setName(rs.getString("name"));
            return secretManagerPoc;
        }));
    }

    @Override
    public List<SecretManagerPoc> getAll() {
        final String sql = "SELECT * FROM secret_manager_poc;";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps.stream().map(data -> {
            SecretManagerPoc secretManagerPoc = new SecretManagerPoc();
            secretManagerPoc.setId((Integer) data.get("id"));
            secretManagerPoc.setName((String) data.get("name"));
            secretManagerPoc.setContact(((Integer) data.get("contact")).longValue());
            return secretManagerPoc;
        }).collect(Collectors.toList());
    }
}
