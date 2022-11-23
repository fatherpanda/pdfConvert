package com.dipol.pdfconvert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableScheduling
@EnableWebMvc
@ConfigurationPropertiesScan("com.dipol.pdfconvert.property")
public class PdfConvertApplication {

    public static void main(String[] args) {
        SpringApplication.run(PdfConvertApplication.class, args);
    }

}
