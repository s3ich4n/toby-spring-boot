package com.s3ich4n.hellospring;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

public class MainApplication {

    public static void main(String[] args) {
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

        // 람다식으로 전환.
        //  내가 이렇게 작업하는 녀석은 구현해야할 메소드가 한개뿐이라 가능함
        //  템플릿-콜백 패턴?
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            HelloController helloController = new HelloController();

            // 어댑터 패턴이 여기 들어감
            servletContext.addServlet("hello", new HttpServlet() {
                // 웹 요청의 응답을 세팅함. 뭘 만드는지 알아야 한다는 뜻임.
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    // 공통기능 처리가 여기에 있음
                    // 서블릿 컨테이너의 mapping 기능을 Front Controller 가 해야함
                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals("GET")) {
                        String name = req.getParameter("name");

                        // 이걸 쓸거다 하는 선택: 매핑
                        // 자바 타입으로 (DTO, Java Bean) 형식으로 파라미터를 주는 것: 바인딩
                        // (Spring MVC의 그것은 더 복잡하긴함. 근데 원리는 이런식임)
                        String ret = helloController.hello(name);

                        resp.setStatus(HttpStatus.OK.value());
                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                        resp.getWriter().println(ret);

                    }
                    else if (req.getRequestURI().equals("/user")) {
                        //
                    }
                    else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }
                }
                // url 매핑을 함. 익명클래스로 만든걸 매핑하겠다는 뜻
            }).addMapping("/*");
        });
        // 서블릿을 구동시킴
        webServer.start();
    }

}
