package com.wwb.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;

/**
 * Created by Intery on 2016/5/15.
 */
@Controller
@RequestMapping("login.do")
public class LoginController{

    private String FAIL = "fail";
    private String email;
    private String password;

    InfouserManager infousermanager;

    public LoginController() {
        // TODO Auto-generated constructor st
    }

    public String execute(){
        //��ȡһ����̬��ActionContextʵ��
        ActionContext acx = ActionContext.getContext();
        System.out.println(email);
        System.out.println(password);
        Integer flag = infousermanager.verify(email.trim(),password.trim());
        if(flag!=0){
            acx.getSession().put(EriqooConstant.USER, email);
            acx.getSession().put(EriqooConstant.STATE, "�ѵ�¼");
            acx.getSession().put(EriqooConstant.userID, flag);
            return SUCCESS;
        }else{
            return FAIL;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public InfouserManager getInfousermanager() {
        return infousermanager;
    }



    public void setInfousermanager(InfouserManager infousermanager) {
        this.infousermanager = infousermanager;
    }

}
