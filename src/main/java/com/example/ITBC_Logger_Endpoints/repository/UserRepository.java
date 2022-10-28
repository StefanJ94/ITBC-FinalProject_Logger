package com.example.ITBC_Logger_Endpoints.repository;

import com.example.ITBC_Logger_Endpoints.model.Log;
import com.example.ITBC_Logger_Endpoints.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    List<User> getAllUsers();
    void insertUser(User user);

    List<Log> getAllLogs();
    void  insertLog(Log log);
}
