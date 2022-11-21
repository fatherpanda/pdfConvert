package com.dipol.pdfconvert.controller;

import com.dipol.pdfconvert.data.service.AdobeService;
import com.dipol.pdfconvert.data.service.FileStorageService;

import com.dipol.pdfconvert.property.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.Future;

@Controller
public class MainController {
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private AdobeService adobeService;
    @Autowired
    FileStorageProperties fileStorageProperties;

    @RequestMapping("/test")
    public String index() {
        return "index";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public Map<String, String> upload(@RequestParam("file") MultipartFile file) throws IOException, URISyntaxException {
        // String fileName = fileStorageService.storeFile(file);
        String uploadPath = "/";
        ;
        URL uploadPathResource = getClass().getResource(uploadPath);
        URI path = uploadPathResource.toURI();

        Path fileRealPath = Paths.get(path.getPath() + fileStorageProperties.getUploadDir()).toAbsolutePath()
                .normalize();
        UUID uuid = UUID.randomUUID();
        String uuidFilename = uuid + "_" + file.getOriginalFilename();

        Path filePath = Paths.get(fileRealPath + "/" + uuidFilename);
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(filePath, StandardOpenOption.WRITE);

        ByteBuffer buffer = ByteBuffer.allocate((int) file.getSize());

        buffer.put(file.getBytes());
        buffer.flip();

        Future<Integer> operation = fileChannel.write(buffer, 0);
        buffer.clear();

        while (!operation.isDone())
            ;

        // System.out.println(filePath);
        // adobeService.convert(fileRealPath.toString(), uuidFilename);
        // adobeService.executor("","");

        Map<String, String> map = new HashMap<>();
        // map.put("filename",fileName);
        return map;
    }

}
