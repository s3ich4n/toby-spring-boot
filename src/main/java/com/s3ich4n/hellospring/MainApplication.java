package com.s3ich4n.hellospring;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

public class MainApplication {

    public static void main(String[] args) {
        // 스프링 컨테이너를 만들고, 등록하고, 사용시작을 준비한다
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBean(HelloController.class);
        applicationContext.refresh();

        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("hello", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals("GET")) {
                        String name = req.getParameter("name");

                        HelloController helloController = applicationContext.getBean(HelloController.class);
                        String ret = helloController.hello(name);

                        resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
                        resp.getWriter().println(ret);
                    }
                    else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }
                }
            }).addMapping("/*");
        });
        webServer.start();
    }

}
