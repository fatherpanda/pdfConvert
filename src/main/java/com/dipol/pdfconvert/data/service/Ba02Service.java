package com.dipol.pdfconvert.data.service;

import com.dipol.pdfconvert.data.entity.Aa02;
import com.dipol.pdfconvert.data.entity.Aa02PK;
import com.dipol.pdfconvert.data.entity.Ba02;
import com.dipol.pdfconvert.data.entity.Ba02PK;
import com.dipol.pdfconvert.data.repository.Ba02Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Ba02Service {
    @Autowired
    Ba02Repository ba02Repository;
    public Page<Ba02> list(Pageable pageable)
    {
        return ba02Repository.findAll(pageable);
    }
    public boolean delete(List<Ba02PK> ba02PKList)
    {
        boolean isResult=true;

        if(0==0) { //추후 사용자 권한설정을 위한 조건
            ba02Repository.deleteAllById(ba02PKList);
            List<Ba02> aa02List=ba02Repository.findAllById(ba02PKList);
            if(aa02List.size()>0) isResult=false;
        }
        return isResult;
    }
}
