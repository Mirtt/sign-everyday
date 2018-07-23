package com.mirt.sign.controller;

import com.mirt.sign.common.HttpCode;
import com.mirt.sign.common.ResultJson;
import com.mirt.sign.common.validation.ValidationInsert;
import com.mirt.sign.model.User;
import com.mirt.sign.service.UserService;
import com.mirt.sign.util.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.groups.Default;
import java.util.Map;

/**
 * 注册用户接口
 *
 * @author Mirt
 * @date 18-6-5.
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    /**
     * 注册用户要求验证码与session中验证码一致，并且填写相应信息
     *
     * @param request
     * @param user
     * @param code
     * @return
     */
    @PostMapping("/user")
    public ResultJson register(
            HttpServletRequest request,
            @RequestBody @Validated({ValidationInsert.class, Default.class}) User user,
            @RequestParam("code") String code
    ) {
        HttpSession session = request.getSession();
        if (session.getAttribute("code") == null) {
            return new ResultJson(HttpCode.ERROR, "无验证码信息");
        }
        if (!code.equals(session.getAttribute("code"))) {
            return new ResultJson(HttpCode.ERROR, "验证码填写错误");
        }
        // 通过验证后对用户进行注册
        ResultJson<Map<String, Object>> result;
        try {
            result = userService.registerUser(user);
        } catch (DataAccessException e) {
            return new ResultJson(HttpCode.ERROR, e.getMessage());
        }
        return result;
    }

    /**
     * 注册的时候 session中存入验证码信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/generateCode")
    public ResultJson generateCode(HttpServletRequest request) {
        String code = CodeUtil.generateCode();
        HttpSession session = request.getSession();
        session.setAttribute("code", code);
        return new ResultJson<>(code);
    }
}
