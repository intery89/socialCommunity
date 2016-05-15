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
import java.util.List;

/**
 * Created by Intery on 2016/5/15.
 */
@Controller
public class LoginController {

    private static final String pic = "pic/";

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute("LoginUser") LoginForm user, HttpSession session) {
        String username = user.getUsername();
        String password = user.getPassword();

        ResultResponse resultResponse = loginService.checkUserIdentification(username, password);

        if (resultResponse.getStatus().getCode().equals(ResultResponseCode.SUCCESS.getCode())) {
            //需要将member的相关数据放入到session中
            return new ModelAndView("base/index.jsp");
        } else if (resultResponse.getStatus().getCode().equals(ResultResponseCode.USERNAME_PASSWORD_ERROR.getCode())) {
            return new ModelAndView("base/login.jsp");
        } else {
            //抛出异常或者是其他错误
            return new ModelAndView("base/error.jsp");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/generateValidateCode")
    public String generateValidationCode(HttpSession session) {
        try {
            List<String> codeAndPath = ValidationCodeGenerator.generateCode(pic);
            session.setAttribute("code", codeAndPath.get(1));
            return codeAndPath.get(2);
        } catch (IOException e) {
            e.printStackTrace();
            session.setAttribute("code", "");
            String path = pic + "default.jpg";
            return path;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/checkValidateCode", method = RequestMethod.GET)
    public boolean generateValidationCode(@PathVariable("inputCode") String inputCode, HttpSession session) {
        try {
            String code = (String) session.getAttribute("code");
            if (code.equalsIgnoreCase(inputCode)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
