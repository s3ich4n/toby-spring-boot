package com.s3ich4n.hellospring.config;


import com.s3ich4n.hellospring.config.autoconfig.DispatcherServletConfig;
import com.s3ich4n.hellospring.config.autoconfig.TomcatWebServerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({TomcatWebServerConfig.class, DispatcherServletConfig.class})
public @interface EnableMyAutoConfiguration {
}
