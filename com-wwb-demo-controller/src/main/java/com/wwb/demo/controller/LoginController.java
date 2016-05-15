package com.wwb.demo.controller;

import com.wwb.demo.domain.model.LoginForm;
import com.wwb.demo.service.impl.LoginService;
import com.wwb.demo.utils.ValidationCodeGenerator;
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

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute("LoginUser") LoginForm user, HttpSession session){
        String username = user.getUsername();
        String password = user.getPassword();
        boolean isClient = loginService.checkUserIdentification(username, password);

        if(isClient == true) {
            return new ModelAndView("base/index.jsp");
        }else{
            return new ModelAndView("base/login.jsp");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/generateValidateCode")
    public String generateValidationCode(HttpSession session){
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
    public boolean generateValidationCode(@PathVariable("inputCode")String inputCode, HttpSession session){
        try {
            String code = (String) session.getAttribute("code");
            if(code.equalsIgnoreCase(inputCode)){
                return true;
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }
}
