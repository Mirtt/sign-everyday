package com.mirt.sign.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回结果通用类
 *
 * @authur Zhang Yuqi
 * @create 2018/4/11.
 */
@Data
public class ResultJson<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int httpCode = HttpCode.SUCCESS.code;

    private T data;

    private String msg = HttpCode.SUCCESS.description;

    private String dataType;

    public ResultJson() {
        super();
    }

    public ResultJson(T data) {
        super();
        this.data = data;
        this.dataType = data.getClass().getSimpleName();
    }

    public ResultJson(HttpCode httpCode, String msg) {
        super();
        this.msg = msg;
        this.httpCode = httpCode.code;
    }

    public ResultJson(HttpCode httpCode, T data, String msg) {
        super();
        this.httpCode = httpCode.code;
        this.data = data;
        this.msg = msg;
        this.dataType = data.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "\nstatus:" + this.httpCode + "\ndata:" + data + "\nmsg:" + msg;
    }
}
