package com.dipol.pdfconvert.controller;

import com.dipol.pdfconvert.data.entity.Aa01;
import com.dipol.pdfconvert.data.service.Aa01Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

import java.util.Map;

@RestController
@RequestMapping("/api/aa01")
public class Aa01Controller {
    @Autowired
    Aa01Service aa01Service;

    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
    public Page<Aa01> aa01List(@PageableDefault(size = 20) Pageable pageable) {
        return aa01Service.list(pageable);
    }

    @RequestMapping(value = { "", "/" }, method = RequestMethod.DELETE)
    public Map<String, Object> aa01Delete(@RequestBody Map<String, String[]> map) {
        boolean isResult = aa01Service.delete(map.get("gubn"));
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", isResult);
        return resultMap;
    }

    @RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
    public Aa01 aa01List(@RequestBody Aa01 aa01, HttpServletRequest request) {
        String userId = "";
        aa01.setAddDate(new Date());
        aa01.setAddIp(request.getRemoteAddr());
        aa01.setAddUser(userId);
        return aa01Service.write(aa01);
    }

}
