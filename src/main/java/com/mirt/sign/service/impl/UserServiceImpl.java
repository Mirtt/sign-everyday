package com.mirt.sign.service.impl;

import com.mirt.sign.common.HttpCode;
import com.mirt.sign.common.ResultJson;
import com.mirt.sign.dao.UserMapper;
import com.mirt.sign.model.User;
import com.mirt.sign.service.UserService;
import com.mirt.sign.util.IdGenerator;
import com.mirt.sign.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @authur zyq
 * @create 18-6-7.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public ResultJson<Map<String, Object>> registerUser(User user) {
        user.setUserId(IdGenerator.nextId());
        user.setUpdateTime(LocalDateTime.now());
        user.setCreateTime(LocalDateTime.now());
        user.setPassword(MD5Util.encode(user.getPassword()));
        //insert user into MySql
        userMapper.insertUser(user);
        ResultJson<Map<String,Object>> result = new ResultJson<>();
        result.setHttpCode(HttpCode.SUCCESS.code);
        result.setMsg("注册成功");
        Map<String,Object> resultData = new HashMap<>();
        //complete response data
        resultData.put("userName",user.getUserName());
        resultData.put("phone",user.getPhone());
        result.setData(resultData);
        result.setDataType(user.getClass().getSimpleName());
        return result;
    }

    @Override
    public ResultJson<User> getUserInfoByName(String userName) {
        User user = Optional.ofNullable(userMapper.getUserByUserName(userName)).orElse(new User());
        return new ResultJson<>(user);
    }
}
