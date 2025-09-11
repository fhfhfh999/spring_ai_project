package com.sustech.service;

import com.sustech.domain.User;
import com.sustech.repository.UserRepository;
import com.sustech.response.LoginResponse;
import com.sustech.utils.SecurityUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public boolean isValidUser(String username, String password) {
//        User user = userRepository.findByUsername(username);
//        String hashedPassword = user.getPassword_hash();
//        return SecurityUtils.verifyPassword(password, hashedPassword);
//    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void save(User user) {
        userRepository.save(user);
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
        if (!SecurityUtils.verifyPassword(password, user.getPassword_hash())) {
            System.out.println("密码错误");
            return LoginResponse.failure("账号或密码错误");
        }
        return LoginResponse.success(user);
    }
}
