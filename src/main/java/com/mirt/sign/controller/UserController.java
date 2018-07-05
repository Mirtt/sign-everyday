package com.mirt.sign.controller;

import com.mirt.sign.common.ResultJson;
import com.mirt.sign.model.User;
import com.mirt.sign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关操作接口
 *
 * @authur Zhang Yuqi
 * @create 2018/7/4.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResultJson<User> getUserInfoByName(@RequestParam("userName") String userName) {
        return userService.getUserInfoByName(userName);
    }
}
