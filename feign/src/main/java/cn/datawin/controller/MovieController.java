package cn.datawin.controller;

import cn.datawin.bean.User;
import cn.datawin.client.UserFeignClient;
import cn.datawin.config.FeignConfiguration;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hyygavin on 2019/1/29.
 */
@Import(FeignConfiguration.class)
@RestController
public class MovieController {
    UserFeignClient userUserFeignClient;
    UserFeignClient adminUserFeignClient;

    @Autowired
    public MovieController(Decoder decoder, Encoder encoder, Client client, Contract contract){
        this.userUserFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("user","123")).target(UserFeignClient.class,"http://feign-user-service/");
        this.adminUserFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("admin","123456")).target(UserFeignClient.class,"http://feign-user-service/");
    }

    @RequestMapping("/user-user/{id}")
    public User findByIdUser(@PathVariable("id") Integer id){
        return userUserFeignClient.findById(id);
    }
    @RequestMapping("/admin-user/{id}")
    public User findByIdAdmin(@PathVariable("id") Integer id){
        return adminUserFeignClient.findById(id);
    }
}
