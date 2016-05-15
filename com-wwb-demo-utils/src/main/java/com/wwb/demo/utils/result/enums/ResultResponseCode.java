package com.wwb.demo.utils.result.enums;

public enum ResultResponseCode {

    SUCCESS("0000000", "成功！"),
    USERNAME_PASSWORD_ERROR("0000001", "用户名或者密码错误"),
    VERIFY_CODE_ERROR("0000002", "验证码不正确"),


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
