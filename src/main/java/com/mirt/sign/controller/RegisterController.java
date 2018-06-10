package com.mirt.sign.controller;

import com.mirt.sign.common.CodeUtil;
import com.mirt.sign.common.HttpCode;
import com.mirt.sign.common.ResultJson;
import com.mirt.sign.model.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 注册用户接口
 *
 * @authur zyq
 * @create 18-6-5.
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    /**
     * 注册用户要求验证码与session中验证码一致，并且填写相应信息
     * @param request
     * @param user
     * @param code
     * @return
     */
    @PostMapping("/user")
    public ResultJson register(
            HttpServletRequest request,
            @RequestBody User user,
            @RequestParam("code")String code
    ) {
        HttpSession session = request.getSession();
        if (session.getAttribute("code") == null){
            return new ResultJson(HttpCode.ERROR,"无验证码信息");
        }
        if (!code.equals(session.getAttribute("code"))){
            return new ResultJson(HttpCode.ERROR,"验证码填写错误");
        }
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator v = validatorFactory.getValidator();
        Set<ConstraintViolation<User>> errorSet = v.validate(user);
        if (!errorSet.isEmpty()) {
            String[] errMsg = new String[errorSet.size()];
            int i = 0;
            for (ConstraintViolation<User> c : errorSet) {
                errMsg[i++] = c.getMessage();
            }
            return new ResultJson(HttpCode.ERROR, String.join(", ", errMsg));
        }
        //todo 通过验证后对用户进行注册
        return new ResultJson(HttpCode.SUCCESS,"注册成功");
    }

    /**
     * 注册的时候 session中存入验证码信息
     * @param request
     * @return
     */
    @RequestMapping("/generateCode")
    public ResultJson generateCode(HttpServletRequest request) {
        String code = CodeUtil.generateCode();
        HttpSession session = request.getSession();
        session.setAttribute("code",code);
        ResultJson<String> result = new ResultJson<>(code);
        return result;
    }

}
