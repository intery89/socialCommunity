package com.wwb.demo.domain.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Intery on 2016/5/24.
 */
@Entity
@Table(name = "t_user")
public class User {

        private Long id;
        private String email;//邮箱地址
        private Integer status;//状态，0-未激活；1-已激活
        private String validateCode;//激活码
        private Date registerTime;//注册时间

        public User() {}

        @Id
        @GeneratedValue
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        @Column(nullable=false , unique=true)
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getValidateCode() {
            return validateCode;
        }

        public void setValidateCode(String validateCode) {
            this.validateCode = validateCode;
        }

        @Temporal(TemporalType.TIMESTAMP)
        @Column(nullable = false)
        public Date getRegisterTime() {
            return registerTime;
        }

        public void setRegisterTime(Date registerTime) {
            this.registerTime = registerTime;
        }

        @Transient
        public Date getLastActivateTime() {
            Calendar cl = Calendar.getInstance();
            cl.setTime(registerTime);
            cl.add(Calendar.DATE , 2);

            return cl.getTime();
        }

    }
