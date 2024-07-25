package com.s3ich4n.hellospring.helloboot;

import com.s3ich4n.hellospring.HelloDecorator;
import com.s3ich4n.hellospring.SimpleHelloService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {
    @Test
    void simpleHelloService() {
        SimpleHelloService helloService = new SimpleHelloService();

        String ret = helloService.sayHello("Test");


        Assertions.assertThat(ret).isEqualTo("Hello Test");
    }

    @Test
    void helloDecorator() {
        // 간단히 람다식을 넣어서 테스트해보자.
        HelloDecorator decorator = new HelloDecorator(name -> name);

        var ret = decorator.sayHello("Test");

        Assertions.assertThat(ret).isEqualTo("*Test*");
    }
}
