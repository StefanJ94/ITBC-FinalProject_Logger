package com.example.ITBC_Logger_Endpoints.repository;

import com.example.ITBC_Logger_Endpoints.enums.LogType;
import com.example.ITBC_Logger_Endpoints.model.Log;
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

    @Override
    public List<Log> getAllLogs() {
        String action = "SELECT [logId], [message], [logType], [dateTime], [id] FROM Logs";
        return jdbcTemplate.query(
                action,
                BeanPropertyRowMapper.newInstance(Log.class)
        );
    }

    @Override
    public void insertLog(Log log) {

        String logType = "";
        if (log.getLogType().equals(LogType.ERROR)) {
            logType = "ERROR";
        } else if (log.getLogType().equals(LogType.WARNING)) {
            logType = "WARNING";
        } else {
            logType = "INFO";
        }

        String action = "INSERT INTO Logs ([logId], [message], [logType], [dateTime], [id]) VALUES ('" + log.getLogId() +
                "','" + log.getMessage() + "','" + log.getLogType() + "','" + log.getDateTime()  + "')";

        jdbcTemplate.execute(action);
    }
}
