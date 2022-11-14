package com.dipol.pdfconvert.data.service;

import com.dipol.pdfconvert.data.entity.Aa01;
import com.dipol.pdfconvert.data.repository.Aa01Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class Aa01Service {
    @Autowired
    Aa01Repository aa01Repository;
    public Page<Aa01> list(Pageable pageable)
    {
        return aa01Repository.findAll(pageable);
    }
    public Aa01 view(String gubn){
        return aa01Repository.getReferenceById(gubn);
    }
    public boolean delete(String[] gubn)
    {
        boolean isResult=true;

        if(0==0) { //추후 사용자 권한설정을 위한 조건
            aa01Repository.deleteAllById(Arrays.asList(gubn));
            List<Aa01> aa01List= aa01Repository.findAllById(Arrays.asList(gubn));
            if(aa01List.size()>0) isResult=false;
        }
        return isResult;
    }
    public Aa01 write(Aa01 aa01) {
        return aa01Repository.saveAndFlush(aa01);
    }
}
