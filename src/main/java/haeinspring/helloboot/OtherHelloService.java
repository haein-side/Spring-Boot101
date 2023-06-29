package haeinspring.helloboot;

public class OtherHelloService implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hi " + name;
    }

}
