package cn.datawin.controller;

import cn.datawin.bean.User;
import cn.datawin.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hyygavin on 2018/9/19.
 */
@RestController
public class TestController {
    @Autowired
    private UserService userService;
    @RequestMapping("/test1")
    public String test1(){
        User user = new User();
        user.setName("Tom");
        user.setGender("male");
        userService.save(user);
        List<User> userList = userService.findByName("Tom");
        return JSON.toJSONString(userList);
    }
}
