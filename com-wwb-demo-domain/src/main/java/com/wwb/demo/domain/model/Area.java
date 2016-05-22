package com.wwb.demo.domain.model;
/**
 * 关于省份地区实体类
 */

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "area")
public class Area extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "treePath")
    private String treePath;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "parentId")
    private Area parent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private Set<Area> children = new HashSet<Area>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTreePath() {
        return treePath;
    }

    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }

    public Area getParent() {
        return parent;
    }

    public void setParent(Area parent) {
        this.parent = parent;
    }

    public Set<Area> getChildren() {
        return children;
    }

    public void setChildren(Set<Area> children) {
        this.children = children;
    }

}
