package com.mirt.sign.model;

import com.mirt.sign.common.validation.ValidationInsert;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户类
 *
 * @author Mirt
 * @date 2018/4/11.
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 4737829251573179541L;

    private Long userId;
    @NotNull
    @Size(min = 2, max = 8, message = "username need to between 2 to 8")
    private String userName;
    @Pattern(regexp = "^1\\d{10}$", message = "invalid phone number")
    private String phone;
    @NotNull
    @Email(message = "incorrect email address", groups = ValidationInsert.class)
    private String email;
    @NotNull
    @Size(min = 6, max = 12, message = "password need to between 6 to 12")
    private String password;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
