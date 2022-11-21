package com.dipol.pdfconvert.controller;

import com.dipol.pdfconvert.data.entity.Aa04;
import com.dipol.pdfconvert.data.service.Aa04Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/aa04")
public class Aa04Controller {

    @Autowired
    Aa04Service aa04Service;

    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
    public Page<Aa04> aa04List(@PageableDefault(size = 100) Pageable pageable) {
        return aa04Service.list(pageable);
    }

}
