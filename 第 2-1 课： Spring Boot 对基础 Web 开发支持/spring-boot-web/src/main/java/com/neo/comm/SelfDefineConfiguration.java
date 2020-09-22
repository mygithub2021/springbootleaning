package com.neo.comm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: springbootleaning
 * @description: 从配置文件中读取指定内置tomcat接口
 * @author: YePengFei
 * @create: 2020-09-18 14:51
 **/
@Configuration
public class SelfDefineConfiguration {
    //从配置文件获取端口值
    @Value("${tomcatport:8085}")
    private int port;

    //实例化对象，使用端口初始化容器
    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        return new TomcatServletWebServerFactory(this.port);
    }
}
