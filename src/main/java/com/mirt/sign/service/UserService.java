package com.mirt.sign.service;

import com.mirt.sign.model.User;


/**
 * @author Mirt
 * @date 18-6-7.
 */
public interface UserService {

    /**
     * 注册用户信息
     *
     * @param user 待注册用户
     * @return 返回结果
     */
    void registerUser(User user);

    /**
     * 根据用户邮箱获取用户信息
     *
     * @param email 用户名
     * @return 用户信息
     */
    User getUserInfoByEmail(String email);

    /**
     * 更新用户密码
     *
     * @param user 含有用户密码信息和id
     * @return 是否成功
     */
    void updateUserPassword(User user);

    /**
     * 验证用户登录信息是否正确（邮箱，密码）
     *
     * @param user 用户信息
     * @return true if user exist
     */
    boolean checkUserPwd(User user);
}
