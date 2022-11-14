package com.dipol.pdfconvert.data.repository;

import com.dipol.pdfconvert.data.entity.Aa02;
import com.dipol.pdfconvert.data.entity.Aa02PK;
import com.dipol.pdfconvert.data.repository.custom.Aa02CustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Aa02Repository extends JpaRepository<Aa02, Aa02PK>, Aa02CustomRepository {
}
