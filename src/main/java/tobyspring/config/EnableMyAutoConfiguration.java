package tobyspring.config;

import org.springframework.context.annotation.Import;
import tobyspring.config.autoconfig.DispatchetServletConfig;
import tobyspring.config.autoconfig.TomcatWebServerConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({DispatchetServletConfig.class, TomcatWebServerConfig.class}) // 컴포넌트 애노테이션 붙은 (혹은 메타 애노테이션으로 갖고 있는) 클래스들을 임포트를 통해 구성정보에 직접 추가 가능
public @interface EnableMyAutoConfiguration {
}
