package com.s3ich4n.hellospring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class HelloApiTest {
    @Test
    void helloApi() {
        TestRestTemplate rest = new TestRestTemplate();

        // 타입 리턴도 지정해줌
        //      옛날엔 바인딩이라고했고, 지금은 컨버팅(메시지 컨버터를 썼으니까)
        ResponseEntity<String> res =
                rest.getForEntity("http://localhost:8080/app/hello?name={name}", String.class, "Spring");

        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        Assertions.assertThat(res.getBody()).isEqualTo("*Hello Spring*");
    }

    @Test
    void failsHelloApi() {
        TestRestTemplate rest = new TestRestTemplate();

        // 리스폰스는 보통 지정 안하면 500이 나감
        ResponseEntity<String> res =
                rest.getForEntity("http://localhost:8080/app/hello?name=", String.class);

        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
