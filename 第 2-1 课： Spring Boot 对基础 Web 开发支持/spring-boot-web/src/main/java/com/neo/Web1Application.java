package com.neo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 * @program: springbootleaning
 * @description: 启动类方式修改springboot 内置tomcat端口
 * @author: YePengFei
 * @create: 2020-09-18 14:11
 **/

/**
 * EmbeddedServletContainerCustomizer（嵌入式servlet容器定制）这个在spring boot2.X的版本中就不再提供.
 *
 * **/
@SpringBootApplication
public class Web1Application {
    public static void main(String[] args) {
        SpringApplication.run(Web1Application.class, args);
    }
    //通过代码方式修改Servlet端口的方式
//    @Bean
//    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryWebServerFactoryCustomizer() {
//        return new WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>() {
//            @Override
//            public void customize(ConfigurableServletWebServerFactory factory) {
//                factory.setPort(8081);
//            }
//        };
//    }


}
