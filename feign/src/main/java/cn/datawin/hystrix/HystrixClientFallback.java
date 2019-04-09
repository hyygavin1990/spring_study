package cn.datawin.hystrix;

import cn.datawin.bean.User;
import cn.datawin.client.UserFeignClient;
import org.springframework.stereotype.Component;

@Component
public class HystrixClientFallback implements UserFeignClient {

    @Override
    public User findById( Integer id) {
        User user = new User();
        user.setId(-1);
        user.setName("error");
        return user;
    }
}