package com.s3ich4n.hellospring.config.autoconfig;

import com.s3ich4n.hellospring.config.MyAutoConfiguration;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@MyAutoConfiguration
public class ServerPropertiesConfig {
    @Bean
    public ServerProperties serverProperties(Environment environment) {
//        ServerProperties properties = new ServerProperties();
//
//        properties.setContextPath(environment.getProperty("contextPath"));
//        properties.setPort(Integer.parseInt(environment.getProperty("port")));
//
//        return properties;
        return Binder.get(environment).bind("", ServerProperties.class).get();
    }
}
