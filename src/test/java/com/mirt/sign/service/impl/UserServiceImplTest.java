package com.mirt.sign.service.impl;

import com.mirt.sign.SignEverydayApplication;
import com.mirt.sign.model.User;
import com.mirt.sign.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Mirt
 * @date 2018/7/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SignEverydayApplication.class)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    @Rollback
    public void registerUser() {
        User user = new User();
        user.setUserName("xxx");
        user.setPassword("xxx");
        user.setEmail("123@123.com");
        userService.registerUser(user);
    }
}