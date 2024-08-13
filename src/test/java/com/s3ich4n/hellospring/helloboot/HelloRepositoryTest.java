package com.s3ich4n.hellospring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@HellobootTest
public class HelloRepositoryTest {
    @Autowired HelloRepository helloRepository;
    @Autowired JdbcTemplate jdbcTemplate;

    @Test
    void findHelloFailed() {
        Assertions.assertThat(helloRepository.findHello("s3ich4n")).isNull();
    }

    @Test
    void testIncreaseCount() {
        Assertions.assertThat(helloRepository.countOf("s3ich4n")).isEqualTo(0);

        helloRepository.increaseCount("s3ich4n");
        Assertions.assertThat(helloRepository.countOf("s3ich4n")).isEqualTo(1);

        helloRepository.increaseCount("s3ich4n");
        Assertions.assertThat(helloRepository.countOf("s3ich4n")).isEqualTo(2);
    }
}
