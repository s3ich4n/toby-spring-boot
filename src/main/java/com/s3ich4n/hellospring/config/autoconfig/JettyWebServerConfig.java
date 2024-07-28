package com.s3ich4n.hellospring.config.autoconfig;

import com.s3ich4n.hellospring.config.ConditionalMyOnClass;
import com.s3ich4n.hellospring.config.MyAutoConfiguration;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

@MyAutoConfiguration
@ConditionalMyOnClass("org.eclipse.jetty.server.Server")
public class JettyWebServerConfig {
    // Bean 오브젝트의 이름은 아래와 같이 팩토리 메소드를 쓰면 메소드이름으로 쓰는데
    // 충돌날 수 있으니 다르게 주자
    @Bean("jettyWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory() {

        return new JettyServletWebServerFactory();
    }

    // 어떤 기준으로 "선택"할건지 정해주고 리턴한다.
    // 이를 위해 컨텍스트, 메타데이터를 제공한다
    //
    //      이것보단 어노테이션으로 처리하는 편이 더 깔끔하다 -> 하드코딩을 피할 수 있고, 조건추가 가능
    // static class JettyCondition implements Condition {
    //     @Override
    //     public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    //         return ClassUtils.isPresent("org.eclipse.jetty.server.Server", context.getClassLoader());
    //     }
    // }
}
