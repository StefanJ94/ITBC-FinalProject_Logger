package com.example.ITBC_Logger_Endpoints.repository;

import com.example.ITBC_Logger_Endpoints.enums.LogType;
import com.example.ITBC_Logger_Endpoints.model.Admin;
import com.example.ITBC_Logger_Endpoints.model.Log;
import com.example.ITBC_Logger_Endpoints.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

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
    public void insertUserAdmin(Admin admin) {
        String action = "INSERT INTO Users ([id],[username],[password],[email]) VALUES ('"
                + admin.getId() + "','" + admin.getUsername() + "','" + admin.getPassword() +
                "','" + admin.getEmail() + "')";

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
        User user;
        int logType;
        if (log.getLogType().equals(LogType.ERROR)) {
            logType = 0;
        } else if (log.getLogType().equals(LogType.WARNING)) {
            logType = 1;
        } else {
            logType = 2;
        }

        String action = "INSERT INTO Logs ([logId], [message], [logType], [dateTime], [id]) VALUES ('" + log.getLogId() +
                "','" + log.getMessage() + "','" + log.getLogType() + "','" + log.getDateTime() + "','" + log.getId() + "')";

        jdbcTemplate.execute(action);
    }

    @Override
    public void insertLog2(Log log) {
        String action = "INSERT INTO Logs ([logId], [message], [logType], [dateTime], [id]) VALUES ('" + log.getLogId() +
                "','" + log.getMessage() + "','" + log.getLogType() + "','" + log.getDateTime() + "','" + log.getId() + "')";

        jdbcTemplate.execute(action);
    }

    @Override
    public UUID userLogin(User user) {
        UUID token = null;
        String findUser = "SELECT username FROM Users WHERE username = '"+ user.getUsername()+"'";
        String findUserClass = jdbcTemplate.queryForObject(findUser, String.class);

        String findPassword = "SELECT password FROM Users WHERE username = '"+ user.getUsername()+"'";
        String findPasswordUser = jdbcTemplate.queryForObject(findPassword, String.class);

        String findId = "SELECT id FROM Users WHERE username = '"+ user.getUsername()+"'";
        UUID findIdUser = jdbcTemplate.queryForObject(findId, UUID.class);

        if (user.getPassword().equals(findPasswordUser) && user.getUsername().equals(findUserClass)) {
           token =  findIdUser;
        }
        return token;
    }

    @Override
    public void changePassword (UUID id, User user) {
        String password = user.getPassword();
        String action = "UPDATE Users SET [password]='" + password + "' WHERE id='" + id + "'";
        jdbcTemplate.execute(action);
    }
}
