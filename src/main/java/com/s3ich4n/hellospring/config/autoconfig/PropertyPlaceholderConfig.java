package com.s3ich4n.hellospring.config.autoconfig;

import com.s3ich4n.hellospring.config.MyAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@MyAutoConfiguration
public class PropertyPlaceholderConfig {
    // 스프링 컨테이너의 확장 포인트
    //      Bean 이 정의하는 기본 정보를 모은 후 후처리를 한다
    @Bean
    PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
