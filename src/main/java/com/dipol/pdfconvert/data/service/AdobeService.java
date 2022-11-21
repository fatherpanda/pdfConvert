package com.dipol.pdfconvert.data.service;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.auth.Credentials;
import com.adobe.pdfservices.operation.exception.SdkException;
import com.adobe.pdfservices.operation.exception.ServiceApiException;
import com.adobe.pdfservices.operation.exception.ServiceUsageException;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.ExtractPDFOperation;
import com.adobe.pdfservices.operation.pdfops.options.extractpdf.ExtractElementType;
import com.adobe.pdfservices.operation.pdfops.options.extractpdf.ExtractPDFOptions;
import com.adobe.pdfservices.operation.pdfops.options.extractpdf.ExtractRenditionsElementType;
import com.adobe.pdfservices.operation.pdfops.options.extractpdf.TableStructureType;
import com.dipol.pdfconvert.lib.CmdExecutor;
import com.dipol.pdfconvert.property.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public class AdobeService {
    @Autowired
    FileStorageProperties fileStorageProperties;

    public void executor(String filePath, String fileName) throws IOException, InterruptedException {
        // adobeService.convert(fileStorageService.fileStorageLocation.toString(),
        // fileName);
        CmdExecutor cmdExecutor = new CmdExecutor(fileStorageProperties);
        boolean isWindow = System.getProperty("os.name").toLowerCase().startsWith("windows");
        System.out.println("::this OS " + (isWindow ? "window" : "mac"));
        cmdExecutor.processBuilder(isWindow);
        // cmdExecutor.processBuilder(isWindow);
    }

    public String convert(String filePath, String fileName) {
        String fileNameZip = "";
        Credentials credentials = null;
        try {
            fileNameZip = fileName.substring(0, fileName.lastIndexOf(".")) + ".zip";
            credentials = Credentials.serviceAccountCredentialsBuilder()
                    .fromFile("pdfservices-api-credentials.json")
                    .build();
            // Create an ExecutionContext using credentials.
            ExecutionContext executionContext = ExecutionContext.create(credentials);

            ExtractPDFOperation extractPDFOperation = ExtractPDFOperation.createNew();

            // Provide an input FileRef for the operation
            FileRef source = FileRef.createFromLocalFile(filePath + "/" + fileName);
            extractPDFOperation.setInputFile(source);

            // Build ExtractPDF options and set them into the operation
            ExtractPDFOptions extractPDFOptions = ExtractPDFOptions.extractPdfOptionsBuilder()
                    .addElementsToExtract(Arrays.asList(ExtractElementType.TEXT, ExtractElementType.TABLES))
                    .addElementToExtractRenditions(ExtractRenditionsElementType.TABLES)
                    .addTableStructureFormat(TableStructureType.CSV)
                    .build();
            extractPDFOperation.setOptions(extractPDFOptions);

            // Execute the operation
            FileRef result = extractPDFOperation.execute(executionContext);

            // Save the result at the specified location
            result.saveAs(filePath + "/" + fileNameZip);
        } catch (ServiceApiException | IOException | SdkException | ServiceUsageException ex) {
            System.out.println("Exception encountered while executing operation" + ex);
        }
        return fileNameZip;
    }

}
