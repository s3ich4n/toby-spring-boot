package com.s3ich4n.hellospring;

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
                rest.getForEntity("http://localhost:8080/hello?name={name}", String.class, "Spring");

        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        Assertions.assertThat(res.getBody()).isEqualTo("Hello Spring");
    }
}
