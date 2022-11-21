package com.dipol.pdfconvert.data.service;

import com.dipol.pdfconvert.data.entity.Da02;
import com.dipol.pdfconvert.data.repository.Da02Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class Da02Service {
    @Autowired
    Da02Repository da02Repository;

    public Page<Da02> list(Pageable pageable, Da02 da02) {
        return da02Repository.selectDa02(pageable, da02);
    }

    public boolean delete(List<String> idList) {
        boolean isResult = true;

        if (0 == 0) { // 추후 사용자 권한설정을 위한 조건
            da02Repository.deleteAllById(idList);
            List<Da02> da02List = da02Repository.findAllById(idList);
            if (da02List.size() > 0)
                isResult = false;
        }
        return isResult;
    }

    public void write(Da02 da02) {
        da02.setAddDate(new Date());
        da02.setUpdateDate(new Date());
        da02Repository.save(da02);
    }

    public void writeAll(List<Da02> da02List) {
        for (Da02 da02 : da02List) {
            da02.setAddDate(new Date());
            da02.setUpdateDate(new Date());
        }
        da02Repository.saveAll(da02List);
    }

    public void modify(Da02 da02) {
        da02.setUpdateDate(new Date());
        da02Repository.save(da02);
    }

    public List<Integer> eventNoGroup() {
        return da02Repository.selectEventNo();
    }

}
