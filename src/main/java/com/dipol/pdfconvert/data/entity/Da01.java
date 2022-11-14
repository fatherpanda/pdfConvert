package com.dipol.pdfconvert.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Da01 {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "acc_no")
    private String accNo;
    @Basic
    @Column(name = "prod_name")
    private String prodName;
    @Basic
    @Column(name = "car_desc")
    private String carDesc;
    @Basic
    @Column(name = "vin")
    private String vin;
    @Basic
    @Column(name = "user_name")
    private String userName;
    @Basic
    @Column(name = "write_date")
    private String writeDate;
    @Basic
    @Column(name = "tool_version")
    private String toolVersion;
    @Basic
    @Column(name = "report_version")
    private String reportVersion;
    @Basic
    @Column(name = "status_flag")
    private String statusFlag;
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
