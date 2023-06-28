package haeinspring.helloboot;

import org.springframework.web.bind.annotation.*;

import java.util.Objects;
@RequestMapping ("/hello")
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping
    @ResponseBody
    public String hello(@RequestParam String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
