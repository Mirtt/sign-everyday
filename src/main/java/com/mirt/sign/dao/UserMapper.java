package com.mirt.sign.dao;

import com.mirt.sign.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * user相关dao层操作
 *
 * @authur Zhang Yuqi
 * @create 2018/7/4.
 */
@CacheConfig(cacheNames = "user")
@Repository
public interface UserMapper {

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


    @Insert("insert into user " +
            "(user_id,user_name,phone,email,password,create_time,update_time)" +
            "value " +
            "(#{userId},#{userName},#{phone},#{email}," +
            "#{password},#{createTime},#{updateTime})")
    @CachePut(key = "#p0.userName")
    void insertUser(User user);

}
