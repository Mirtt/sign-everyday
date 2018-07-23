package com.mirt.sign.dao;

import com.mirt.sign.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * user相关dao层操作
 *
 * @author Mirt
 * @date 2018/7/4.
 */
@CacheConfig(cacheNames = "user")
@Repository
public interface UserMapper {

    /**
     * 根据用户名获取用户信息
     *
     * @param userName 用户名
     * @return 该用户信息
     */
    @Results(id = "userResult", value = {
            @Result(column = "user_id", property = "userId"),
            @Result(column = "user_name", property = "userName"),
            @Result(column = "phone", property = "phone"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Select("select * from user where user_name = #{userName}")
    @Cacheable(key = "#p0")
    User getUserByUserName(String userName);


    /**
     * 向数据库中加入用户信息
     *
     * @param user 待加入用户
     */
    @Insert("insert into user " +
            "(user_id,user_name,phone,email,password,create_time,update_time)" +
            "value " +
            "(#{userId},#{userName},#{phone},#{email}," +
            "#{password},#{createTime},#{updateTime})")
    @CachePut(key = "#p0.userName")
    void insertUser(User user);

    /**
     * 更新用户密码
     *
     * @param user 含有用户密码和id信息
     */
    @Update("update user set password = #{password} where user_id = #{userId}")
    @CacheEvict(key = "#p0.userName")
    void updateUserPassword(User user);
}
