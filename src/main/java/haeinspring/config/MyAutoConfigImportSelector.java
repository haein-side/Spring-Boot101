package haeinspring.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {
                "haeinspring.config.autoconfig.DispatcherServletConfig",
                "haeinspring.config.autoconfig.TomcatWebServerConfig"
        };
    }
}
