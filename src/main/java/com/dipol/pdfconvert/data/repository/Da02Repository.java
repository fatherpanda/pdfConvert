package com.dipol.pdfconvert.data.repository;

import com.dipol.pdfconvert.data.entity.Da02;
import com.dipol.pdfconvert.data.repository.custom.Da02CustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Da02Repository extends JpaRepository<Da02, String>, Da02CustomRepository {
}
