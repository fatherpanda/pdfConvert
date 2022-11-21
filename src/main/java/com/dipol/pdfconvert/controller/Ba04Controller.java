package com.dipol.pdfconvert.controller;

import com.dipol.pdfconvert.data.entity.Ba04;
import com.dipol.pdfconvert.data.entity.Ba04PK;
import com.dipol.pdfconvert.data.service.Ba04Service;
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
@RequestMapping("/api/ba04")
public class Ba04Controller {

    @Autowired
    Ba04Service ba04Service;

    @RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
    public Page<Ba04> ba04List(@PageableDefault(size = 100) Pageable pageable) {
        return ba04Service.list(pageable);
    }

    @RequestMapping(value = { "", "/" }, method = RequestMethod.DELETE)
    public Map<String, Object> ba04Delete(@RequestBody List<Ba04PK> ba04PKList) {
        boolean isResult = ba04Service.delete(ba04PKList);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", isResult);
        return resultMap;
    }

    @RequestMapping(value = "reportVersion", method = RequestMethod.GET)
    public List<String> ba04ReportVersion() {
        return ba04Service.reportVersion();
    }
}
