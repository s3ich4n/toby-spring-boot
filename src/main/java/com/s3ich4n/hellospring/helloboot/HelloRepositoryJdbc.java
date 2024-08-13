package com.s3ich4n.hellospring.helloboot;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HelloRepositoryJdbc implements HelloRepository {
    private final JdbcTemplate jdbcTemplate;

    public HelloRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Hello findHello(String name) {
        // 단일 컬럼 조회. `rowMapper` 인터페이스 구현하면 오브젝트/람다식을 넣으면 자바 클래스에 담긴 형태로 리턴 가능

        try {
            return jdbcTemplate.queryForObject("select * from hello where name = '" + name + "'",
                    (rs, rowNum) -> new Hello(
                            rs.getString("name"),
                            rs.getInt("count")
                    ));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void increaseCount(String name) {
        Hello hello = findHello(name);
        if (hello == null) jdbcTemplate.update("insert into hello values (?, ?)", name, 1);
        else jdbcTemplate.update("update hello set count = ? where name = ?", hello.getCount() + 1, name);
    }
}
