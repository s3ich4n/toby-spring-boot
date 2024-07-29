package com.s3ich4n.hellospring.config.autoconfig;

import com.s3ich4n.hellospring.config.ConditionalMyOnClass;
import com.s3ich4n.hellospring.config.MyAutoConfiguration;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@MyAutoConfiguration
@ConditionalMyOnClass("org.eclipse.jetty.server.Server")
public class JettyWebServerConfig {
    @Bean("jettyWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory() {

        return new JettyServletWebServerFactory();
    }
}
