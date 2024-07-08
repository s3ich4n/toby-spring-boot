package com.s3ich4n.hellospring;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;

public class MainApplication {

    public static void main(String[] args) {
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

        // 일관된 방식으로 팩토리에서 꺼내서 추상화 하게하기 위함
        WebServer webServer = serverFactory.getWebServer();
        webServer.start();
    }

}
