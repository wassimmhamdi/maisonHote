package com.MS1.gestionUsers.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MS1.gestionUsers.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByEmailContaining(String email);
    Boolean existsByEmail(String email);
    public User findByResetPasswordToken(String token);

}
