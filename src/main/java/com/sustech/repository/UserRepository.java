package com.sustech.repository;

import com.sustech.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    boolean existsByUsername(String username);

    void deleteByUsername(String username);

    UserDetails getUsersByUsername(String username);
}
