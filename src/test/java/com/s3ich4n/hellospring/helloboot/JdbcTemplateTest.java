package com.s3ich4n.hellospring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@HellobootTest
public class JdbcTemplateTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void init() {
        // 메모리에서 돌릴 예정. 내장형 DB 돌릴 때는 DB 생성을 하도록 해야함
        //      실제로는 더 효율적으로 해야할 것임
        try {
            jdbcTemplate.execute("drop table if exists hello");
            jdbcTemplate.execute("create table hello(name varchar(50) primary key, count int)");
            System.out.println("테이블이 성공적으로 생성되었습니다.");
        } catch (Exception e) {
            System.out.println("테이블 생성 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 지난번에 안됐던 이유? 트랜잭션매니저를 정의하지 않았기 때문
    @Test
    void insert_and_query() {
        jdbcTemplate.update("insert into hello values(?, ?)", "s3ich4n", 3);
        jdbcTemplate.update("insert into hello values(?, ?)", "test", 1);

        Long count = jdbcTemplate.queryForObject("select count(*) from HELLO", Long.class);
        Assertions.assertThat(count).isEqualTo(2L);
    }

}
