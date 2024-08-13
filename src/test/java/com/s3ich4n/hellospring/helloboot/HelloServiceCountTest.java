package com.s3ich4n.hellospring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class HelloServiceCountTest {
    @Autowired HelloService helloService;
    // 2가지 접근법이 있음
    //      1. jdbctemplate 이용 -> jdbc 템플릿에 주입 (쌩쿼리를 H2에 세팅)
    //      2. Repository를 잘 주입해서 바로 로직을 검증 (Repository 자체에 대한 테스트가 선행되어야함)
    @Autowired HelloRepository helloRepository;

    @Test
    void sayHelloIncreaseTest() {
        // 이렇게 하면 테스트를 n번 돌릴 수 있다!
        IntStream.rangeClosed(1, 10).forEach(count -> {
            helloService.sayHello("s3ich4n");
            Assertions.assertThat(helloRepository.countOf("s3ich4n")).isEqualTo(count);
        });
    }
}
