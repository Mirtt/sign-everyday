package com.mirt.sign.service.impl;

import com.mirt.sign.common.HttpCode;
import com.mirt.sign.common.ResultJson;
import com.mirt.sign.model.User;
import com.mirt.sign.service.UserService;
import com.mirt.sign.util.IdGenerateUtil;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @authur zyq
 * @create 18-6-7.
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    @Transient
    public ResultJson<Map<String, Object>> registerUser(User user) {
        user.setUserId(IdGenerateUtil.generateId());
        user.setUpdateTime(LocalDateTime.now());
        user.setCreateTime(LocalDateTime.now());
        //todo insert user into MySql

        ResultJson<Map<String,Object>> result = new ResultJson<>();
        result.setHttpCode(HttpCode.SUCCESS.code);
        result.setMsg("注册成功");
        Map<String,Object> resultData = new HashMap<>();
        //todo complete response data
        resultData.put("userName",user.getUserName());
        resultData.put("phone",user.getPhone());
        result.setData(resultData);
        return result;
    }
}
