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
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Intery on 2016/5/15.
 */
@Service
public class LoginService {

    @Autowired
    private MemberDao memberDao;

    public ResultResponse checkUserIdentification(String username, String password) {
        ResultResponse resultResponse = null;
        try {
            Member member = memberDao.getMemberByUserName(username);
            if (member != null) {
                String pwd = member.getPassword();
                if (pwd.equals(CipherUtils.convertMD5(password))) {
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


