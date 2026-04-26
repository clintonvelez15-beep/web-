package org.example.ssm.service;

import org.example.ssm.entity.User;

/**
 * 用户业务逻辑接口
 */
public interface UserService {

    /**
     * 用户注册
     * @param user 用户信息（密码为明文，内部加密）
     * @return 注册成功返回true，失败返回false（用户名已存在）
     */
    boolean register(User user);

    /**
     * 用户登录验证
     * @param username 用户名
     * @param password 明文密码
     * @return 验证成功返回User对象，失败返回null
     */
    User login(String username, String password);

    /**
     * 检查用户名是否可用
     * @param username 用户名
     * @return 可用返回true，已存在返回false
     */
    boolean isUsernameAvailable(String username);
}