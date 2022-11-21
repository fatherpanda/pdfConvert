package com.dipol.pdfconvert.controller;

import com.dipol.pdfconvert.data.entity.*;
import com.dipol.pdfconvert.data.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/api/")
public class ApiController {
    @Autowired
    Aa01Service aa01Service;
    @Autowired
    Aa02Service aa02Service;
    @Autowired
    Aa04Service aa04Service;
    @Autowired
    Ba02Service ba02Service;
    @Autowired
    Ba04Service ba04Service;

    @RequestMapping(value = "/aa02", method = RequestMethod.GET)
    public Page<Aa02> aa02List(@PageableDefault(size = 20) Pageable pageable)
    {
        return aa02Service.list(pageable);
    }
    @RequestMapping(value = "/aa02", method = RequestMethod.DELETE)
    public Map<String, Object> aa02Delete(@RequestBody List<Aa02PK> aa02PKList)
    {
        boolean isResult=aa02Service.delete(aa02PKList);
        Map<String, Object> resultMap=new HashMap<>();
        resultMap.put("result", isResult);
        return resultMap;
    }
    public Aa02 aa02Write(@RequestBody Aa02 aa02, HttpServletRequest request)
    {
        String userId="";
        aa02.setAddDate(new Date());
        aa02.setAddIp(request.getRemoteAddr());
        aa02.setAddUser(userId);
        return aa02Service.write(aa02);
    }
    @RequestMapping(value = "/aa04", method = RequestMethod.GET)
    public Page<Aa04> aa04List(@PageableDefault(size = 100) Pageable pageable)
    {
        return aa04Service.list(pageable);
    }

    @RequestMapping(value = "/ba02", method = RequestMethod.GET)
    public Page<Ba02> ba02List(@PageableDefault(size = 100) Pageable pageable)
    {
        return ba02Service.list(pageable);
    }

    @RequestMapping(value = "/ba02", method = RequestMethod.DELETE)
    public Map<String, Object> ba02Delete(@RequestBody List<Ba02PK> ba02PKList)
    {
        boolean isResult=ba02Service.delete(ba02PKList);
        Map<String, Object> resultMap=new HashMap<>();
        resultMap.put("result", isResult);
        return resultMap;
    }
    @RequestMapping(value = "/ba04", method = RequestMethod.GET)
    public Page<Ba04> ba04List(@PageableDefault(size = 100) Pageable pageable)
    {
        return ba04Service.list(pageable);
    }
    @RequestMapping(value = "/ba04", method = RequestMethod.DELETE)
    public Map<String, Object> ba04Delete(@RequestBody List<Ba04PK> ba04PKList)
    {
        boolean isResult=ba04Service.delete(ba04PKList);
        Map<String, Object> resultMap=new HashMap<>();
        resultMap.put("result", isResult);
        return resultMap;
    }
}
