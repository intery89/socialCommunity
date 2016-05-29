package com.wwb.demo.service.impl;

import com.wwb.demo.dao.MemberDao;
import com.wwb.demo.domain.model.Member;
import com.wwb.demo.service.vo.UserVo;
import com.wwb.demo.utils.ValidateFieldUtil;
import com.wwb.demo.utils.result.ResultResponse;
import com.wwb.demo.utils.result.enums.ResultResponseCode;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by ai on 2016/5/29.
 */
@Service
public class RegisterService {
    protected final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private MemberDao memberDao;

    public ResultResponse isUserNameAvailable(String userName) {
        ResultResponse resultResponse = null;
        try {
            resultResponse = ValidateFieldUtil.userNameValidation(userName);
            if (!resultResponse.isSuccess()) {
                return resultResponse;
            }
            boolean isNameExist = memberDao.isUserNameExist(userName);
            if (isNameExist) {
                return new ResultResponse(ResultResponseCode.USERNAME_IS_NOT_AVAILABLE, false);
            }
            return resultResponse;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return new ResultResponse(ResultResponseCode.SYSTEM_ERROR, false);
        }
    }

    public ResultResponse isEmailAvailable(String email) {
        ResultResponse resultResponse = null;
        try {
            resultResponse = ValidateFieldUtil.emailValidation(email);
            if (!resultResponse.isSuccess()) {
                return resultResponse;
            }
            boolean isEmailExist = memberDao.isEmailExist(email);
            if (isEmailExist) {
                return new ResultResponse(ResultResponseCode.USERNAME_IS_NOT_AVAILABLE, false);
            }
            return resultResponse;
        } catch (Exception e) {
            return new ResultResponse(ResultResponseCode.SYSTEM_ERROR, false);
        }
    }

    @Transactional
    public ResultResponse userRegister(UserVo userVo) {
        Member member = new Member();
        try {
            BeanUtils.copyProperties(member, userVo);
            memberDao.create(member);
        } catch (Exception e) {
            logger.error(e.toString(),e);
            return new ResultResponse(ResultResponseCode.SYSTEM_ERROR, false);
        }
        return new ResultResponse(ResultResponseCode.SUCCESS, true);
    }

}
