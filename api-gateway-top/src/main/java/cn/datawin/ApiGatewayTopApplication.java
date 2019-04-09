package cn.datawin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayTopApplication {


	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayTopApplication.class, args);
	}
//	@Bean
//	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route(p -> p
//						.path("/get")
//						.uri("http://localhost:9000/hello"))
//				.build();
//	}
}
