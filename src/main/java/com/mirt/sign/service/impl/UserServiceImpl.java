package com.mirt.sign.service.impl;

import com.mirt.sign.dao.UserMapper;
import com.mirt.sign.model.User;
import com.mirt.sign.service.UserService;
import com.mirt.sign.util.IdGenerator;
import com.mirt.sign.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Mirt
 * @date 18-6-7.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerUser(User user) {
        user.setUserId(IdGenerator.nextId());
        user.setUpdateTime(LocalDateTime.now());
        user.setCreateTime(LocalDateTime.now());
        user.setPassword(Md5Util.encode(user.getPassword()));
        //insert user into MySql
        userMapper.insertUser(user);
    }

    @Override
    public User getUserInfoByEmail(String email) {
        User u = new User();
        u.setEmail(email);
        return Optional.ofNullable(userMapper.getUserByEmail(u)).orElse(new User());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserPassword(User user) {
        user.setPassword(Md5Util.encode(user.getPassword()));
        userMapper.updateUserPassword(user);
    }

    @Override
    public boolean checkUserPwd(User user) {
        if (Objects.isNull(user))
            return false;
        User u = userMapper.getUserByEmail(user);
        if (Objects.isNull(u))
            return false;
        String inputPwd = user.getPassword();
        String actualPwd = u.getPassword();
        if (Objects.isNull(inputPwd) || Objects.isNull(actualPwd))
            return false;
        return Objects.equals(Md5Util.encode(inputPwd), actualPwd);
    }
}
