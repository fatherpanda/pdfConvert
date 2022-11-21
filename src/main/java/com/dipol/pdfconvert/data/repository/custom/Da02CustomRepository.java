package com.dipol.pdfconvert.data.repository.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dipol.pdfconvert.data.entity.Da02;

public interface Da02CustomRepository {
  public Page<Da02> selectDa02(Pageable pageable, Da02 da02);

  public List<Integer> selectEventNo();

}
