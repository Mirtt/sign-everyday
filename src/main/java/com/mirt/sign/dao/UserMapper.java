package com.mirt.sign.dao;

import com.mirt.sign.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
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

    @Select("select * from user where user_name = #{userName}")
    //@Cacheable(key = "#userName")
    User getUserByUserName(String userName);


    @Insert("insert into user " +
            "(user_id,user_name,phone,email,password,create_time,update_time)" +
            "value " +
            "(#{userId},#{userName},#{phone},#{email}," +
            "#{password},#{createTime},#{updateTime})")
    @CachePut(key = "#p0.userName")
    void insertUser(User user);

}
