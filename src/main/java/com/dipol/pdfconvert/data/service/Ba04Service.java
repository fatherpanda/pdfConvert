package com.dipol.pdfconvert.data.service;


import com.dipol.pdfconvert.data.entity.Ba04;
import com.dipol.pdfconvert.data.entity.Ba04PK;
import com.dipol.pdfconvert.data.repository.Ba04Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Ba04Service {
    @Autowired
    Ba04Repository ba04Repository;

    public Page<Ba04> list(Pageable pageable) {
        return ba04Repository.findAll(pageable);
    }

    public boolean delete(List<Ba04PK> ba04PKList) {
        boolean isResult = true;

        if (0 == 0) { //추후 사용자 권한설정을 위한 조건
            ba04Repository.deleteAllById(ba04PKList);
            List<Ba04> aa02List = ba04Repository.findAllById(ba04PKList);
            if (aa02List.size() > 0) isResult = false;
        }
        return isResult;
    }
}
