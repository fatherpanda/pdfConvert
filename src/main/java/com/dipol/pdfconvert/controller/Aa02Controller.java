package com.dipol.pdfconvert.controller;

import com.dipol.pdfconvert.data.entity.Aa02;
import com.dipol.pdfconvert.data.entity.Aa02PK;
import com.dipol.pdfconvert.data.service.Aa02Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/aa02")
public class Aa02Controller {
    @Autowired
    Aa02Service aa02Service;

    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
    public Page<Aa02> aa02List(@PageableDefault(size = 20) Pageable pageable) {
        return aa02Service.list(pageable);
    }

    @RequestMapping(value = { "", "/" }, method = RequestMethod.DELETE)
    public Map<String, Object> aa02Delete(@RequestBody List<Aa02PK> aa02PKList) {
        boolean isResult = aa02Service.delete(aa02PKList);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", isResult);
        return resultMap;
    }

    public Aa02 aa02Write(@RequestBody Aa02 aa02, HttpServletRequest request) {
        String userId = "";
        aa02.setAddDate(new Date());
        aa02.setAddIp(request.getRemoteAddr());
        aa02.setAddUser(userId);
        return aa02Service.write(aa02);
    }

    @RequestMapping(value = "/gubn/{gubn}", method = RequestMethod.GET)
    public List<Aa02> getCode(@PathVariable String gubn) {
        return aa02Service.selectCode(gubn);
    }

}
