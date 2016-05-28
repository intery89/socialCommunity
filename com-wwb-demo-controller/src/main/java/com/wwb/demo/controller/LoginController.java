package com.wwb.demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

    private static final String pic = "/static/code/";
    private static final Map<String, String> mockCache = new HashMap<String, String>();
    
    @Autowired
    LoginService loginService;

    @RequestMapping("/hello")
    public String hello(){
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

    @ResponseBody
    @RequestMapping(value = "/generateValidateCode")
    public Map<String, String> generateValidationCode(HttpSession session) {
    	String realPath = session.getServletContext().getRealPath("/");
        Map<String, String> result = new HashMap<String, String>();
        try {
            Map<String, String> codeInfo = ValidationCodeGenerator.generateCode(realPath + pic);
            Map<String, String> code = new HashMap<String, String>();
            code.put("code", codeInfo.get("code"));
            code.put("timestamp", String.valueOf(System.currentTimeMillis()));
            session.setAttribute("code", code);
            result.put("path", pic + codeInfo.get("picName") + ".jpg");
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            session.setAttribute("code", "");
            String path = pic + "default.jpg";
            result.put("path", path);
            return result;
        }
    }

    @RequestMapping(value = "/checkValidateCode")
    @ResponseBody
    public Map<String, String> generateValidationCode(@RequestParam String validateCode,HttpSession session) {
        Map<String, String> result = new HashMap<String, String>();
        try {
        	Map<String, String> code = null;
        	if(session.getAttribute("code") instanceof Map){
        		code = (Map<String, String>) session.getAttribute("code");
        	}
        	if(code != null){
        		long now = System.currentTimeMillis();
        		long isExpired = now - Long.parseLong(code.get("timestamp"));
        		// 5 mins expired
        		if(isExpired > 300000){
        			code = null;
        			result.put("result", "expired");
        			return result;
        		}
        	}
        	
            if (code.get("code").equalsIgnoreCase(validateCode)) {
                result.put("result", "success");
                return result;
            }
            result.put("result", "false");
            return result;
        } catch (Exception e) {
        	e.printStackTrace();
            result.put("result", "false");
            return result;
        }
    }
}
