package com.dipol.pdfconvert.data.repository.custom;

import java.util.List;

import com.dipol.pdfconvert.data.entity.Ba04;

public interface Ba04CustomRepository {
    public List<String> getReportVersion();

    public List<Ba04> getListByReportVersiton(String reportVersion);
}
