package com.dipol.pdfconvert.data.repository;

import com.dipol.pdfconvert.data.entity.Aa04;
import com.dipol.pdfconvert.data.repository.custom.Aa04CustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Aa04Repository extends JpaRepository<Aa04, String>, Aa04CustomRepository {
}
