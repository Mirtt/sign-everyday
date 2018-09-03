package com.mirt.sign.controller.font;

import com.mirt.sign.util.CodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/")
    public String login() {
        return "login";
    }

    @RequestMapping("/register")
    public String register(HttpServletRequest request,Model model) {
        String code = CodeUtil.generateCode();
        HttpSession session = request.getSession();
        session.setAttribute("code", code);
        model.addAttribute("code", code);
        return "register";
    }
}
