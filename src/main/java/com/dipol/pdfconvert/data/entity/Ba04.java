package com.dipol.pdfconvert.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Ba04 {
    @EmbeddedId
    private Ba04PK ba04PK;
    @Basic
    @Column(name = "sort_order")
    private Integer sortOrder;
    @Basic
    @Column(name = "sub_order")
    private Integer subOrder;
    @Basic
    @Column(name = "attr")
    private String attr;
    @Basic
    @Column(name = "code_desc")
    private String codeDesc;
    @Basic
    @Column(name = "category")
    private String category;
    @Basic
    @Column(name = "org_code")
    private String orgCode;
    @Basic
    @Column(name = "add_user")
    private String addUser;
    @Basic
    @Column(name = "add_date")
    private Timestamp addDate;
    @Basic
    @Column(name = "add_ip")
    private String addIp;
    @Basic
    @Column(name = "update_user")
    private String updateUser;
    @Basic
    @Column(name = "update_date")
    private Timestamp updateDate;
    @Basic
    @Column(name = "update_ip")
    private String updateIp;
    @Basic
    @Column(name = "bigo")
    private String bigo;


}
