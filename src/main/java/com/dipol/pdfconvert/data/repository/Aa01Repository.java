package com.dipol.pdfconvert.data.repository;

import com.dipol.pdfconvert.data.entity.Aa01;
import com.dipol.pdfconvert.data.repository.custom.Aa01CustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Aa01Repository extends JpaRepository<Aa01, String>, Aa01CustomRepository {
}
