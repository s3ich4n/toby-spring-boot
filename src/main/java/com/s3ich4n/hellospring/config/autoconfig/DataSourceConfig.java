package com.s3ich4n.hellospring.config.autoconfig;

import com.s3ich4n.hellospring.config.ConditionalMyOnClass;
import com.s3ich4n.hellospring.config.EnableMyConfigurationProperties;
import com.s3ich4n.hellospring.config.MyAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

import com.zaxxer.hikari.HikariDataSource;

@MyAutoConfiguration
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations")
@EnableMyConfigurationProperties(MyDataSourceProperties.class)
public class DataSourceConfig {
    @Bean
    @ConditionalMyOnClass("com.zaxxer.hikari.HikariDataSource") // HikaraDataSource가 있어야...
    @ConditionalOnMissingBean
    DataSource dataSource(MyDataSourceProperties properties) throws ClassNotFoundException {
        // 매번 커넥션을 맺는다.
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        // 클래스의 이름으로 찾기 위해 Class.forName을 씀.
        //      근데 없을 수도 있으니 예외 발생시키게 처리
        // 드라이버 타입으로 캐스팅 필요
        dataSource.setDriverClass((Class<? extends Driver>) Class.forName(properties.getDriverClassName()));
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());

        return dataSource;
    }

    // 이게 없으면 위의 메소드도 실행되지 못하게 하려면...?
    @Bean
    @ConditionalOnMissingBean
    DataSource hikariDataSource(MyDataSourceProperties properties) {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setJdbcUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());

        return dataSource;
    }
}
