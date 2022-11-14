package com.dipol.pdfconvert.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Ba02 {
    @EmbeddedId
    private Ba02PK ba02PK;
    @Basic
    @Column(name = "reqfield")
    private String reqfield;
    @Basic
    @Column(name = "abs_method")
    private String absMethod;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "sub_title")
    private String subTitle;
    @Basic
    @Column(name = "desc_name")
    private String descName;
    @Basic
    @Column(name = "first_line")
    private String firstLine;
    @Basic
    @Column(name = "record_time")
    private String recordTime;
    @Basic
    @Column(name = "record_value")
    private String recordValue;
    @Basic
    @Column(name = "worksname")
    private String worksname;
    @Basic
    @Column(name = "conv_type")
    private String convType;
    @Basic
    @Column(name = "car_company")
    private String carCompany;
    @Basic
    @Column(name = "car_kind")
    private String carKind;
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
