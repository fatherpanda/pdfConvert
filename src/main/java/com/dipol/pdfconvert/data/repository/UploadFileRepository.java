package com.dipol.pdfconvert.data.repository;

import com.dipol.pdfconvert.data.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {

}
