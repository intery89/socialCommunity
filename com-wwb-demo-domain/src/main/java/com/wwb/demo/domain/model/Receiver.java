package com.wwb.demo.domain.model;

import javax.persistence.*;

/**
 * 收货地址信息
 * Created by ai on 2016/5/15.
 */

@Entity
@Table(name = "receiver")
public class Receiver extends BaseEntity {

    @JoinColumn(name = "receiverName")
    private String receiverName;
    /**
     * 收货人地址的县级别
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "areaId")
    private Area area;

    /**
     * 收货人地址的县级别以下的详细地址
     */
    @JoinColumn(name = "detailAddress")
    private String detailAddress;

    /**
     * 邮编
     */
    @Column(name = "zipCode")
    private String zipCode;

    /**
     * 固定电话
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 移动电话
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 是否默认
     */
    @Column(name = "defaultAddress")
    private Boolean defaultAddress;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
