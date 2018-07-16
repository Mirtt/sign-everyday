package com.mirt.sign.controller;

import com.mirt.sign.common.HttpCode;
import com.mirt.sign.common.ResultJson;
import com.mirt.sign.common.ValidationUpdate;
import com.mirt.sign.model.User;
import com.mirt.sign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.Objects;

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

    @PostMapping("/{userId}")
    public ResultJson<User> updateUserPassword(@RequestBody @Validated({ValidationUpdate.class, Default.class}) User user, @PathVariable("userId") Long userId) {
        if (Objects.isNull(user.getUserId()) || !userId.equals(user.getUserId()))
            return new ResultJson<>(HttpCode.ERROR, "用户数据错误");
        return userService.updateUserPassword(user);
    }
}
