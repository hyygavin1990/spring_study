package cn.datawin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 小程序首页
 * author:zq
 * date:2020/3/5
 */
@Controller
@RequestMapping("/page")
public class PageController {


    @RequestMapping(value = "/aaaa")
    public String aaaa() {
        return "/a/test";
    }
}
