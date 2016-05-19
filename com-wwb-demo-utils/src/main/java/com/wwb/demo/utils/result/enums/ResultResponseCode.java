package com.wwb.demo.utils.result.enums;

public enum ResultResponseCode {

    SUCCESS("0000000", "成功！"),
    USERNAME_PASSWORD_ERROR("0000001", "用户名或者密码错误"),
    VERIFY_CODE_ERROR("0000002", "验证码不正确"),
    USERNAME_IS_NOT_AVAILABLE("0000003", "用户名已经存在"),
    USERNAME_CANNOT_NULL("0000004", "用户名不能为空"),
    USERNAME_LENGTH_ERROR("0000005", "用户名为6-18个字符"),
    USERNAME_CHARACTER_ERROR("0000006", "只能为英文字符、数字或者下划线"),
    PASSWORD_CANNOT_NULL("0000004", "密码不能为空"),
    PASSWORD_LENGTH_ERROR("0000005", "密码为6-16个字符"),
    SYSTEM_ERROR("0000050", "系统错误");
    /**
     * 返回码
     */
    private String code;
    /**
     * 返回信息
     */
    private String msg;

    ResultResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ResultResponseCode getByCode(String code) {
        if (null == code) {
            return null;
        }
        for (ResultResponseCode responseCode : values()) {
            if (responseCode.code.equals(code)) {
                return responseCode;
            }
        }
        throw new IllegalArgumentException("No enum code '" + code + "'. " + ResultResponseCode.class);

    }
}
