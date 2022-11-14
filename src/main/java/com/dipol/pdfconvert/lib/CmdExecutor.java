package com.dipol.pdfconvert.lib;

import com.dipol.pdfconvert.property.FileStorageProperties;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Executors;

public class CmdExecutor {
    private FileStorageProperties fileStorageProperties;
    public CmdExecutor(FileStorageProperties fileStorageProperties)
    {
        this.fileStorageProperties=fileStorageProperties;
    }
    public void runtime(boolean isWindows) throws IOException, InterruptedException
    {
        String uploadPath = "/";;
        URL uploadPathResource =getClass().getResource(uploadPath);
        String path=uploadPathResource.getPath();
        Path fileRealPath = Paths.get(path+fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        String homeDirectory=fileRealPath.toString();
        System.out.println("home directory is"+homeDirectory);

        Process process;
        if(isWindows) {
            process=Runtime.getRuntime().exec(String.format("cmd.exe /c dir %s", homeDirectory));
        } else {
            process=Runtime.getRuntime().exec(String.format("sh -c ls -l %s", homeDirectory));
        }
        StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
        Executors.newSingleThreadExecutor().submit(streamGobbler);
        int exitCode=process.waitFor();
        assert exitCode==0;
    }

    public void processBuilder(boolean isWindows) throws IOException, InterruptedException
    {
        String uploadPath = "/";;
        URL uploadPathResource =getClass().getResource(uploadPath);
        String path=uploadPathResource.getPath();
        Path fileRealPath = Paths.get(path+fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

        System.out.println("::START");
        String homeDirectory=fileRealPath.toString();
        System.out.println("home directory is"+homeDirectory);

        ProcessBuilder builder = new ProcessBuilder();
//        builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
//        builder.redirectError(ProcessBuilder.Redirect.INHERIT);

        builder.directory(new File(homeDirectory));
        if(isWindows) {
            builder.command("cmd.exe", "/c", "dir");
        } else {
            builder.command("sh", "-c", "ls -l");
        }
        Process process = builder.start();

        StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
        Executors.newSingleThreadExecutor().submit(streamGobbler);

        int exitCode=process.waitFor();
        assert exitCode==0;

    }
}
