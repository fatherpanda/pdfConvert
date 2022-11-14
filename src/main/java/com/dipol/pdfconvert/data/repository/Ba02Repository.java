package com.dipol.pdfconvert.data.repository;

import com.dipol.pdfconvert.data.entity.Ba02;
import com.dipol.pdfconvert.data.entity.Ba02PK;
import com.dipol.pdfconvert.data.repository.custom.Ba02CustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Ba02Repository extends JpaRepository<Ba02, Ba02PK>, Ba02CustomRepository{
}
