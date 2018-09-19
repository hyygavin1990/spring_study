package cn.datawin;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hyygavin on 2018/6/8.
 */
@FeignClient("aaaaa")
public interface HelloClient {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String hello();
}
