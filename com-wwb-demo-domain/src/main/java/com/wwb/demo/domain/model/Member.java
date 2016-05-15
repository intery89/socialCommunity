package com.wwb.demo.domain.model;

import javax.persistence.*;


@Entity
@Table(name = "member")
public class Member extends BaseEntity {

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;


    @Column(name = "zipCode")
    private String zipCode;

    @Column(name = "phone")
    private String phone;

    @Column(name = "avatarUrl")
    private String avatarUrl;

    @Column(name = "mobile")
    private String mobile;

    /**
     * type = 1买家
     * type = 2卖家
     */
    @Column(name = "type")
    private String type;

    /**
     * 注册用户所在省市县
     */
    @ManyToOne( fetch = FetchType.LAZY,cascade = {CascadeType.REFRESH})
    @JoinColumn(name="areaId")
    private Area area;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "memberCertificateId")
    private MemberCertificate memberCertificate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}