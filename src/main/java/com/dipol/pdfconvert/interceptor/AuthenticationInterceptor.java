package com.dipol.pdfconvert.interceptor;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.util.Collection;
import java.util.Properties;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession();
        String RequestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = RequestURI.substring(contextPath.length());
        String url = request.getScheme()+"://"+request.getServerName();
        url += request.getServerPort() != 80 ? ":"+request.getServerPort() : "";
        url += request.getContextPath();


//        response.setHeader("Access-Control-Allow-Origin", "http://localhost:80, http://localhost:3000");
////        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
//        response.setHeader("Access-Control-Allow-Methods", "*");
//        response.setHeader("Access-Control-Allow-Credentials", "true");

        return true;
    }

    // 컨트롤러가 수행되고 화면이 보여지기 직전에 수행되는 메서드
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception
    {
        //System.out.println(sessionInfo);

    }


    private void addSameSite(HttpServletResponse response, String sameSite)
    {
        Collection<String> headers = response.getHeaders(HttpHeaders.SET_COOKIE);
        boolean firstHeader=true;
        for(String header: headers)
        {
            if(firstHeader) {
                response.setHeader(HttpHeaders.SET_COOKIE, String.format("%s; Secure; %s", header, "SameSite="+sameSite));
                firstHeader=false;
                continue;
            }
            response.addHeader(HttpHeaders.SET_COOKIE,  String.format("%s; Secure; %s", header, "SameSite="+sameSite));
        }
    }

    public Properties getProperties(String propertie_name) throws FileNotFoundException {
        String uploadPath = "/";
        URL uploadPathResource =getClass().getResource(uploadPath);
        Properties propertie = new Properties();
        try {
            File profile=new File(uploadPathResource.getPath()+propertie_name);
            if(profile.exists()) {
                InputStream is = new FileInputStream(profile.getAbsolutePath());
                propertie.load(is);
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return propertie;
    }

    public void setProperties(String propertie_name, Properties propertie)
    {
        ClassLoader classLoader = getClass().getClassLoader();
        String uploadPath = "static/data/";;
        URL uploadPathResource =classLoader.getResource(uploadPath);
        String path=uploadPathResource.getPath();
        //String path=getRootPath()+"/data/"+propertie_name;
        try {
            File file = new File(path);
            if(file.exists()==false) file.mkdir();
            String comments=propertie.getProperty("comment","");
            OutputStream output=new FileOutputStream(path+propertie_name);
            propertie.store(output, comments);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
