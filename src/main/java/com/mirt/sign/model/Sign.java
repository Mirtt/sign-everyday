package com.mirt.sign.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 签到功能实体
 *
 * @authur Zhang Yuqi
 * @create 2018/4/11.
 */
@Data
public class Sign {

    private long id;
    @NotNull
    private User user;

    private String signLocation;
    private LocalDate signDate;
    private LocalTime signTime;

}
