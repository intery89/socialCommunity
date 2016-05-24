package com.wwb.demo.service.impl;

import com.wwb.demo.dao.MemberDao;
import com.wwb.demo.domain.model.Member;
import com.wwb.demo.service.vo.UserVo;
import com.wwb.demo.utils.CipherUtils;
import com.wwb.demo.utils.ValidateFieldUtil;
import com.wwb.demo.utils.result.ResultResponse;
import com.wwb.demo.utils.result.enums.ResultResponseCode;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Intery on 2016/5/15.
 */
public class LoginService {

    @Autowired
    private MemberDao memberDao;

    public String decipher(String password) {
        return CipherUtils.convertMD5(CipherUtils.convertMD5(password));
    }

    public ResultResponse checkUserIdentification(String username, String password) {
        ResultResponse resultResponse = null;
        try {
            Member member = memberDao.getMemberByUserName(username);
            if (member != null) {
                String pwd = member.getPassword();
                if (pwd.equals(decipher(password))) {
                    resultResponse = new ResultResponse(ResultResponseCode.SUCCESS, true);
                }
            } else {
                resultResponse = new ResultResponse(ResultResponseCode.USERNAME_PASSWORD_ERROR, false);
            }
        } catch (Exception e) {
            resultResponse = new ResultResponse(ResultResponseCode.USERNAME_PASSWORD_ERROR, false);
        }
        return resultResponse;
    }

    public ResultResponse isUserNameAvailable(String userName) {
        ResultResponse resultResponse = null;
        try {
            boolean isNameExist = memberDao.isUserNameExist(userName);
            if (!isNameExist) {
                return new ResultResponse(ResultResponseCode.USERNAME_PASSWORD_ERROR, false);
            }
            return ValidateFieldUtil.userNameValidation(userName);

        } catch (Exception e) {
            return new ResultResponse(ResultResponseCode.USERNAME_PASSWORD_ERROR, false);
        }
    }

    public ResultResponse userRegister(UserVo userVo) {
        ResultResponse resultResponse = ValidateFieldUtil.userNameValidation(userVo.getUserName());
        if (!resultResponse.isSuccess()) {
            return resultResponse;
        }
        resultResponse = ValidateFieldUtil.passwordValidation(userVo.getPassword());
        if (!resultResponse.isSuccess()) {
            return resultResponse;
        }
        Member member = new Member();
        try {
            BeanUtils.copyProperties(member, userVo);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        memberDao.create(member);
        return new ResultResponse(ResultResponseCode.SUCCESS, true);
    }

}


