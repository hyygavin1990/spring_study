package cn.datawin;

import cn.datawin.bean.User;
import cn.datawin.client.UserFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@SpringBootApplication
@EnableFeignClients
@RestController
@EnableEurekaClient
public class FeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignApplication.class, args);
	}


	@Resource
	UserFeignClient userFeignClient;

	@RequestMapping("/{id}")
	public User findById(@PathVariable("id") Integer id){
		return userFeignClient.findById(id);
	}

}

