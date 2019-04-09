package cn.datawin.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @RequestMapping("")
    public String fallback(String lb){
        if(StringUtils.isNotBlank(lb)){
            return "service:"+lb+" is not working";
        }else{
            return "service's name is required";
        }

    }
}