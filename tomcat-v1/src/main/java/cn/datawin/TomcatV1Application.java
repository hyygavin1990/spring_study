package cn.datawin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TomcatV1Application {

//	@Value("${name}")
//	private String name;


	@Autowired
	public Environment env;//当前环境的application.properties的 配置
	@RequestMapping("/hello")
	public String hello(){
		return "tomcat-v1-hello";
	}

	@PostMapping("/user")
	public String user(@RequestBody User user){
		return "tomcat-v1-user:"+user.getId()+","+user.getName();
	}


	public static void main(String[] args) {
		SpringApplication.run(TomcatV1Application.class, args);
	}
}
