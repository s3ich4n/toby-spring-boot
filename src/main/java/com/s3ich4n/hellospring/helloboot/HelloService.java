package com.s3ich4n.hellospring.helloboot;

public interface HelloService {
    String sayHello(String name);

    default int countOf(String name) { return 0; };
}
