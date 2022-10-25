package com.example.ITBC_Logger_Endpoints.repository;

import com.example.ITBC_Logger_Endpoints.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TestJpaRepository extends JpaRepository<User, UUID> {

    Optional<User> findById(UUID id);

    List<User> findByUsernameStartsWith(String username);

    List<User> findByEmailStartsWith(String email);

    @Query(value = "SELECT COUNT(*) FROM Users WHERE username=:username", nativeQuery = true)
    Integer isDuplicateUserName(@Param("username") String username);

    @Query(value = "SELECT COUNT(*) FROM Users WHERE email=:email", nativeQuery = true)
    Integer isDuplicateEmail(@Param("email") String email);
}
