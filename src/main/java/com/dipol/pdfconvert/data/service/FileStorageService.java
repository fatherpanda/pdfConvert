package com.dipol.pdfconvert.data.service;

import com.dipol.pdfconvert.exception.FileStorageException;
import com.dipol.pdfconvert.exception.MyFileNotFoundException;
import com.dipol.pdfconvert.property.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
    public final Path fileStorageLocation;


    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) throws IOException, URISyntaxException {
        String uploadPath = "/";;
        URL uploadPathResource =getClass().getResource(uploadPath);
        URI uri=uploadPathResource.toURI();

//        String path=Paths.get(Paths.get(uri).toAbsolutePath().toString()+"/"+fileStorageProperties.getUploadDir());
        this.fileStorageLocation = Paths.get(Paths.get(uri).toAbsolutePath().toString()+"/"+fileStorageProperties.getUploadDir());
//        try{
            Files.createDirectories(this.fileStorageLocation);
//        }catch(Exception ex){
//            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
//        }
    }

    public String storeFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

//        try{
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
//        }catch(IOException ex){
//            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
//        }
    }

    public Resource loadFileAsResource(String fileName) {
        try{
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if(resource.exists()){
                return resource;
            }else{
                throw new MyFileNotFoundException("File not found" + fileName);
            }
        }catch(MalformedURLException ex){
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}
