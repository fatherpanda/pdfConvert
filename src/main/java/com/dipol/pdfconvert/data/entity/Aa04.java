package com.dipol.pdfconvert.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Aa04 {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "errdate")
    private Timestamp errdate;
    @Basic
    @Column(name = "doc_kind")
    private String docKind;
    @Basic
    @Column(name = "acc_no")
    private String accNo;
    @Basic
    @Column(name = "event_no")
    private Integer eventNo;
    @Basic
    @Column(name = "code")
    private String code;
    @Basic
    @Column(name = "code_seq")
    private Integer codeSeq;
    @Basic
    @Column(name = "errno")
    private String errno;
    @Basic
    @Column(name = "errmsg")
    private String errmsg;
    @Basic
    @Column(name = "pageno")
    private Integer pageno;
    @Basic
    @Column(name = "pgmid")
    private String pgmid;
    @Basic
    @Column(name = "tableid")
    private String tableid;
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
    @Column(name = "bigo")
    private String bigo;

}
