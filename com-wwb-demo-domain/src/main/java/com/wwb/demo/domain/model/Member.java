package com.wwb.demo.domain.model;

import javax.persistence.*;
import java.util.List;

/**
 * 会员信息实体类，包括买家和卖家
 */
@Entity
@Table(name = "member")
public class Member extends BaseEntity {

    public Member(){

    }
    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

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
    @ManyToOne( fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
    @JoinColumn(name="areaId")
    private Area area;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "registerMember")
    private MemberCertificate memberCertificate;


    @OneToMany( fetch = FetchType.LAZY,cascade = {CascadeType.ALL},mappedBy = "member")
    private List<Receiver> receivers;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public MemberCertificate getMemberCertificate() {
        return memberCertificate;
    }

    public void setMemberCertificate(MemberCertificate memberCertificate) {
        this.memberCertificate = memberCertificate;
    }

    public List<Receiver> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<Receiver> receivers) {
        this.receivers = receivers;
    }
}