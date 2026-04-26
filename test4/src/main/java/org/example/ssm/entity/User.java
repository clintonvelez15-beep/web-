package org.example.ssm.entity;

import java.time.LocalDateTime;

/**
 * 用户实体类 - 对应数据库user表
 */
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createTime;

    // Getter和Setter（IDEA自动生成：Alt+Insert）
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }

    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + '\'' +
                ", email='" + email + '\'' + '}';
    }
}