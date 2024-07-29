package com.s3ich4n.hellospring.config.autoconfig;

import com.s3ich4n.hellospring.config.MyAutoConfiguration;
import com.s3ich4n.hellospring.config.MyConfigurationProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.util.Map;

import static org.springframework.core.annotation.AnnotationUtils.*;

@MyAutoConfiguration
public class PropertyPostProcessorConfig {
    // 모든 Bean을 등록하면 이걸 돌 것이다
    // 그런데, 내가 만든 어떤 어노테이션을 달고있는 모든 빈에만 돌거다
    @Bean
    BeanPostProcessor beanPostProcessor(Environment env) {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                // reflection 을 잘 해야하지만, 스프링 프레임워크는 이걸 또 준비해놨다
                MyConfigurationProperties annotation = findAnnotation(bean.getClass(), MyConfigurationProperties.class);

                if (annotation == null) return bean;

                Map<String, Object> attrs = getAnnotationAttributes(annotation);

                String prefix = (String) attrs.get("prefix");

                // 저 `name` 은 접두사
                return Binder.get(env).bindOrCreate(prefix, bean.getClass());
            }
        };
    }
}
