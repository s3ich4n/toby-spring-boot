package com.s3ich4n.hellospring;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import java.io.IOException;

public class MainApplication {

    public static void main(String[] args) {
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

        // 람다식으로 전환.
        //  내가 이렇게 작업하는 녀석은 구현해야할 메소드가 한개뿐이라 가능함
        //  템플릿-콜백 패턴?
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            // 어댑터 패턴이 여기 들어감
            servletContext.addServlet("hello", new HttpServlet() {
                // 웹 요청의 응답을 세팅함. 뭘 만드는지 알아야 한다는 뜻임.
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    resp.setStatus(200);
                    resp.setHeader("Content-Type", "text/plain");
                    resp.getWriter().println("Hello Servlet");
                }
                // url 매핑을 함. 익명클래스로 만든걸 매핑하겠다는 뜻
            }).addMapping("/hello");
        });
        // 서블릿을 구동시킴
        webServer.start();
    }

}
