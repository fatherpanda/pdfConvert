package com.dipol.pdfconvert.controller;
import com.dipol.pdfconvert.data.entity.Ba02;
import com.dipol.pdfconvert.data.entity.Ba02PK;
import com.dipol.pdfconvert.data.service.Ba02Service;
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
@RequestMapping("/api/ba02")
public class Ba02Controller {

    @Autowired
    Ba02Service ba02Service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Page<Ba02> ba02List(@PageableDefault(size = 100) Pageable pageable)
    {
        return ba02Service.list(pageable);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public Map<String, Object> ba02Delete(@RequestBody List<Ba02PK> ba02PKList)
    {
        boolean isResult=ba02Service.delete(ba02PKList);
        Map<String, Object> resultMap=new HashMap<>();
        resultMap.put("result", isResult);
        return resultMap;
    }}
