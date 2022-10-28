package com.example.ITBC_Logger_Endpoints.repository;

import com.example.ITBC_Logger_Endpoints.model.Admin;
import com.example.ITBC_Logger_Endpoints.model.Log;
import com.example.ITBC_Logger_Endpoints.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository {

    List<User> getAllUsers();
    void insertUser(User user);
    void insertUserAdmin(Admin admin);

    List<Log> getAllLogs();
    void  insertLog(Log log);
    void insertLog2(Log log);

    UUID userLogin(User user);

    void changePassword (UUID id, User user);
}
