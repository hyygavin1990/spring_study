package cn.datawin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class UserManagementRibbonClient {

    @Autowired
    RestTemplate restTemplate;

    @Value("${server.port}")
    String port;

    @GetMapping("/hello")
    public String helloByRest(){
        String result = this.restTemplate.getForObject("http://user-service/hello", String.class);
        return result;
    }


    @GetMapping("/user")
    public String userForRest(){
        User user = new User();
        user.setId(1);
        user.setName("孙悟空");
        String result = this.restTemplate.postForObject("http://userService/user",user, String.class);
        return result;
    }

}
