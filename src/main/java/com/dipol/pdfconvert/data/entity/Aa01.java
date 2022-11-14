package com.dipol.pdfconvert.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "aa01", schema = "pdftest")
@Data
public class Aa01 {
    @Id
    @Column(name = "gubn")
    private String gubn;
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
    @Column(name = "conv_type")
    private String convType;
    @Basic
    @Column(name = "memo")
    private String memo;
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
