package com.sustech.service;

import com.sustech.domain.User;
import com.sustech.repository.UserRepository;
import com.sustech.response.LoginResponse;
import com.sustech.utils.SecurityUtils;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean save(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }


    public LoginResponse validateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("用户不存在");
            return LoginResponse.failure("账号或密码错误");
        }
        if (!SecurityUtils.verifyPassword(password, user.getPassword())) {
            System.out.println("密码错误");
            return LoginResponse.failure("账号或密码错误");
        }
        return LoginResponse.success(user);
    }

    public UserDetails loadUserByUsername(String username) {
        return userRepository.getUsersByUsername(username);
    }
}
