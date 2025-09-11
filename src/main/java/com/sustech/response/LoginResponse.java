package com.sustech.response;

import com.sustech.domain.User;

public class LoginResponse {
    private boolean success;
    private String message;
    private User user;

    public LoginResponse(boolean b, String loginSuccessful, User user) {
        this.success = b;
        this.message = loginSuccessful;
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static LoginResponse success(User user) {
        return new LoginResponse(true, "Login successful", user);
    }

    public static LoginResponse failure(String message) {
        return new LoginResponse(false, message, null);
    }
}

