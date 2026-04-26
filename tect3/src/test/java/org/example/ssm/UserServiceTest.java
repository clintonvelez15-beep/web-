package org.example.ssm;

import org.example.ssm.entity.User;
import org.example.ssm.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testRegister() {
        User user = new User();
        user.setUsername("testUser" + System.currentTimeMillis()); // 确保唯一
        user.setPassword("123456");
        user.setEmail("test@test.com");

        boolean result = userService.register(user);
        assertTrue("注册应该成功", result);
        System.out.println("注册用户ID: " + user.getId());
    }

    @Test
    public void testLoginSuccess() {
        // 先确保admin存在（已在SQL中插入）
        User user = userService.login("admin", "123456");
        assertNotNull("登录应该成功", user);
        assertEquals("admin", user.getUsername());
    }

    @Test
    public void testLoginFail() {
        User user = userService.login("admin", "wrongpassword");
        assertNull("密码错误应该返回null", user);

        User user2 = userService.login("notexist", "123456");
        assertNull("用户不存在应该返回null", user2);
    }
}