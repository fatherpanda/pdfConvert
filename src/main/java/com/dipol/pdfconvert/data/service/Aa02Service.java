package com.dipol.pdfconvert.data.service;

import com.dipol.pdfconvert.data.entity.Aa02;
import com.dipol.pdfconvert.data.entity.Aa02PK;
import com.dipol.pdfconvert.data.repository.Aa02Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Aa02Service {
    @Autowired
    Aa02Repository aa02Repository;
    public Page<Aa02> list(Pageable pageable)
    {
        return aa02Repository.findAll(pageable);
    }
    public boolean delete(List<Aa02PK> aa02PKList)
    {
        boolean isResult=true;

        if(0==0) { //추후 사용자 권한설정을 위한 조건
            aa02Repository.deleteAllById(aa02PKList);
            List<Aa02> aa02List=aa02Repository.findAllById(aa02PKList);
            if(aa02List.size()>0) isResult=false;
        }
        return isResult;
    }
    public Aa02 write(Aa02 aa02) {
        return aa02Repository.saveAndFlush(aa02);
    }
}
