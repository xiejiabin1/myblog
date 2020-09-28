package com.xie.myblog.util;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description: Swagger2配置类
 * @author: 谢
 * @time: 2020/7/27 0:27
 */
@EnableKnife4j
@EnableSwagger2
@Configuration
@Import(value = {BeanValidatorPluginsConfiguration.class})
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())// 可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 标题
                .title("先谢郭嘉博客的Swagger API文档")
                // 描述
                .description("使用Knife4j构建API文档")
                // 作者信息
                .contact(new Contact("先谢郭嘉", "https://xiejiabin.online/", "xie430423@163.com"))
                // 服务网址
                .termsOfServiceUrl("https://xiejiabin.online/")
                // 版本
                .version("1.0")
                .build();
    }

}

