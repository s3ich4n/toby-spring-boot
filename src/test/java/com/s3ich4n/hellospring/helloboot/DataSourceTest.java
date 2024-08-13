package com.s3ich4n.hellospring.helloboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@JdbcTest
public class DataSourceTest {
    @Autowired DataSource dataSource;

    @Test
    void connectionTest() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.close();
    }
}
