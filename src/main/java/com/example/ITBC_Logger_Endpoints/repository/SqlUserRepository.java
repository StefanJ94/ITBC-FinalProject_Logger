package com.example.ITBC_Logger_Endpoints.repository;

import com.example.ITBC_Logger_Endpoints.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SqlUserRepository implements UserRepository{


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAllUsers() {
        String query = "SELECT id, username, password, email FROM Users";

        return jdbcTemplate.query(
                query,
                BeanPropertyRowMapper.newInstance(User.class)
        );
    }

    @Override
    public void insertUser(User user) {
        String action = "INSERT INTO Users ([id],[username],[password],[email]) VALUES ('"
                + user.getId() + "','" + user.getUsername() + "','" + user.getPassword() +
                "','" + user.getEmail() + "')";

        jdbcTemplate.execute(action);
    }
}
