package cn.datawin;

import cn.datawin.consumer.MyListener;
import cn.datawin.producer.KafkaSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@SpringBootApplication
@RestController
@EnableScheduling
public class KafkaApplication {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(KafkaApplication.class, args);

//		KafkaSender sender = context.getBean(KafkaSender.class);
//
//		for (int i = 0; i < 100; i++) {
//			//调用消息发送类中的消息发送方法
//			sender.send();
//			try {
//				Thread.sleep(30);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}

//		while(true){
//			String id = MyListener.queue.poll();
//			System.out.println("queue poll!"+id);
//			Thread.sleep(1000);
//		}
	}
/*
	@Resource
	private KafkaSender kafkaSender;

	@RequestMapping("/test")
	public String test(String topic){
		kafkaSender.send(topic);
		return "1";
	}*/

}
