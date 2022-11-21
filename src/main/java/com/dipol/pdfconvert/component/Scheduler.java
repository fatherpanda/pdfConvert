package com.dipol.pdfconvert.component;

import com.dipol.pdfconvert.data.entity.Ba04;
import com.dipol.pdfconvert.data.entity.Da01;
import com.dipol.pdfconvert.data.entity.Da02;
import com.dipol.pdfconvert.data.entity.Da02PK;
import com.dipol.pdfconvert.data.service.AdobeService;
import com.dipol.pdfconvert.data.service.Ba04Service;
import com.dipol.pdfconvert.data.service.Da01Service;
import com.dipol.pdfconvert.data.service.Da02Service;
import com.dipol.pdfconvert.data.service.FileStorageService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class Scheduler {
    @Autowired
    AdobeService adobeService;
    @Autowired
    ArrayList<Da01> convertList;
    @Autowired
    Da01Service da01Service;
    @Autowired
    Da02Service da02Service;
    @Autowired
    Ba04Service ba04Service;
    @Autowired
    FileStorageService fileStorageService;
    boolean isConvert = false;
    // @Scheduled(cron = "0 * 9 * * ?")
    // public void cronJobSch() {
    // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    // Date now = new Date();
    // String strDate = sdf.format(now);
    // System.out.println("Java cron job expression:: " + strDate);
    // }

    @Scheduled(fixedDelay = 10000)
    public void scheduleFixedRateTask()
            throws IOException, InterruptedException, CsvValidationException, NumberFormatException, ParseException {

        if (convertList.size() > 0 && !isConvert) {
            isConvert = true;
            System.out.println(
                    "Fixed rate task - " + System.currentTimeMillis() / 1000);
            Iterator iter = convertList.iterator();
            while (iter.hasNext()) {
                Da01 da01 = (Da01) iter.next();
                Convert(da01);
                // convertList.remove(iter);
                iter.remove();
            }
            isConvert = false;
        }
    }

    public void Convert(Da01 da01)
            throws IOException, ParseException, CsvValidationException, NumberFormatException {

        String fileNameZip = adobeService.convert(fileStorageService.fileStorageLocation.toString(), da01.getDocPath());
        String name = fileNameZip.substring(0, fileNameZip.lastIndexOf("."));
        Path sourceLocation = fileStorageService.fileStorageLocation.resolve(fileNameZip);
        Path targetLocation = fileStorageService.fileStorageLocation.resolve(name);
        fileStorageService.unzip(sourceLocation, targetLocation);

        List<Ba04> ba04List = ba04Service.list(da01.getReportVersion());

        List<Path> csvPath = fileStorageService.getCsvList(targetLocation);
        List<Da02> da02List = new ArrayList<>();
        for (Path path : csvPath) {
            CSVReader reader = fileStorageService.readDataFromCsv(path.toString());
            String[] nextLine;

            int eventNo = 0;
            String colunmValue = "";
            int rowCnt = 0;
            while ((nextLine = reader.readNext()) != null) { // 2
                String firstColunm = removeUTF8BOM(nextLine[0].replaceAll(" ", "").trim());
                boolean isFirstNumber = false;
                if (!firstColunm.equals("")) {
                    rowCnt = 0;
                    System.out.println(firstColunm);
                    if (firstColunm.equals("이벤트번호카테고리번호신호명값설명")) {
                        isFirstNumber = true;
                    } else {

                        firstColunm = removeUTF8BOM(nextLine[0].replaceAll(" ", "").replace("이벤트번호", ""));
                        eventNo = Integer.parseInt(firstColunm);
                    }

                } else {
                    if (isFirstNumber && rowCnt == 0) {
                        eventNo = Integer.parseInt(nextLine[0]);
                    }
                    Ba04 ba04 = null;
                    if (rowCnt < 4) {
                        ba04 = ba04Search(ba04List, nextLine[2]);
                        colunmValue = nextLine[3].trim();
                    } else {
                        ba04 = ba04Search(ba04List, nextLine[3]);
                        colunmValue = nextLine[4].trim();
                    }

                    Da02 da02 = new Da02();
                    Da02PK da02pk = new Da02PK();
                    da02pk.setAccNo(da01.getAccNo());
                    da02pk.setDssadCode(ba04.getBa04PK().getDssadCode());
                    da02pk.setEventNo(eventNo);
                    da02.setDa02PK(da02pk);
                    da02.setSortOrder(ba04.getSortOrder());
                    da02.setRecodeValue(colunmValue);
                    da02.setAddUser(da01.getAddUser());
                    da02.setUpdateUser(da01.getUpdateUser());
                    da02.setAddIp(da01.getAddIp());
                    da02.setUpdateIp(da01.getUpdateIp());
                    da02List.add(da02);
                    rowCnt++;
                }
            }

        }
        da02Service.writeAll(da02List);

        da01.setStatusFlag("10");
        da01Service.write(da01);
    }

    public Ba04 ba04Search(List<Ba04> ba04List, String searchStr) {
        Ba04 selectBa04 = null;
        for (Ba04 ba04 : ba04List) {
            if (ba04.getOrgCode().equals(searchStr.trim())) {
                selectBa04 = ba04;
                break;
            }
        }
        return selectBa04;

    }

    private String removeUTF8BOM(String s) {
        if (s.startsWith("\uFEFF")) {
            s = s.substring(1);
        }
        return s;
    }
}
