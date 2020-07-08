package com.xie.myblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@ServletComponentScan
@SpringBootApplication
@MapperScan("com.xie.myblog.dao")
public class MyblogApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        try{
            SpringApplication.run(MyblogApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //用于构建war文件并进行部署
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MyblogApplication.class);
    }
}
