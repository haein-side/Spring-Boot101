package haeinspring.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


// @Configuration이 붙은 클래스가 Application Context에 처음 등록된다는 게 중요함
// Bean Factory Method 이상으로 전체 Application 구성하는 데에 중요한 정보 많이 넣을 수 있음
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
		MySpringApplication.run(HellobootApplication.class, args); // @Configuration + @ComponentScan
	}

//	public static void main(String[] args) {
//		SpringApplication.run(HellobootApplication.class, args);
//	}

}
