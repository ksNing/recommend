package com.example.recommend.Enum;

import lombok.Getter;

@Getter
public enum UserEnum {
    LOGIN_SUCCESS(1,"登录成功"),
    LOGIN_ERROR(2,"用户名或密码不正确");
    private Integer code;
    private String msg;

    UserEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
