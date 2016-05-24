package com.wwb.demo.utils;

import com.wwb.demo.utils.result.ResultResponse;
import com.wwb.demo.utils.result.enums.ResultResponseCode;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ai on 2016/5/17.
 */
public class ValidateFieldUtil {
    //6-18位的英文数字以及下划线
    public static ResultResponse userNameValidation(String userName) {

        if (StringUtils.isBlank(userName)) {
            return new ResultResponse(ResultResponseCode.USERNAME_CANNOT_NULL, false);
        }
        int length = userName.length();
        if (length > 16 || length < 6) {
            return new ResultResponse(ResultResponseCode.USERNAME_LENGTH_ERROR, false);
        }
        Pattern pattern = Pattern.compile("^[0-9a-zA-Z_]{1,}$");
        Matcher matcher = pattern.matcher("userName");
        if (!matcher.matches()) {
            new ResultResponse(ResultResponseCode.USERNAME_CHARACTER_ERROR, false);
        }
        return new ResultResponse(ResultResponseCode.SUCCESS, true);
    }

    //密码长度6-16位字符
    public static ResultResponse passwordValidation(String userName) {
        if (StringUtils.isBlank(userName)) {
            return new ResultResponse(ResultResponseCode.PASSWORD_CANNOT_NULL, false);
        }
        int length = userName.length();
        if (length > 16 || length < 6) {
            return new ResultResponse(ResultResponseCode.PASSWORD_LENGTH_ERROR, false);
        }
        return new ResultResponse(ResultResponseCode.SUCCESS, true);
    }


    //验证email
    public static ResultResponse emailValidation(String email) {
        if (StringUtils.isBlank(email)) {
            return new ResultResponse(ResultResponseCode.EMAIL_CANNOT_NULL, false);
        }
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher m = p.matcher(email);

        if (!m.matches()) {
            return new ResultResponse(ResultResponseCode.EMAIL_FORMAT_ERROR, false);
        }
        return new ResultResponse(ResultResponseCode.SUCCESS, true);
    }


    /**
     * https://github.com/VincentSit/ChinaMobilePhoneNumberRegex 上面的电话号码验证
     */

    public static ResultResponse mobileValidation(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return new ResultResponse(ResultResponseCode.MOBILE_CANNOT_NULL, false);
        }
        Pattern p = Pattern.compile("(^(13\\d|15[^4,\\D]|17[13678]|18\\d)\\d{8}|170[^346,\\D]\\d{7})$");
        Matcher m = p.matcher(mobile);
        if (!m.matches()) {
            return new ResultResponse(ResultResponseCode.MOBILE_FORMAT_ERROR, false);
        }
        return new ResultResponse(ResultResponseCode.SUCCESS, true);
    }


}
