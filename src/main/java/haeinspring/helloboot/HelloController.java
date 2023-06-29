package haeinspring.helloboot;

import org.springframework.web.bind.annotation.*;

import java.util.Objects;

// @Component 단점 : 어떤 클래스들이 Bean으로 만들어지는지 잘 모를 수 있음 but 편리해서 추천
// Meta Annotation : Bean Object가 어떤 종류다 를 명시하고 싶은지 - 웹, 서비스, 데이터 엑세스 계층인지 컴포넌트의 역할 명시 (계층형 아키텍처)
// @RestController : @ResponseBody 추가됨 - api로써 Controller를 만들 때 리턴하는 값을 body에 넣어서 리턴함
//                  : 클래스 레벨의 @RequestMapping 생략해도 메소드 레벨에 매핑 정보 넣어줘도 DispatcherServlet이 다 찾아줌

@RestController
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
