package com.dipol.pdfconvert.data.repository.custom;

import com.dipol.pdfconvert.data.entity.Aa02;

import java.util.List;

public interface Aa02CustomRepository {
    public List<Aa02> selectByGubn(String gubn);
}
