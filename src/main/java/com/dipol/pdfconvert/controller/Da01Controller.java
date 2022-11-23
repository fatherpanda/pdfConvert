package com.dipol.pdfconvert.controller;

import com.dipol.pdfconvert.data.entity.Da01;
import com.dipol.pdfconvert.data.service.Da01Service;
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
@RequestMapping("/api/da01")
public class Da01Controller {
    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    FileStorageProperties fileStorageProperties;
    @Autowired
    ArrayList<Da01> convertList;
    @Autowired
    Da01Service da01Service;

    @RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
    public Page<Da01> da01List(@PageableDefault(size = 100) Pageable pageable) {
        return da01Service.list(pageable);
    }

    @RequestMapping(value = { "", "/" }, method = RequestMethod.DELETE)
    public Map<String, Object> da01Delete(@RequestBody List<String> idList) {
        boolean isResult = da01Service.delete(idList);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", isResult);
        return resultMap;
    }

    @RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
    public Map<String, Object> da01Write(Da01 da01, @RequestParam("pdfFile") MultipartFile pdfFile,
            HttpServletRequest request) throws URISyntaxException, IOException {
        String uuidFilename = fileStorageService.storeFile(pdfFile);

        // while (!operation.isDone());
        String userId = "";
        // da01.setStatusFlag("10"); //10임시등록 20항목처리중 30 처리완료
        da01.setAddDate(new Date());
        da01.setAddIp(request.getRemoteAddr());
        da01.setAddUser(userId);
        da01.setUpdateDate(new Date());
        da01.setUpdateIp(request.getRemoteAddr());
        da01.setUpdateUser(userId);
        da01.setDocPath(uuidFilename);
        da01Service.write(da01);
        convertList.add(da01);

        boolean isResult = false;
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", isResult);
        return resultMap;
    }

}
