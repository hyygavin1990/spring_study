package cn.datawin;

import cn.datawin.redis.RedisService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@RestController
public class RedisApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RedisApplication.class, args);
//
//		RedisService redisService = context.getBean(RedisService.class);
//		redisService.set("robot_reply_words","您拨打的,您拨叫的,通话中,挂机,语音,留言,发送,请按,重录");
//		redisService.set("useless_num_words","多,上,来");
	}
	@Resource
	private RedisService redisService;


//	@RequestMapping(value = "/inc", method = RequestMethod.GET)
//	public String inc(HttpServletRequest request) {
//		return redisService.inc("test",1)+"";
//	}
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public Object get(HttpServletRequest request,String key) {
		return redisService.get(key);
	}

	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public Object del(HttpServletRequest request,String key) {
		return redisService.delete(key);
	}
//	@RequestMapping(value = "/set", method = RequestMethod.GET)
//	public String get(HttpServletRequest request) {
//		redisService.set("robot_reply_words","您拨打的,您拨叫的,通话中,挂机,语音,留言,发送,请按,重录");
//		redisService.set("useless_num_words","多,上,来");
//		return "aa";
//	}
}

