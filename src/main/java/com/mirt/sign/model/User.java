package com.mirt.sign.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 用户类
 *
 * @authur Zhang Yuqi
 * @create 2018/4/11.
 */
@Data
@Getter
@Setter
public class User {

    private long userId;
    private String userName;
    private String phone;
    private String email;
    private String password;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
