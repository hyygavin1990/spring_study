package cn.datawin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
//@EnableEurekaClient
public class TomcatV1Application {

//	@Value("${name}")
//	private String name;


	@Autowired
	public Environment env;//当前环境的application.properties的 配置
	@Autowired
	public DiscoveryClient discoveryClient;
	@RequestMapping("/hello")
	public String hello(){
		return "tomcat-v1-hello";
	}

	@PostMapping("/user")
	public String user(@RequestBody User user){
		return "tomcat-v1-user:"+user.getId()+","+user.getName();
	}
	@RequestMapping(value = "/{id}")
	public User user(@PathVariable("id") Integer id ){
		User user = new User();
		user.setId(id);
		user.setName("Tom");
		return user;
	}

	@RequestMapping("/user-instance")
	public List<ServiceInstance> showInfo(){
		return discoveryClient.getInstances("syzj-v2-model-server");
	}


	public static void main(String[] args) {
		SpringApplication.run(TomcatV1Application.class, args);
	}
}
