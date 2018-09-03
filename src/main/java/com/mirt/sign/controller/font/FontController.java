package com.mirt.sign.controller.font;

import com.mirt.sign.util.CodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 前段路由
 *
 * @author zyq
 * @date 18-9-2.
 */
@Controller
public class FontController {

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(HttpServletRequest request, Model model) {
        String code = CodeUtil.generateCode();
        HttpSession session = request.getSession();
        session.setAttribute("code", code);
        model.addAttribute("code", code);
        return "register";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }

    @GetMapping("/fail")
    public String fail() {
        return "fail";
    }
}
