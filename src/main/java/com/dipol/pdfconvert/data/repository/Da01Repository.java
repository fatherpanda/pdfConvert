package com.dipol.pdfconvert.data.repository;

import com.dipol.pdfconvert.data.entity.Da01;
import com.dipol.pdfconvert.data.repository.custom.Da01CustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Da01Repository extends JpaRepository<Da01, String>, Da01CustomRepository {
}
