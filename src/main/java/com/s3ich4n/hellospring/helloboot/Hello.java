package com.s3ich4n.hellospring.helloboot;

public class Hello {
    private String name;
    private int count;

    public Hello(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }
}
