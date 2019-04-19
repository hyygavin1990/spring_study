package cn.datwin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TomcatV2Application {

	@Autowired
	public Environment env;//当前环境的application.properties的 配置
	@RequestMapping("/hello")
	public String hello(){
		return "tomcat-v2-hello";
	}
	public static void main(String[] args) {
		SpringApplication.run(TomcatV2Application.class, args);
	}

	@PostMapping("/user")
	public String user(@RequestBody User user){
		return "tomcat-v2-user:"+user.getId()+","+user.getName();
	}

	@Value("${redis}")
	private String redis;
	@Value("${db}")
	private String db;
	@Value("${rule}")
	private String rule;

	@RequestMapping("/test")
	public String test(){
		return redis+"=="+db+"=="+rule;
	}

}
