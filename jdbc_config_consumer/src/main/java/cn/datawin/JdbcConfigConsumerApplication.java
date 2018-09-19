package cn.datawin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JdbcConfigConsumerApplication {


	@Autowired
	public Environment env;//当前环境的application.properties的 配置
	@RequestMapping("/hello")
	public String hello(){
		return "gitname:"+env.getProperty("gitname")+",jdbcname:"+env.getProperty("jdbcname");
	}

	public static void main(String[] args) {
		SpringApplication.run(JdbcConfigConsumerApplication.class, args);
	}
}
