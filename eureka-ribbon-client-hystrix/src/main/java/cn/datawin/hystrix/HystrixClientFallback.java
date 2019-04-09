package cn.datawin.hystrix;

import cn.datawin.client.UserFeignClient;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class HystrixClientFallback implements UserFeignClient {


    @Override
    public String hi( String name) {
        JSONObject error = new JSONObject();
        error.put("id",-1);
        error.put("name","error");
        return error.toJSONString();
    }
}