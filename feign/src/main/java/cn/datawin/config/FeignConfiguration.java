package cn.datawin.config;

import feign.Contract;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hyygavin on 2019/1/28.
 */
@Configuration
public class FeignConfiguration {

    @Bean
    public Contract feignContract(){

        return new feign.Contract.Default();
    }
    @Bean
    public Decoder decoder(){

        return new JacksonDecoder();
    }
    @Bean
    public Encoder encoder(){

        return new JacksonEncoder();
    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "123");
    }
}
