package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {

    @Test
    public void helloController() throws Exception {
        HelloController helloController = new HelloController(name -> name);

        String ret = helloController.hello("Test");

        Assertions.assertThat(ret).isEqualTo("Test");
    }

    @Test
    public void failsHelloController() throws Exception {
        //given
        HelloController helloController = new HelloController(name -> name);
        //when

        //then
        Assertions.assertThatThrownBy(() -> {
            String ret = helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> {
            String ret = helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
