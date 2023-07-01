package haeinspring.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> autoConfigs = new ArrayList<>();

        // 자동구성에 사용할 configuration class들의 목록이 candidates에 들어가 있을 것
        // Iterable<String> candidates = ImportCandidates.load(MyAutoConfiguration.class, classLoader);
        // Iterable이기 떄문에 for each 사용가능
        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add);

        return autoConfigs.toArray(new String[0]);
    }
}
