package com.s3ich4n.hellospring.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    // 여기선 Configuration 클래스들을 다 넣고, 후보로 잡아두는 것
    // 다 쓸건 아니니까
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {
            "com.s3ich4n.hellospring.config.autoconfig.DispatcherServletConfig",
            "com.s3ich4n.hellospring.config.autoconfig.TomcatWebServerConfig",
        };
    }
}
