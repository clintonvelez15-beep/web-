-- ========================================================
-- SSM Demo Database Schema
-- Database: ssm_demo
-- Target: MySQL 8.0+
-- ========================================================

DROP DATABASE IF EXISTS ssm_demo;

CREATE DATABASE ssm_demo
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE ssm_demo;

-- --------------------------------------------------------
-- Table: user
-- --------------------------------------------------------
CREATE TABLE user (
    id          INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    username    VARCHAR(50)  NOT NULL UNIQUE COMMENT '用户名',
    password    VARCHAR(255) NOT NULL COMMENT '密码（MD5加密）',
    email       VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
    create_time DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = '用户表';

-- --------------------------------------------------------
-- Sample Data
-- Passwords are MD5 hashes of the plaintext password '123456'
-- --------------------------------------------------------
INSERT INTO user (username, password, email)
VALUES ('admin', 'e10adc3949ba59abbe56e057f20f883e', 'admin@example.com'),
       ('test', 'e10adc3949ba59abbe56e057f20f883e', 'test@example.com');
