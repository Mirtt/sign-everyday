package com.mirt.sign.controller;

import com.mirt.sign.common.HttpCode;
import com.mirt.sign.common.ResultJson;
import com.mirt.sign.model.User;
import com.mirt.sign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 用于用户登录登出
 *
 * @author Mirt Zhang
 * @date 2018/8/30
 */
@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResultJson<String> login(HttpServletRequest request, @RequestBody User user) {
        // 确认登录用户的账号密码是否正确
        boolean isUserExist = userService.checkUserPwd(user);
        if (isUserExist) {
            // 用户session存储到redis中
            HttpSession session = request.getSession();
            session.setAttribute("user-name", user.getUserName());
            session.setAttribute("user-email", user.getEmail());
            return new ResultJson<>();
        }
        return new ResultJson<>(HttpCode.ERROR, "登录失败");
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        // todo 注销用户session
        HttpSession session = request.getSession();
        session.invalidate();
        // todo 页面重定向到登录页面
    }


    @GetMapping("/session")
    public ResultJson<Object> getCurrentSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return new ResultJson<>(HttpCode.SUCCESS, session.getAttribute("user-email"), session.getId());
    }
}
