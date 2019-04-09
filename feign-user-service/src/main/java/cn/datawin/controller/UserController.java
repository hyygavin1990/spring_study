package cn.datawin.controller;

import cn.datawin.bean.User;
import cn.datawin.client.UserService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * Created by hyygavin on 2019/1/29.
 */
@RestController
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    UserService userService;

    @RequestMapping("/user/{id}")
    public User findById(@PathVariable("id") Integer id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            UserDetails user = (UserDetails) principal;
            Collection<? extends GrantedAuthority> collection = user.getAuthorities();
            for (GrantedAuthority c : collection) {
                logger.info("当前的用户是{},角色是{}",user.getUsername(),c.getAuthority());
            }
        }
        return userService.findById(id);
    }
    @RequestMapping("/hi")
    public String hi(String name){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            UserDetails user = (UserDetails) principal;
            Collection<? extends GrantedAuthority> collection = user.getAuthorities();
            for (GrantedAuthority c : collection) {
                logger.info("当前的用户是{},角色是{}",user.getUsername(),c.getAuthority());
            }
        }
        JSONObject json = new JSONObject();
        json.put("name",name);
        return json.toJSONString();
    }
}
