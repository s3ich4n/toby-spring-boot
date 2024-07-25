package com.s3ich4n.hellospring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationTest {
    @Test
    void configuration() {
        // Common common = new Common();

        // Assertions.assertThat(common).isSameAs(common);
        // 같다고 나오고

        // MyConfig myConfig = new MyConfig();

        // Bean1 bean1 = myConfig.bean1();
        // Bean2 bean2 = myConfig.bean2();

        // Assertions.assertThat(bean1.common).isSameAs(bean2.common);
        // 당연히 다르다고 나온다!

        // SpringContainer 의 구성정보로 쓰면 또 다르다..!


        // 스프링 안에서 Bean으로 만들어서 쓰일 때와는 자바 본연의 동작과 조금다르다
        //      (서로다른 객체 둘을 동일비교를 하는데, 어떻게 이게 같냐 이거죠)
        // 이는 Configuration의 Proxy Bean 메소드가 우선 처리되기 때문에 그렇다
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfig.class);
        ac.refresh();

         Bean1 bean1 = ac.getBean(Bean1.class);
         Bean2 bean2 = ac.getBean(Bean2.class);

         Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }

    @Test
    void proxyCommonMethod() {
        // Proxy는 확장해서 대체하는 것. 데코레이터와는 다르다
        MyConfigProxy myConfigProxy = new MyConfigProxy();

        Bean1 bean1 = myConfigProxy.bean1();
        Bean2 bean2 = myConfigProxy.bean2();

        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }

    static class MyConfigProxy extends MyConfig {
        private Common common;

        @Override
        Common common() {
            if (this.common == null) this.common = super.common();

            return this.common;
        }
    }

//     @Configuration(proxyBeanMethods = false)
    @Configuration
    static class MyConfig {
        @Bean
        Common common() {
            return new Common();
        }

        @Bean
        Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        Bean2 bean2() {
            return new Bean2(common());
        }
    }

    static class Bean1 {
        private final Common common;

        Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2 {
        private final Common common;

        Bean2(Common common) {
            this.common = common;
        }
    }

    static class Common { }

}
