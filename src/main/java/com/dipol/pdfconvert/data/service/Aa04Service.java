package com.dipol.pdfconvert.data.service;

import com.dipol.pdfconvert.data.entity.Aa04;
import com.dipol.pdfconvert.data.repository.Aa04Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class Aa04Service {
    @Autowired
    Aa04Repository aa04Repository;
    public Page<Aa04> list(Pageable pageable)
    {
        return aa04Repository.findAll(pageable);
    }
}
