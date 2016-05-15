package com.wwb.demo.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户认证的相关信息
 */
@Entity
@Table(name = "memberCertificate")
public class MemberCertificate extends BaseEntity {
    @Column(name = "realName")
    private String realName;
    /**
     * 审核状态
     * 1 = 未审核
     * 2 = 审核通过
     * 3 = 审核未通过
     */
    @Column(name = "verifyStatus")
    private int verifyStatus;

    /**
     * 审核备注
     */
    @Column(name = "verifyNote")
    private String verifyNote;

    /**
     * 认证人的身份证号码
     */
    @Column(name = "idCardNo")
    private String idCardNo;

    @Column(name = "idCardBackUrl")
    private String idCardBackUrl;


    @Column(name = "idCardFrontUrl")
    private String idCardFrontUrl;

    /**
     * 营业执照图片地址
     */
    @Column(name = "bizCodePictureUrl")
    private String bizCodePictureUrl;
    /**
     * 税务登记证图片地址
     */
    @Column(name = "taxCodePictureUrl")
    private String taxCodePictureUrl;
    /**
     * 机构组织代码证图片地址
     */
    @Column(name = "orgCodePictureUrl")
    private String orgCodePictureUrl;

    /**
     * 三证合一以后只用这一个证
     */
    @Column(name = "onlyCardUrl")
    private String onlyCardUrl;


    /**
     * 审核日期
     */
    @Column(name = "auditDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date auditDate;

    /**
     * 审核人
     */
    @OneToOne
    @JoinColumn(name = "auditorId")
    private Member auditor;

    /**
     * 对应的注册用户id
     */
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member registerMember;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(int verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getVerifyNote() {
        return verifyNote;
    }

    public void setVerifyNote(String verifyNote) {
        this.verifyNote = verifyNote;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getIdCardBackUrl() {
        return idCardBackUrl;
    }

    public void setIdCardBackUrl(String idCardBackUrl) {
        this.idCardBackUrl = idCardBackUrl;
    }

    public String getIdCardFrontUrl() {
        return idCardFrontUrl;
    }

    public void setIdCardFrontUrl(String idCardFrontUrl) {
        this.idCardFrontUrl = idCardFrontUrl;
    }

    public String getBizCodePictureUrl() {
        return bizCodePictureUrl;
    }

    public void setBizCodePictureUrl(String bizCodePictureUrl) {
        this.bizCodePictureUrl = bizCodePictureUrl;
    }

    public String getTaxCodePictureUrl() {
        return taxCodePictureUrl;
    }

    public void setTaxCodePictureUrl(String taxCodePictureUrl) {
        this.taxCodePictureUrl = taxCodePictureUrl;
    }

    public String getOrgCodePictureUrl() {
        return orgCodePictureUrl;
    }

    public void setOrgCodePictureUrl(String orgCodePictureUrl) {
        this.orgCodePictureUrl = orgCodePictureUrl;
    }

    public String getOnlyCardUrl() {
        return onlyCardUrl;
    }

    public void setOnlyCardUrl(String onlyCardUrl) {
        this.onlyCardUrl = onlyCardUrl;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public Member getAuditor() {
        return auditor;
    }

    public void setAuditor(Member auditor) {
        this.auditor = auditor;
    }

    public Member getRegisterMember() {
        return registerMember;
    }

    public void setRegisterMember(Member registerMember) {
        this.registerMember = registerMember;
    }
}