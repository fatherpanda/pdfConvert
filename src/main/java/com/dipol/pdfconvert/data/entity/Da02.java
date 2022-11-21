package com.dipol.pdfconvert.data.entity;

import lombok.Data;

import javax.persistence.*;

import java.util.Date;

@Entity
@Data
public class Da02 {
    @EmbeddedId
    Da02PK da02PK;
    @Basic
    @Column(name = "sort_order")
    private Integer sortOrder;
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
