package cn.datawin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FreemarkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreemarkerApplication.class, args);
    }

}
