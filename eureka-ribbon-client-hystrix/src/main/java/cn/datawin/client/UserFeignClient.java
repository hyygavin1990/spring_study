package cn.datawin.client;

import cn.datawin.hystrix.HystrixClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by hyygavin on 2019/1/28.
 */
@FeignClient(name="feign-service",fallback = HystrixClientFallback.class )
public interface UserFeignClient {

    @RequestMapping("/hi")
    public String hi(@RequestParam("name") String name);
}
