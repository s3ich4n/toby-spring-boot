package com.s3ich4n.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan
public class MainApplication {

    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    public static void main(String[] args) {
        // 여기 전달받는 클래스의 조건
        //      1. @Configuration 이 붙은 클래스
        //      2. 컴포넌트 스캔, Factory 메소드를 가지고 스프링 컨테이너에게
        //          애플리케이션 구성을 어떻게 할지 알려주는 정보를 갖고있는 클래스
        //      3. 커맨드라인의 옵션도 받을 수 있어야 함
        MySpringApplication.run(MainApplication.class, args);
    }

//    놀랍도록 유사하군.....
//    public static void main(String[] args) {
//        SpringApplication.run(MainApplication.class, args);
//    }
}
