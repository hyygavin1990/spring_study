package com.hyy.controller;

import com.hyy.entity.Person;
import com.hyy.util.RsaSecurityParameter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hyygavin on 2019/8/20.
 */
public class IndexController {

    /**
     * 跳转rsa页面
     *
     * @return
     */

    @RequestMapping("/rsa")
    public String goRsa() {
        return "rsa";
    }

    /**
     * RSA测试
     *
     * @return object
     */
    @RequestMapping("/rsaTest")
    @ResponseBody
    @RsaSecurityParameter
    public Person rsaTest(@RequestBody Person info) {
        System.out.println(info.getName());
        String content = "内容";
        info.setName(content);
        return  info;
    }
}
