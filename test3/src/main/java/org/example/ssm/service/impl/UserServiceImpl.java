package org.example.ssm.service.impl;

import org.example.ssm.entity.User;
import org.example.ssm.mapper.UserMapper;
import org.example.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * MD5加密工具方法（简单加密，生产环境建议加盐）
     */
    private String encryptPassword(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    @Transactional  // 事务注解，确保操作原子性
    public boolean register(User user) {
        // 1. 检查用户名是否已存在
        if (userMapper.countByUsername(user.getUsername()) > 0) {
            return false; // 用户名已存在
        }

        // 2. 密码加密存储
        user.setPassword(encryptPassword(user.getPassword()));

        // 3. 插入数据库
        return userMapper.insert(user) > 0;
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        return userMapper.countByUsername(username) == 0;
    }

    @Override
    public User login(String username, String password) {
        // 1. 查询用户
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            return null; // 用户不存在
        }

        // 2. 密码比对（加密后比对）
        String encryptedPwd = encryptPassword(password);
        if (!encryptedPwd.equals(user.getPassword())) {
            return null; // 密码错误
        }

        // 3. 清除敏感信息后返回（可选）
        user.setPassword(null);
        return user;
    }
}