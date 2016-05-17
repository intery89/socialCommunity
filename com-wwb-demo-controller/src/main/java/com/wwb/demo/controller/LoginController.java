package com.wwb.demo.controller;

import com.wwb.demo.domain.model.LoginForm;
import com.wwb.demo.service.impl.LoginService;
import com.wwb.demo.utils.ValidationCodeGenerator;
import com.wwb.demo.utils.result.ResultResponse;
import com.wwb.demo.utils.result.enums.ResultResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Intery on 2016/5/15.
 */
@Controller
public class LoginController {

    private static final String pic = "pic/";

    @Autowired
    LoginService loginService;

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
        Map<String, String> result = new HashMap<String, String>();
        try {
            List<String> codeAndPath = ValidationCodeGenerator.generateCode(pic);
            session.setAttribute("code", codeAndPath.get(1));
            result.put("path", codeAndPath.get(2));
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            session.setAttribute("code", "");
            String path = pic + "default.jpg";
            result.put("path", path);
            return result;
        }
    }

    @RequestMapping(value = "/checkValidateCode", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> generateValidationCode(@RequestBody Map<String, String> inputCode, HttpSession session) {
        Map<String, String> result = new HashMap<String, String>();
        try {
            String code = (String) session.getAttribute("code");
            if (code.equalsIgnoreCase(inputCode.get("code"))) {
                result.put("result", "success");
                return result;
            }
            result.put("result", "false");
            return result;
        } catch (Exception e) {
            result.put("result", "false");
            return result;
        }
    }
}
