package com.dipol.pdfconvert.data.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class UploadFile {
    @Id
    @GeneratedValue
    private Long id;
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private Long size;
}
