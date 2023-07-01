package haeinspring.helloboot;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException();

        return helloService.sayHello(name);
    }

}
