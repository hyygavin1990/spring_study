package cn.datawin.hystrix;

import cn.datawin.bean.User;
import cn.datawin.client.UserFeignClient;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class HystrixClientFallback implements UserFeignClient {


    @Override
    public JSONObject hi( String name) {
        JSONObject error = new JSONObject();
        error.put("id",-1);
        error.put("name","error");
        return error;
    }

    @Override
    public User findById( Integer id) {
        User user = new User();
        user.setId(-1);
        user.setName("error");
        return user;
    }
}