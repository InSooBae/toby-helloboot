package tobyspring.helloboot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

// 여러가지 기능과 책임을 부가적으로 주는 내용이 데코레이터라 할 수 있다.
@Service
@Primary
public class HelloDecorator implements HelloService {

    private final HelloService helloService;

    public HelloDecorator(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String sayHello(String name) {
        return "*" + helloService.sayHello(name) + "*";
    }

}
