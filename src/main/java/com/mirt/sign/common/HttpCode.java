package com.mirt.sign.common;

/**
 * 返回的状态码以及描述
 *
 * @authur Zhang Yuqi
 * @create 2018/4/11.
 */
public enum HttpCode {
    SUCCESS(200, "success"),
    NO_AUTHORIZATION(401, "无权限"),
    ERROR(400, "Bad Request"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "无此资源"),
    SERVERS_ERROR(500, "服务器问题");

    public int code;
    public String description;

    HttpCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
