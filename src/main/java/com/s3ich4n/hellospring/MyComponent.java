package com.s3ich4n.hellospring;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)     // 생명주기
@Target(ElementType.TYPE)               // 클래스, 인터페이스 같은 타입에 적용됨
@Component                              // 여기 이걸 붙이네?
public @interface MyComponent {
}
