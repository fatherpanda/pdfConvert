package com.dipol.pdfconvert.data.repository;

import com.dipol.pdfconvert.data.entity.Ba04;
import com.dipol.pdfconvert.data.entity.Ba04PK;
import com.dipol.pdfconvert.data.repository.custom.Ba04CustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Ba04Repository extends JpaRepository<Ba04, Ba04PK>, Ba04CustomRepository {
}
