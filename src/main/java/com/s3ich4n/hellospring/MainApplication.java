package com.s3ich4n.hellospring;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MainApplication {

    public static void main(String[] args) {
        // 스프링 컨테이너를 만들고, 등록하고, 사용시작을 준비한다
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh();

        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("dispatcherServlet",
                    // 웹 애플리케이션 컨텍스트 타입을 줘야함
                    new DispatcherServlet(applicationContext)
                    ).addMapping("/*");
        });
        webServer.start();
    }
}
