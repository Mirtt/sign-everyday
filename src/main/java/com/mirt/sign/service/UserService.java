package com.mirt.sign.service;

import com.mirt.sign.common.ResultJson;
import com.mirt.sign.model.User;

import java.util.Map;

;

/**
 * @authur zyq
 * @create 18-6-7.
 */
public interface UserService {

    ResultJson<Map<String, Object>> registerUser(User user);

    ResultJson<User> getUserInfoByName(String userName);
}
