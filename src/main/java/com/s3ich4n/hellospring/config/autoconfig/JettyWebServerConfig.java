package com.s3ich4n.hellospring.config.autoconfig;

import com.s3ich4n.hellospring.config.MyAutoConfiguration;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;

@MyAutoConfiguration
@Conditional(JettyWebServerConfig.JettyCondition.class)
public class JettyWebServerConfig {
    // Bean 오브젝트의 이름은 아래와 같이 팩토리 메소드를 쓰면 메소드이름으로 쓰는데
    // 충돌날 수 있으니 다르게 주자
    @Bean("jettyWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory() {

        return new JettyServletWebServerFactory();
    }

    // 어떤 기준으로 "선택"할건지 정해주고 리턴한다.
    // 이를 위해 컨텍스트, 메타데이터를 제공한다
    static class JettyCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }
}
