package com.dipol.pdfconvert.data.entity;

import lombok.Data;

import javax.persistence.*;

import java.util.Date;

@Entity
@Data
public class Da01 {
    @Id
    @Column(name = "acc_no")
    private String accNo;
    @Column(name = "prod_name")
    private String prodName;
    @Column(name = "car_desc")
    private String carDesc;
    @Column(name = "vin")
    private String vin;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "write_date")
    private String writeDate;
    @Column(name = "tool_version")
    private String toolVersion;
    @Column(name = "report_version")
    private String reportVersion;
    @Column(name = "status_flag")
    private String statusFlag;
    @Column(name = "doc_path")
    private String docPath;
    @Column(name = "add_user")
    private String addUser;
    @Column(name = "add_date")
    private Date addDate;
    @Column(name = "add_ip")
    private String addIp;
    @Column(name = "update_user")
    private String updateUser;
    @Column(name = "update_date")
    private Date updateDate;
    @Column(name = "update_ip")
    private String updateIp;
    @Column(name = "bigo")
    private String bigo;
}
