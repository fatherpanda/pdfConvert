package com.dipol.pdfconvert.controller;

import com.dipol.pdfconvert.data.entity.Da01;
import com.dipol.pdfconvert.data.entity.Da02;
import com.dipol.pdfconvert.data.entity.Da02PK;
import com.dipol.pdfconvert.data.service.Da01Service;
import com.dipol.pdfconvert.data.service.Da02Service;
import com.dipol.pdfconvert.data.service.FileStorageService;
import com.dipol.pdfconvert.property.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

@RestController
@RequestMapping("/api/da02")
public class Da02Controller {
    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    FileStorageProperties fileStorageProperties;
    @Autowired
    ArrayList<Da01> convertList;
    @Autowired
    Da01Service da01Service;
    @Autowired
    Da02Service da02Service;

    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
    public Page<Da02> da02List(@PageableDefault(size = 100) Pageable pageable,
            @RequestParam(required = false) String accNo,
            @RequestParam(required = false) Integer eventNo) {
        Da02 da02 = new Da02();
        Da02PK da02pk = new Da02PK();
        da02pk.setAccNo(accNo);
        da02pk.setEventNo(eventNo);
        da02.setDa02PK(da02pk);
        return da02Service.list(pageable, da02);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Da02> da02List(
            @RequestParam(required = false) String accNo,
            @RequestParam(required = false) Integer eventNo) {
        Da02 da02 = new Da02();
        Da02PK da02pk = new Da02PK();
        da02pk.setAccNo(accNo);
        da02pk.setEventNo(eventNo);
        da02.setDa02PK(da02pk);
        return da02Service.listAll(da02);
    }

    @RequestMapping(value = "/eventNo")
    public List<Integer> eventNoGroup() {

        return da02Service.eventNoGroup();
    }

}
