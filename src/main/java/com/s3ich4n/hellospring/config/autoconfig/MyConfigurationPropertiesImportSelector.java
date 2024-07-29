package com.s3ich4n.hellospring.config.autoconfig;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

public class MyConfigurationPropertiesImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        MultiValueMap<String, Object> attrs = importingClassMetadata.getAllAnnotationAttributes(EnableMyAutoConfigurationProperties.class.getName());

        Class propertyClass = (Class) attrs.getFirst("value");

        return new String[] {propertyClass.getName() };
    }
}
