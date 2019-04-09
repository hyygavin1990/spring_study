package cn.datawin.client;

import cn.datawin.bean.User;
import cn.datawin.config.FeignConfiguration;
import cn.datawin.hystrix.HystrixClientFallback;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by hyygavin on 2019/1/28.
 */
@FeignClient(name="feign-user-service",configuration = FeignConfiguration.class,fallback = HystrixClientFallback.class)
public interface UserFeignClient {

    @RequestLine("GET /user/{id}")
    public User findById(@Param("id") Integer id);
}
