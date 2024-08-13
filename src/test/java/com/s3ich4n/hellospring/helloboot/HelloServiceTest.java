package com.s3ich4n.hellospring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {
    @Test
    void simpleHelloService() {
        // 이 테스트는 DB 조회의 관심사와 다른 관심사에 집중한다
        //      서비스의 메소드 기능을 보고싶은 것이기 때문.
        //      이 경우는 DI를 하기 위해 실행만 적당히 되도록 객체를 만들어주면 된다
        //      (이를 협력 오브젝트라고 부른다)
        SimpleHelloService helloService = new SimpleHelloService(helloRepositoryStub);

        String ret = helloService.sayHello("Test");

        Assertions.assertThat(ret).isEqualTo("Hello Test");
    }

    private static HelloRepository helloRepositoryStub = new HelloRepository() {
        @Override
        public Hello findHello(String name) {
            return null;
        }

        @Override
        public void increaseCount(String name) {

        }
    };

    @Test
    void helloDecorator() {
        // 간단히 람다식을 넣어서 테스트해보자.
        //      여기서는 구현할 메소드가 여러개니까 할 수 없다!
        //      따라서 그냥 넘어감.
//        HelloDecorator decorator = new HelloDecorator(name -> name);

//        var ret = decorator.sayHello("Test");

//        Assertions.assertThat(ret).isEqualTo("*Test*");
    }
}
