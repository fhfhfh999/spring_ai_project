package com.sustech.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtils {

    // 将密码进行SHA-256哈希处理
    public static String encode(String password) {
        try {
            // 创建MessageDigest实例，指定哈希算法
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // 将密码转换为字节数组并进行哈希
            byte[] hash = digest.digest(password.getBytes());

            // 将字节数组转换为16进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    // 校验输入的密码是否与已加密的哈希值匹配
    public static boolean verifyPassword(String password, String hashedPassword) {
        String hashedInput = encode(password);
        return hashedInput.equals(hashedPassword);
    }
}

