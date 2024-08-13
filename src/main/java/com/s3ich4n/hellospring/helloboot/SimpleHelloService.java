package com.s3ich4n.hellospring.helloboot;

import org.springframework.stereotype.Service;

@Service
public class SimpleHelloService implements HelloService {
    private final HelloRepository helloRepository;

    // 이렇게 구성하면 HelloRepository에 의존하게 된다!
    public SimpleHelloService(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    @Override
    public String sayHello(String name) {
        this.helloRepository.increaseCount(name);

        return "Hello " + name;
    }

    @Override
    public int countOf(String name) {
        return helloRepository.countOf(name);
    }
}
