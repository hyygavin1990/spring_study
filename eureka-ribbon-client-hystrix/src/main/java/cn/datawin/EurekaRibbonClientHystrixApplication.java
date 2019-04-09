package cn.datawin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableHystrix
@EnableFeignClients
@EnableEurekaClient
public class EurekaRibbonClientHystrixApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaRibbonClientHystrixApplication.class, args);
	}

}
