package com.s3ich4n.hellospring.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;


// BeanClassLoaderAware 를 implement 해서 써도 됨.
public class MyAutoConfigImportSelector implements DeferredImportSelector {

    // 스프링 부트가 클래스를 로드할 때 쓰는 변수.
    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> autoConfigs = new ArrayList<>();

        // for(String candidate : ImportCandidates.load(MyAutoConfiguration.class, classLoader)) {
        //     autoConfigs.add(candidate);
        // }

        // 이 annotation 이름과 매칭되는 파일에서 설정값을 읽어옴을 알 수 있다.
        //      String location = String.format("META-INF/spring/%s.imports", annotation.getName());
        //       요 로직 보이죠? 왜 썼나 알 수 있음
        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add);

        // return autoConfigs.toArray(String[]::new);
        // return Arrays.copyOf(autoConfigs.toArray(), autoConfigs.size(), String[].class);
        return autoConfigs.toArray(new String[0]);      // 이 방법이 가장 대중적
    }
}
