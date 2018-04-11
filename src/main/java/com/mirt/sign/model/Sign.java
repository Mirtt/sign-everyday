package com.mirt.sign.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 签到功能实体
 *
 * @authur Zhang Yuqi
 * @create 2018/4/11.
 */
@Getter
@Setter
public class Sign {

    private long id;
    private User user;
    private String signLocation;
    private LocalDate signDate;
    private LocalTime signTime;

}
