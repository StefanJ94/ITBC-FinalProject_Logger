package com.example.ITBC_Logger_Endpoints.repository;

import com.example.ITBC_Logger_Endpoints.model.Log;
import com.example.ITBC_Logger_Endpoints.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TestJpaRepository extends JpaRepository<User, UUID> {

    Optional<User> findById(UUID id);
    Optional<User> findByUsernameOrEmail(String username,String email);
    Optional<User> findByUsername(String username);

    List<User> findByUsernameStartsWith(String username);
    List<User> findByEmailStartsWith(String email);

    Optional<Log> findById(int logId);


    @Query(value = "SELECT COUNT(*) FROM Users WHERE username=:username", nativeQuery = true)
    Integer isDuplicateUserName(@Param("username") String username);

    @Query(value = "SELECT COUNT(*) FROM Users WHERE email=:email", nativeQuery = true)
    Integer isDuplicateEmail(@Param("email") String email);

    @Query(value = "SELECT COUNT(*) FROM Logs WHERE id=:id", nativeQuery = true) String
    getNumberOfLogs (@Param("id") UUID id);

    @Query(value = "SELECT COUNT(*) FROM Users WHERE password=:password", nativeQuery = true)
    Integer isExistPassword(@Param("password") String password);

    @Query(value = "SELECT COUNT(*) FROM Users WHERE id=:id", nativeQuery = true)
    Integer isIdExist(@Param("id") UUID id);

    @Query(value = "SELECT [message], [logType], [dateTime], [id] FROM Logs l JOIN Users u ON l.id = u.id WHERE u.id = :id", nativeQuery = true)
    Log getAllLogsByClient(@Param("id") UUID id);

    @Query(value = "SELECT u FROM Users u WHERE u.id = ?1",nativeQuery = true)
    Optional<User> getUserById(@Param("id") UUID id);

    @Query(value = "SELECT u FROM Users u WHERE u.username = ?1",nativeQuery = true)
    Optional<User> findUserByUsername(@Param("username") String username);

    @Query(value = "SELECT u FROM Users u WHERE u.password = ?1",nativeQuery = true)
    Optional<User> findUserByPassword(@Param("password") String password);

    @Query(value = "SELECT u FROM Users u WHERE u.email = ?1",nativeQuery = true)
    Optional<User> findUsersByEmail(@Param("email") String email);
}
