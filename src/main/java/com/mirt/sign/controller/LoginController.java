package com.mirt.sign.controller;

import com.mirt.sign.common.HttpCode;
import com.mirt.sign.common.ResultJson;
import com.mirt.sign.model.User;
import com.mirt.sign.service.UserService;
import com.mirt.sign.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * 用于用户登录登出
 *
 * @author Mirt Zhang
 * @date 2018/8/30
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(HttpServletRequest request, User user, Model model) {
        // 确认登录用户的账号密码是否正确
        boolean isUserExist = userService.checkUserPwd(user);
        if (isUserExist) {
            // 用户session存储到redis中
            HttpSession session = request.getSession();
            session.setAttribute("user-email", user.getEmail());
            return "mainPage";
        }
        model.addAttribute("msg", "登录失败");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // 注销用户session
        HttpSession session = request.getSession();
        session.invalidate();
        // 页面重定向到登录页面
        return "redirect:login";
    }

    @GetMapping("/auth")
    public void auth(HttpServletRequest request, HttpServletResponse response) {
        // 如果已经有jwt且没有过期则不生成新的jwt
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if ("token".equalsIgnoreCase(c.getName())) {
                String jwt = c.getValue();
                Claims claims = JwtUtil.parseJwt(jwt);
                if (Objects.nonNull(claims)) {
                    return;
                }
            }
        }
        // 生成jwt
        HttpSession session = request.getSession();
        String email = String.valueOf(session.getAttribute("user-email"));
        User user = userService.getUserInfoByEmail(email);
        String jwt = JwtUtil.createJwt(user);
        // jwt加入到cookie中
        Cookie cookie = new Cookie("token", jwt);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }


    @GetMapping("/session")
    public ResultJson<Object> getCurrentSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return new ResultJson<>(HttpCode.SUCCESS, session.getAttribute("user-email"), session.getId());
    }
}
