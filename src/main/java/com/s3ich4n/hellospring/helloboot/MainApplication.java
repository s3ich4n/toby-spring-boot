package com.s3ich4n.hellospring.helloboot;

import com.s3ich4n.hellospring.config.MySpringBootApplication;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@MySpringBootApplication
// 이 애플리케이션 자체도 스프링 빈이다. 그래서 다른 빈으로 주입받을 수 있다.
public class MainApplication {
    private final JdbcTemplate jdbcTemplate;

    public MainApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 자바 라이프사이클 인터페이스와 연관있다
    //      스프링 프레임워크의 모든 환경구성을 다 마치고 실행하는 기능을 의미
    @PostConstruct
    void init() {
        jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
    }

    public static void main(String[] args) {

        SpringApplication.run(MainApplication.class, args);
    }
}
