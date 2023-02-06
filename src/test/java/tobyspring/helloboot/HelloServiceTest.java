package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class HelloServiceTest {

    @Test
    public void simpleHelloService() throws Exception {
        SimpleHelloService simpleHelloService = new SimpleHelloService();

        String ret = simpleHelloService.sayHello("test");

        Assertions.assertThat(ret).isEqualTo("Hello test");
    }

}