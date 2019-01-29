package cn.datawin;

import cn.datawin.redis.RedisService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@RestController
public class RedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

	@Resource
	private RedisService redisService;

	@RequestMapping(value = "/inc", method = RequestMethod.GET)
	public String inc(HttpServletRequest request) {
		return redisService.inc("test",1)+"";
	}
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public Object get(HttpServletRequest request) {
		return redisService.get("test");
	}
}

