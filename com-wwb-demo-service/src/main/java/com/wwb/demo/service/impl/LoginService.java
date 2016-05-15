package com.wwb.demo.service.impl;

import com.wwb.demo.dao.MemberDao;
import com.wwb.demo.domain.model.Member;
import com.wwb.demo.utils.CipherUtils;
import com.wwb.demo.utils.result.ResultResponse;
import com.wwb.demo.utils.result.enums.ResultResponseCode;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Intery on 2016/5/15.
 */
public class LoginService {

    @Autowired
    MemberDao memberDao;

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
}
