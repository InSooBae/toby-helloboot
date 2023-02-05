package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@ComponentScan
public class HellobootApplication {

	@Bean
	public ServletWebServerFactory servletWebServerFactory() {
		return new TomcatServletWebServerFactory();
	}

	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}

	public static void main(String[] args) {
		// 스프링 컨테이너
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				super.onRefresh();
				// 서블릿 컨테이너
				ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
				DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);

				/* 이 부분이 없어도 자동으로 주입이 됐다. 이것은 스프링 컨테이너가 주입함.
				 스프링 컨테이너가 dispatcherServlet은 applicationContext가 필요하구나 하고 주입
				 깊은 내용을 이해하려면 빈의 라이프사이클을 이해하고 있어야한다.

				 ApplicationContextAware이란 인터페이스(스프링 내부 코드 보면 여러 구현체들[DispatcherServlet,Jackson2Object..,]이 해당 인터페이스 구현함)는 빈을 컨테이너가 등록 및 관리하던 중 컨테이너가 관리하는 오브젝트를 빈에다가 주입해주는 라이프싸이클 메서드이다.
				 해당 인터페이스와 같은(~Aware 같은 인터페이스(스프링에 포함된 인터페이스))것을 구현한 어떤 클래스가 스프링 빈으로 등록되면, 스프링 컨테이너는 인터페이스에 포함된 세터 메서드를 이용해 주입해준다.
				*/
				// dispatcherServlet.setApplicationContext(this);

				WebServer webServer = serverFactory.getWebServer(servletContext -> {

					servletContext.addServlet("dispatcherServlet", dispatcherServlet).addMapping("/*");

				});
				webServer.start();
				// 서블릿 컨테이너
			}
		};

		// 자바 코드로 된 구성 정보 클래스 등록
		applicationContext.register(HellobootApplication.class);
		// 스프링 컨테이너 초기화
		applicationContext.refresh();
		// 스프링 컨테이너


	}

}
