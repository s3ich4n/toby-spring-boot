package com.s3ich4n.hellospring.config;


import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Configuration(proxyBeanMethods = false)    // 이 옵션을 적용. 내가 생성한 Bean들이 proxy로 만들어지지 않음을 의미
public @interface MyAutoConfiguration {

}
