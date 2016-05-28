package com.wwb.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wwb.demo.utils.MailUtils;

@Controller
public class RegisterController {

	private static final String defaultEmail = "wwb20160528@163.com";
	private static final String registerContent = "Please kindly click this link to activate your account in zhaoxiwang.\n<a href='http://localhost:8080/com-wwb-demo-controller/loginsuccess?status=1'>激活</a>";
	
    @RequestMapping("/register")
    @ResponseBody
    public String hello(@RequestBody Map<String, String> form, HttpSession session) {
    	System.out.println(form.toString());
    	String email = form.getOrDefault("email", defaultEmail);
    	MailUtils.send(email, "zhaoxiwang-register", registerContent);
        return "loginsuccess?status=0";
    }
    
    @RequestMapping("/loginsuccess")
    public String loginsuccess(@RequestParam String status) {
    	if("1".equalsIgnoreCase(status)){
    		return "success";
    	}else{
            return "reminder";
    	}
    }
}
