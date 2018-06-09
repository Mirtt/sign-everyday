package com.mirt.sign.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * 用户类
 *
 * @authur Zhang Yuqi
 * @create 2018/4/11.
 */
@Data
public class User {

    private Long userId;
    @NotNull
    @Size(min = 2,max = 8,message = "username need to between 2 to 8")
    private String userName;
    @NotNull
    @Pattern(regexp = "^1\\d{10}$",message = "invalid phone number")
    private String phone;
    @Email(message = "incorrect email address")
    private String email;
    @NotNull
    @Size(min = 6,max = 12,message = "password need to between 6 to 12")
    private String password;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
