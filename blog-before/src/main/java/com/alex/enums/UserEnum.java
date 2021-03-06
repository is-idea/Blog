package com.alex.enums;

import lombok.Getter;

/**
 * 用户状态
 *
 * @Author: Alex isidea@outlook.com
 * @Date: 2018/4/27 0027 15:58
 */
@Getter
public enum UserEnum {

    USER_STATE_WARING(0,"账户未激活"),
    USER_STATE_SUCCESS(1,"账户正常"),
    USER_STATE_ERROR(2,"账户封禁"),
    USER_ERROR(3,"账户不存在"),
    USER_PASSWORD_ERROR(4,"密码错误"),
    USER_LOGIN_SUCCESS(200,"登陆成功"),
    USER_REGISTER_ID(300,"USER_REGISTER_ID");
    ;

    private int code;

    private String message;

    UserEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
