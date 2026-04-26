package org.example.ssm.controller;

import org.example.ssm.entity.User;
import org.example.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

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

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();

        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            result.put("code", 400);
            result.put("msg", "用户名和密码不能为空");
            return result;
        }

        User loginUser = userService.login(user.getUsername(), user.getPassword());
        if (loginUser != null) {
            result.put("code", 200);
            result.put("msg", "登录成功");
            result.put("data", loginUser);
        } else {
            result.put("code", 401);
            result.put("msg", "用户名或密码错误");
        }
        return result;
    }

    @GetMapping("/check")
    public Map<String, Object> checkUsername(@RequestParam String username) {
        Map<String, Object> result = new HashMap<>();
        result.put("available", userService.isUsernameAvailable(username));
        return result;
    }
}