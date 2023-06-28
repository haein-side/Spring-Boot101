package haeinspring.helloboot;

import jdk.jfr.ContentType;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication {

	public static void main(String[] args) {
		// Spring Container aka. Singleton Registry
		// Object 생성 시 딱 한 번만 만듦 = 싱글톤 패턴
		GenericApplicationContext applicationContext = new GenericApplicationContext();
		applicationContext.registerBean(HelloController.class);
		applicationContext.registerBean(SimpleHelloService.class);
		applicationContext.refresh(); // Spring Container 초기화 시 object 만들 수 있도록

		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			servletContext.addServlet("hello", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					// 인증, 보안, 다국어, 공통 기능
					// 서블릿 컨테이너의 맵핑 기능을 프론트 컨트롤러가 담당
					if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
						String name = req.getParameter("name");

						// Spring Container가 갖고 있는 Object 전달 받아서 사용
						HelloController helloController = applicationContext.getBean(HelloController.class);

						// 바인딩 - 평범한 자바 타입(String)으로 웹 요청 변환 -> 새로운 형태의 타입으로 변환하고 파라미터로 넘기는 것
						// 웹 요청 직접적으로 엑세스 하는 프론트 컨트롤러와 같은 코드에서 처리하는 객체에게 평범한 데이터 타입으로 변환해서 넘겨주는 것
						String ret = helloController.hello(name);

						resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().println(ret);
					}
					else {
						resp.setStatus(HttpStatus.NOT_FOUND.value());
					}
				}
			}).addMapping("/*");
		});
		// Tomcat Servlet Container 동작
		webServer.start();
	}

}
