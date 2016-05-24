package com.wwb.demo.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    /*
    status = 0 表示记录没有删除
    status = 1 表示记录删除
    其他状态另说
    */
    @Column(name = "status")
    private int status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @PrePersist
    protected void onCreate() {
        Date currentTime = new Date();
        setCreateTime(currentTime);
        setUpdateTime(currentTime);
    }

    @PreUpdate
    protected void onUpdate() {
        Date currentTime = new Date();
        if (getCreateTime() == null) {
            setCreateTime(currentTime);
        }
        setUpdateTime(new Date());
    }

}