package org.example.ssm.controller;

import org.example.ssm.entity.User;
import org.example.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制器 - 处理HTTP请求
 */
@RestController  // 标记为REST控制器，默认返回JSON
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册接口
     * POST /user/register
     * 请求体：{"username":"xxx", "password":"xxx", "email":"xxx"}
     */
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();

        boolean success = userService.register(user);
        if (success) {
            result.put("code", 200);
            result.put("msg", "注册成功");
            result.put("data", user.getUsername());
        } else {
            result.put("code", 400);
            result.put("msg", "用户名已存在");
        }
        return result;
    }

    /**
     * 登录接口
     * POST /user/login
     * 请求体：{"username":"xxx", "password":"xxx"}
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam String username,
                                     @RequestParam String password) {
        Map<String, Object> result = new HashMap<>();

        User user = userService.login(username, password);
        if (user != null) {
            result.put("code", 200);
            result.put("msg", "登录成功");
            result.put("data", user); // 返回用户信息（不含密码）
        } else {
            result.put("code", 401);
            result.put("msg", "用户名或密码错误");
        }
        return result;
    }

    /**
     * 检查用户名是否可用
     */
    @GetMapping("/check")
    public Map<String, Object> checkUsername(@RequestParam String username) {
        Map<String, Object> result = new HashMap<>();
        result.put("available", userService.isUsernameAvailable(username));
        return result;
    }
}