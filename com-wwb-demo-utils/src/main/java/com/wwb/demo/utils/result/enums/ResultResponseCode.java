package com.wwb.demo.utils.result.enums;

public enum ResultResponseCode {

    SUCCESS("0000000", "成功!!!"),
    USERNAME_PASSWORD_ERROR("0000001", "用户名或者密码错误!!!"),
    VERIFY_CODE_ERROR("0000002", "验证码不正确!!!"),
    USERNAME_IS_NOT_AVAILABLE("0000003", "用户名已经存在!!!"),
    USERNAME_CANNOT_NULL("0000004", "用户名不能为空!!!"),
    USERNAME_LENGTH_ERROR("0000005", "用户名为6-18个字符!!!"),
    USERNAME_CHARACTER_ERROR("0000006", "只能为英文字符、数字或者下划线!!!"),
    PASSWORD_CANNOT_NULL("0000004", "密码不能为空!!!"),
    PASSWORD_LENGTH_ERROR("0000005", "密码为6-16个字符!!!"),
    EMAIL_CANNOT_NULL("0000006", "email长度不能为空!!!"),
    EMAIL_FORMAT_ERROR("0000007", "email的格式不对!!!"),
    EMAIL_IS_NOT_AVAILABLE("0000008", "email已经注册!!!"),
    MOBILE_CANNOT_NULL("0000009", "手机号码不能为空!!!"),
    MOBILE_FORMAT_ERROR("0000010", "手机号码格式不对!!!"),
    MOBILE_IS_NOT_AVAILABLE("0000011", "手机的号码已经注册!!"),
    CODE_IS_NOT_SAME("0000012", "验证码不正确!!"),
    PASSWORD_IS_NOT_SAME("0000013", "密码前后不一致!!"),
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
