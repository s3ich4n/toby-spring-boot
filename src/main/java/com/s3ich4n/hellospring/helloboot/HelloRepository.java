package com.s3ich4n.hellospring.helloboot;

public interface HelloRepository {
    Hello findHello(String name);

    // 간단한 데이터 조직으로 판단하고, 이를 Repository 레벨에서 구현
    void increaseCount(String name);

    // Java의 Comparator 메소드를 보고 default method 의 감을 잡으시오
    default int countOf(String name) {
        Hello hello = findHello(name);
        return hello == null ? 0 : hello.getCount();
    }
}
