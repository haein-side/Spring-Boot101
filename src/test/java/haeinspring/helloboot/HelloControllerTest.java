package haeinspring.helloboot;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {

    @Test
    void helloController() {
        HelloController helloController = new HelloController(name -> name);

        // Test Code 가 필요한 의존성 주입 -> 어셈블러 역할을 해준 것
        String ret = helloController.hello("Test");

        Assertions.assertThat(ret).isEqualTo("Test");
    }

    @Test
    void failsHelloController() {
        HelloController helloController = new HelloController(name -> name);

        Assertions.assertThatThrownBy(() -> {
            helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> {
            helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
