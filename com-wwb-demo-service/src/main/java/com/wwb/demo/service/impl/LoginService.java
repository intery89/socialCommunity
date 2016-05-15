package com.wwb.demo.service.impl;

import com.wwb.demo.domain.model.Member;
import com.wwb.demo.utils.CipherUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Intery on 2016/5/15.
 */
public class LoginService {

    @Autowired
    MemberDao memberDao;

    public String decipher(String password){
        return CipherUtils.convertMD5(CipherUtils.convertMD5(password));
    }

    public boolean checkUserIdentification(String username, String password){
        Member member = memberDao.find(username);
        if(member != null) {
            String pwd = member.getPassword();
            if (pwd.equals(decipher(password))) {
                return true;
            }
        }
        return false;
    }

}
