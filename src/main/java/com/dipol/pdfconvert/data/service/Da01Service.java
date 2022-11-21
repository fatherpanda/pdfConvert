package com.dipol.pdfconvert.data.service;


import com.dipol.pdfconvert.data.entity.Ba04;
import com.dipol.pdfconvert.data.entity.Ba04PK;
import com.dipol.pdfconvert.data.entity.Da01;
import com.dipol.pdfconvert.data.repository.Ba04Repository;
import com.dipol.pdfconvert.data.repository.Da01Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Da01Service {
    @Autowired
    Da01Repository da01Repository;

    public Page<Da01> list(Pageable pageable) {
        return da01Repository.findAll(pageable);
    }

    public boolean delete(List<String> idList) {
        boolean isResult = true;

        if (0 == 0) { //추후 사용자 권한설정을 위한 조건
            da01Repository.deleteAllById(idList);
            List<Da01> da01List = da01Repository.findAllById(idList);
            if (da01List.size() > 0) isResult = false;
        }
        return isResult;
    }
    public void write(Da01 da01)
    {
        da01Repository.save(da01);
    }
}
