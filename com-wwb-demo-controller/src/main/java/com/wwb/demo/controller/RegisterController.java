package com.wwb.demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.wwb.demo.utils.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wwb.demo.service.impl.RegisterService;
import com.wwb.demo.service.vo.UserVo;
import com.wwb.demo.utils.result.ResultResponse;
import com.wwb.demo.utils.result.enums.ResultResponseCode;

@Controller
public class RegisterController {
    private static final String pic = "/static/code/";
    private static final String defaultEmail = "wwb20160528@163.com";
    
    @Autowired
    private RegisterService registerService;

    @RequestMapping("/register")
    @ResponseBody
    public ResultResponse register(@RequestBody Map<String, String> form, HttpSession session) {
        String username = form.get("userName");
        String password = form.get("password");
        String code = form.get("code");
        String email = form.get("email");
        String repeatPassword = form.get("repeatPassword");
        ResultResponse resultResponse = checkCode(code, session);
        if (!resultResponse.isSuccess()) {
            return resultResponse;
        }
        resultResponse = registerService.isUserNameAvailable(username);
        if (!resultResponse.isSuccess()) {
            return resultResponse;
        }
        resultResponse = registerService.isEmailAvailable(email);
        if (!resultResponse.isSuccess()) {
            return resultResponse;
        }
        resultResponse = ValidateFieldUtil.passwordValidation(password);
        if (!resultResponse.isSuccess()) {
            return resultResponse;
        }
        if(!password.equals(repeatPassword)){
            return new ResultResponse(ResultResponseCode.PASSWORD_IS_NOT_SAME, false);
        }
        UserVo userVo = new UserVo();
        userVo.setUserName(username);
        userVo.setEmail(email);
        userVo.setPassword(CipherUtils.convertMD5(password));
        resultResponse =  registerService.userRegister(userVo);
        if (!resultResponse.isSuccess()) {
            return resultResponse;
        }
        
        String randomCode = ValidationCodeGenerator.generateVerifyCode(5);
        CommonUtils.cache.put(username, randomCode);
        CommonUtils.codeCache.put(username+randomCode, System.currentTimeMillis());
        
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("code", randomCode);
        String EmailContent = CommonUtils.generateEmailContent(CommonUtils.generateEmailLink(CommonUtils.link, CommonUtils.getRequestParams(params)));
        
        MailUtils.send(email, "zhaoxiwang-register", EmailContent);
        String url= "loginsuccess";
        return new ResultResponse(ResultResponseCode.SUCCESS,url);
    }

    @RequestMapping("/loginsuccess")
    public String loginsuccess(@RequestParam String username, @RequestParam String code) {
    	if(CommonUtils.isValid(username, code)){
    		return "success";
    	}else{
    		return "reminder";
    	}
    }

    @RequestMapping(value = "/checkUserName", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse checkUserName(@RequestParam String userName) {
        return registerService.isUserNameAvailable(userName);
    }

    @RequestMapping(value = "/checkEmail", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse checkEmail(@RequestParam String email) {
        return registerService.isEmailAvailable(email);
    }

    @ResponseBody
    @RequestMapping(value = "/generateValidateCode")
    public Map<String, String> generateValidationCode(HttpSession session) {
        String realPath = session.getServletContext().getRealPath("/");
        String date = CommonUtils.getDate(0);
        Map<String, String> result = new HashMap<String, String>();
        Map<String, String> pastCode = (Map)session.getAttribute("code");
        if(pastCode != null){
        	String pastPath = pastCode.getOrDefault("path", pic + date +  "/default.jpg");
        	ValidationCodeGenerator.removeCodePic(pastPath);
        }
        try {
            Map<String, String> codeInfo = ValidationCodeGenerator.generateCode(realPath + pic + date);
            Map<String, String> code = new HashMap<String, String>();
            code.put("code", codeInfo.get("code"));
            String path = pic + date + "/" + codeInfo.get("picName") + ".jpg";
            code.put("path", path);
            code.put("timestamp", String.valueOf(System.currentTimeMillis()));
            session.setAttribute("code", code);
            result.put("path", path);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            session.setAttribute("code", "");
            String path = pic + date + "/" + "default.jpg";
            result.put("path", path);
            return result;
        }
    }

    @RequestMapping(value = "/checkValidateCode")
    @ResponseBody
    public ResultResponse generateValidationCode(@RequestParam String validateCode, HttpSession session) {
        return checkCode(validateCode, session);
    }

    private ResultResponse checkCode(String validateCode, HttpSession session) {
        try {
            Map<String, String> map = null;
            if (session.getAttribute("code") instanceof Map) {
                map = (Map<String, String>) session.getAttribute("code");
            }
            if (!map.get("code").equalsIgnoreCase(validateCode)) {
                return new ResultResponse(ResultResponseCode.CODE_IS_NOT_SAME, false);
            }
            return new ResultResponse(ResultResponseCode.SUCCESS, true);
        } catch (Exception e) {
            return new ResultResponse(ResultResponseCode.SYSTEM_ERROR, false);
        }
    }
}
