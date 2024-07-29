package com.s3ich4n.hellospring.helloboot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


// 스프링 컨테이너를 띄우고, 구성정보를 넣고, Bean을 가져와서 테스트하자
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MainApplication.class)
@TestPropertySource("classpath:/application.properties")
public class DataSourceTest {
    @Autowired
    DataSource dataSource;

    @Test
    void connectionTest() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.close();
    }
}
