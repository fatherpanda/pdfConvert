package com.dipol.pdfconvert.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@IdClass(Ea02PK.class)
@Data
public class Ea02 {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "acc_no")
    private String accNo;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "event_no")
    private int eventNo;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "code")
    private String code;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "code_seq")
    private int codeSeq;
    @Basic
    @Column(name = "reqfield")
    private String reqfield;
    @Basic
    @Column(name = "sort_order")
    private int sortOrder;
    @Basic
    @Column(name = "pageno")
    private Integer pageno;
    @Basic
    @Column(name = "attr")
    private String attr;
    @Basic
    @Column(name = "recode_time")
    private String recodeTime;
    @Basic
    @Column(name = "recode_value")
    private String recodeValue;
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
