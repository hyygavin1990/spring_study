package cn.datawin.service;

import cn.datawin.client.UserFeignClient;
import cn.datawin.config.FeignConfiguration;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

@Service
public class RibbonService {
    @Autowired
    UserFeignClient userFeignClient;

    public String hi( String name){
        return userFeignClient.hi(name);
    }
}