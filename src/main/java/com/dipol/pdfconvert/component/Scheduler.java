package com.dipol.pdfconvert.component;

import com.dipol.pdfconvert.data.service.AdobeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class Scheduler {
    @Autowired
    AdobeService adobeService;
    @Autowired
    ArrayList<String> convertList;
    boolean isConvert=false;
//    @Scheduled(cron = "0 * 9 * * ?")
//    public void cronJobSch() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//        Date now = new Date();
//        String strDate = sdf.format(now);
//        System.out.println("Java cron job expression:: " + strDate);
//    }

    @Scheduled(fixedDelay = 10000)
    public void scheduleFixedRateTask() throws IOException, InterruptedException {
        System.out.println(convertList.size());
        if(convertList.size()>0 && !isConvert) {
            isConvert=true;
            System.out.println(
                    "Fixed rate task - " + System.currentTimeMillis() / 1000);
            Iterator iter = convertList.iterator();
            while(iter.hasNext()) {
                String fileName= (String) iter.next();
                adobeService.convert("/Users/fnthero/work/pdfConvert/target/classes/upload",fileName);
                //convertList.remove(iter);
                iter.remove();
            }
           isConvert=false;
        }
    }
}
