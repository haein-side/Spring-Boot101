package haeinspring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationTest {
    @Test
    void configuration() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfig.class);
        ac.refresh();

        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);

        // @Configuration이 붙은 클래스가 스프링 컨테이너에서 사용될 때 오브젝트가 하나만 생성됨
        // @Configuration proxy = true -> proxy object 1개 생성 및 bean 등록
        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }

    @Test
    void proxyCommonMethod() {
        // 직접 프록시를 만들어서 스프링 컨테이너 안에서 일어나는 일을 흉내내 본 것
        // common method가 생성하는 개수를 하나로 제한, 재사용할 수 있게 캐싱
        // 프록시 오브젝트를 통해 오브젝트 하나로 관리
        // proxyBeanMethods = true 일 때, 스프링 컨테이너가 프록시 클래스 생성하고 @Configuration 붙은 빈 오브젝트로 관리하는 것
        // proxyBeanMethods = false 라면 자바 코드 그대로 사용할 수 있게 됨
        MyConfigProxy myConfigProxy = new MyConfigProxy();

        Bean1 bean1 = myConfigProxy.bean1();
        Bean2 bean2 = myConfigProxy.bean2();

        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }

    static class MyConfigProxy extends MyConfig {
        private Common common;
        @Override
        Common common() {
            if (this.common == null) this.common = super.common();

            return this.common;
        }
    }

    @Configuration
    static class MyConfig {
        @Bean
        Common common() {
            return new Common();
        }

        @Bean
        Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        Bean2 bean2() {
            return new Bean2(common());
        }
    }

    static class Bean1 {
        private final Common common;

        Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2 {
        private final Common common;

        Bean2(Common common) {
            this.common = common;
        }
    }
    static class Common {

    }
}
