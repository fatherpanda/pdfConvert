package com.dipol.pdfconvert.data.service;

import com.dipol.pdfconvert.exception.FileStorageException;
import com.dipol.pdfconvert.exception.MyFileNotFoundException;
import com.dipol.pdfconvert.property.FileStorageProperties;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class FileStorageService {
    public final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) throws IOException, URISyntaxException {
        String uploadPath = "/";
        URL uploadPathResource = getClass().getResource(uploadPath);
        URI path = uploadPathResource.toURI();

        Path fileRealPath = Paths.get(path.getPath() + fileStorageProperties.getUploadDir()).toAbsolutePath()
                .normalize();
        this.fileStorageLocation = fileRealPath;
        // try{
        Files.createDirectories(this.fileStorageLocation);
        // }catch(Exception ex){
        // throw new FileStorageException("Could not create the directory where the
        // uploaded files will be stored.", ex);
        // }
    }

    public String storeFile(MultipartFile file) throws IOException {
        UUID uuid = UUID.randomUUID();
        System.out.println(file.getName());
        String fileName = StringUtils.cleanPath(uuid + "_" + file.getOriginalFilename());
        // try{
        if (fileName.contains("..")) {
            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
        }

        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
        // }catch(IOException ex){
        // throw new FileStorageException("Could not store file " + fileName + ". Please
        // try again!", ex);
        // }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found" + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

    public void processFile(String fileName) {
        String name = fileName.substring(0, fileName.lastIndexOf(".") - 1);
        Path sourceLocation = this.fileStorageLocation.resolve(fileName);
        Path targetLocation = this.fileStorageLocation.resolve(name);
        unzip(sourceLocation, targetLocation);
        System.out.println(targetLocation);
        // unzip

    }

    public void unzip(Path sourceZip, Path targetDir) {
        try {
            File source = new File(sourceZip.toString());
            String target = targetDir.toString();

            ZipFile zipFile = new ZipFile(source);
            zipFile.extractAll(target);
        } catch (ZipException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Path> getCsvList(Path targetLocation) throws IOException, ParseException {
        Path tablesLocation = targetLocation.resolve("tables");
        Path jsonLocation = targetLocation.resolve("structuredData.json");
        List<Path> returnPaths = new ArrayList<>();
        Reader reader = new FileReader(jsonLocation.toString());
        Object obj = new JSONParser().parse(reader);
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray elementArray = (JSONArray) jsonObject.get("elements");
        for (Object elementObject : elementArray) {
            if (elementObject instanceof JSONObject) {
                JSONObject elementJsonObj = (JSONObject) elementObject;
                if (elementJsonObj.containsKey("filePaths")) {
                    List<String> fileListArray = (List<String>) elementJsonObj.get("filePaths");
                    for (String fileoutName : fileListArray) {
                        if (fileoutName.indexOf("csv") > -1) {
                            returnPaths.add(tablesLocation.resolve(fileoutName));
                        }

                    }

                }
            }
        }

        reader.close();
        return returnPaths;
    }

    public CSVReader readDataFromCsv(String filePath) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(filePath)); // 1
        return reader;

    }
}
