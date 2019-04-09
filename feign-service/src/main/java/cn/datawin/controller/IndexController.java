package cn.datawin.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hyygavin on 2019/4/8.
 */
@RestController
public class IndexController {


    private Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping("/hi")
    public String hi(String name){
        JSONObject json = new JSONObject();
        json.put("name",name);
        return json.toJSONString();
    }
}
