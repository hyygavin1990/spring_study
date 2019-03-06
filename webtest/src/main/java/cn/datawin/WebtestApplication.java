package cn.datawin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WebtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebtestApplication.class, args);
	}
	@Value("${test}")
	private String test;

	@RequestMapping("/test")
	public String test(){
		return test;
	}

}
