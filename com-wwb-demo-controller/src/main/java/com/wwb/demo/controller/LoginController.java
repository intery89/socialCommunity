package com.wwb.demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.wwb.demo.service.impl.RegisterService;
import com.wwb.demo.utils.ValidateFieldUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wwb.demo.domain.model.LoginForm;
import com.wwb.demo.service.impl.LoginService;
import com.wwb.demo.utils.ValidationCodeGenerator;
import com.wwb.demo.utils.result.ResultResponse;
import com.wwb.demo.utils.result.enums.ResultResponseCode;

/**
 * Created by Intery on 2016/5/15.
 */
@Controller
public class LoginController {


    private static final Map<String, String> mockCache = new HashMap<String, String>();

    @Autowired
    private LoginService loginService;



    @RequestMapping("/frontPage")
    public String hello() {
        return "register";
    }




    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse login(@RequestBody LoginForm user, HttpSession session) {
        String username = user.getUsername();
        String password = user.getPassword();

        ResultResponse resultResponse = loginService.checkUserIdentification(username, password);

        if (resultResponse.getStatus().getCode().equals(ResultResponseCode.SUCCESS.getCode())) {
            //需要将member的相关数据放入到session中
            return resultResponse;
        } else if (resultResponse.getStatus().getCode().equals(ResultResponseCode.USERNAME_PASSWORD_ERROR.getCode())) {
            return resultResponse;
        } else {
            //抛出异常或者是其他错误
            return resultResponse;
        }
    }
}
