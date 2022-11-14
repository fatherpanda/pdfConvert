package com.dipol.pdfconvert.data.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class Ea02PK implements Serializable {
    @Column(name = "acc_no")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String accNo;
    @Column(name = "event_no")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventNo;
    @Column(name = "code")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String code;
    @Column(name = "code_seq")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codeSeq;

}
