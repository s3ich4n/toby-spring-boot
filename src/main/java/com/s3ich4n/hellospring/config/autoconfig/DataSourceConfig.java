package com.s3ich4n.hellospring.config.autoconfig;

import com.s3ich4n.hellospring.config.ConditionalMyOnClass;
import com.s3ich4n.hellospring.config.EnableMyConfigurationProperties;
import com.s3ich4n.hellospring.config.MyAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


// enable -> 구성정보를 가진 클래스를 import 하는 것을 의미
@MyAutoConfiguration
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations")
@EnableMyConfigurationProperties(MyDataSourceProperties.class)
@EnableTransactionManagement
public class DataSourceConfig {
    // 이게 없으면 위의 메소드도 실행되지 못하게 하려면...?
    @Bean
    @ConditionalMyOnClass("com.zaxxer.hikari.HikariDataSource") // HikariDataSource 가 있어야...
    @ConditionalOnMissingBean
    DataSource hikariDataSource(MyDataSourceProperties properties) {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setJdbcUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());

        return dataSource;
    }

    @Bean
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

    // @ConditionalOnSingleCandidate?
    // Bean 메소드가 실행될 때 스프링 컨테의 구성정보에 파라미터 내 Class가 하나만 등록되어있다면, 가져와서 쓰겠다
    // 트랜잭션 경계는 @Transactional 을 쓰는데, 이거는 PlatformTransactionManager 라는 인터페이스를 구현해야함
    // 그건 상단의 @EnableTransactionManagement 로 처리하는 모양
    @Bean
    @ConditionalOnSingleCandidate(DataSource.class)
    @ConditionalOnMissingBean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @ConditionalOnSingleCandidate(DataSource.class)
    @ConditionalOnMissingBean
    JdbcTransactionManager transactionManager(DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }

}
