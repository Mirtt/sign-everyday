package com.mirt.sign.controller;

import com.mirt.sign.common.HttpCode;
import com.mirt.sign.common.ResultJson;
import com.mirt.sign.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/user")
    public ResultJson register(@RequestBody User user){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator v  = validatorFactory.getValidator();
        Set<ConstraintViolation<User>> errorSet = v.validate(user);
        if (!errorSet.isEmpty()){
            String[] errMsg = new String[errorSet.size()];
            int i = 0;
            for (ConstraintViolation<User> c:errorSet){
                errMsg[i++] = c.getMessage();
            }
            return new ResultJson(HttpCode.ERROR,String.join(", ",errMsg));
        }
        return new ResultJson("no error");
    }
}
