package com.s3ich4n.hellospring.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    private final HelloService helloService;

    // 단일 주입후보를 찾으면 그것만 주입시킨다.
    // 원래는 @Autowired 어노테이션을 직접쳤어야했다.
    // 근데 데코레이터를 적용하면? 아래와 같이 달라진다.
    // @Primary가 붙은 HelloDecorator가 대신 주입됨.
    //      일종의 프록시 패턴으로 해석됨
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping
    @ResponseBody
    public String hello(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException();

        return helloService.sayHello(name);
    }
}
