package com.dipol.pdfconvert.config;

import com.dipol.pdfconvert.data.entity.Da01;
import com.dipol.pdfconvert.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@Configuration
@ComponentScan
public class WebContextConfiguration implements WebMvcConfigurer {
        private static final String CLASSPATH_RESOURCE_LOCATIONS = "classpath:/";
        private final ArrayList<Da01> convertList = new ArrayList<>();

        @Autowired
        JdbcTemplate jdbcTemplate;

        @Bean
        public String databaseType() {
                String databaseProductName = "";
                String dataBase = "";
                try {
                        Connection connection = jdbcTemplate.getDataSource().getConnection();
                        databaseProductName = connection.getMetaData().getDatabaseProductName();
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                if (databaseProductName.equals("Microsoft SQL Server")) {
                        dataBase = "mssql";
                } else if (databaseProductName.equals("MySQL")) {
                        dataBase = "mysql";
                }
                return dataBase;
        }

        @Bean
        public ArrayList<Da01> getConvertList() {
                return convertList;
        }

        @Bean
        public ViewResolver getViewResolver() {
                InternalResourceViewResolver resolver = new InternalResourceViewResolver();
                resolver.setPrefix("/WEB-INF/view/");
                resolver.setSuffix(".jsp");
                return resolver;
        }

        // @Bean(name = "multipartResolver")
        // public CommonsMultipartResolver multipartResolver() {
        // CommonsMultipartResolver resolver=new CommonsMultipartResolver();
        // resolver.setDefaultEncoding("utf-8");
        // resolver.setResolveLazily(false);
        // return resolver;
        // }

        @Bean
        public CharacterEncodingFilter characterEncodingFilter() {
                CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
                characterEncodingFilter.setEncoding("UTF-8");
                characterEncodingFilter.setForceEncoding(true);
                return characterEncodingFilter;

        }

        /**
         * 언어 변경을 위한 인터셉터를 생성한다.
         */

        // @Bean
        // public LocaleResolver localeResolver() {
        // CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        // localeResolver.setCookieName("language");
        // localeResolver.setDefaultLocale(Locale.ENGLISH);
        // return localeResolver;
        // }

        @Bean
        public LocaleChangeInterceptor localeChangeInterceptor() {
                LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
                interceptor.setParamName("language");
                return interceptor;
        }

        @Autowired
        @Qualifier(value = "authenticationInterceptor")
        private AuthenticationInterceptor authenticationInterceptor;

        @Override
        public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                                .allowedOrigins("http://localhost:3000", "*")
                                .allowedHeaders("*")
                                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "TRACE", "OPTIONS",
                                                "PATCH");
        }

        @Override // 인터셉터 등록
        public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(authenticationInterceptor)
                                .addPathPatterns("/**")
                                .excludePathPatterns("/css/**")
                                .excludePathPatterns("/images/**")
                                .excludePathPatterns("/js/**")
                                .excludePathPatterns("/fonts/**")
                                .excludePathPatterns("/actor/**")
                                .excludePathPatterns("/works/**")
                                .excludePathPatterns("/bower_components/**")
                                .excludePathPatterns("/data/**");
                registry.addInterceptor(localeChangeInterceptor())
                                .addPathPatterns("/**");
                // registry.addInterceptor(localeChangeInterceptor());

                // .addPathPatterns(URL_PATTERNS) //이런식으로 배열로도 가능하고,
                // .excludePathPatterns("/원하는패턴1") //1개1개씩 추가도 가능하다.
                // .excludePathPatterns("/원하는패턴2");
        }

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/css/**")
                                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "static/css/")
                                .setCachePeriod(20);
                registry.addResourceHandler("/images/**")
                                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "static/images/")
                                .setCachePeriod(20);
                registry.addResourceHandler("/js/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "static/js/")
                                .setCachePeriod(20);
                registry.addResourceHandler("/fonts/**")
                                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "static/fonts/")
                                .setCachePeriod(20);
                registry.addResourceHandler("/actor/**")
                                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "static/actor/")
                                .setCachePeriod(20);
                registry.addResourceHandler("/works/**")
                                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "static/works/")
                                .setCachePeriod(20);
                registry.addResourceHandler("/bower_components/**")
                                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "bower_components/")
                                .setCachePeriod(20);
                registry.addResourceHandler("/data/**")
                                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "static/data/")
                                .setCachePeriod(20);
                registry.addResourceHandler("/resources/**")
                                .addResourceLocations("/WEB-INF/resources/");

                registry.addResourceHandler("swagger-ui.html")
                                .addResourceLocations("classpath:/META-INF/resources/");

                registry.addResourceHandler("/webjars/**")
                                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        }
}
