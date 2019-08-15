package com.hmoro.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

// 自定义逻辑返回需要导入的组件
public class MyImportSelector implements ImportSelector {
    // 返回值就是要导入到容器中的组件全类名
    // AnnotationMetadata: 当前标注 @Import 注解的类的所有注解信息
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.hmoro.bean.Yellow","com.hmoro.bean.Blue"};
    }
}
