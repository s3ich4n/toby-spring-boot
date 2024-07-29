package com.s3ich4n.hellospring.config.autoconfig;

import com.s3ich4n.hellospring.config.ConditionalMyOnClass;
import com.s3ich4n.hellospring.config.MyAutoConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfig {
    // BeanFactoryPostProcessor 라는 후처리기를 Bean 으로 등록해야 돌아간다.
    @Value ("${contextPath}")
    String contextPath;

    @Bean("tomcatWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();

        factory.setContextPath(this.contextPath);
        return factory;
    }
}
