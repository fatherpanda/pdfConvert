package com.dipol.pdfconvert.data.entity;

import lombok.Data;

import javax.persistence.*;

import java.util.Date;

@Entity
@Data
public class Aa02 {
    @EmbeddedId
    private Aa02PK aa02PK;
    @Basic
    @Column(name = "useyn")
    private String useyn;
    @Basic
    @Column(name = "sort_order")
    private Integer sortOrder;
    @Basic
    @Column(name = "attr")
    private String attr;
    @Basic
    @Column(name = "cname")
    private String cname;
    @Basic
    @Column(name = "attr1")
    private String attr1;
    @Basic
    @Column(name = "code1")
    private String code1;
    @Basic
    @Column(name = "desc1")
    private String desc1;
    @Basic
    @Column(name = "attr2")
    private String attr2;
    @Basic
    @Column(name = "code2")
    private String code2;
    @Basic
    @Column(name = "desc2")
    private String desc2;
    @Basic
    @Column(name = "attr3")
    private String attr3;
    @Basic
    @Column(name = "code3")
    private String code3;
    @Basic
    @Column(name = "desc3")
    private String desc3;
    @Basic
    @Column(name = "attr4")
    private String attr4;
    @Basic
    @Column(name = "code4")
    private String code4;
    @Basic
    @Column(name = "desc4")
    private String desc4;
    @Basic
    @Column(name = "attr5")
    private String attr5;
    @Basic
    @Column(name = "code5")
    private String code5;
    @Basic
    @Column(name = "desc5")
    private String desc5;
    @Basic
    @Column(name = "add_user")
    private String addUser;
    @Basic
    @Column(name = "add_date")
    private Date addDate;
    @Basic
    @Column(name = "add_ip")
    private String addIp;
    @Basic
    @Column(name = "update_user")
    private String updateUser;
    @Basic
    @Column(name = "update_date")
    private Date updateDate;
    @Basic
    @Column(name = "update_ip")
    private String updateIp;
    @Basic
    @Column(name = "bigo")
    private String bigo;

}
