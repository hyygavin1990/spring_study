package cn.datawin.controller;

import cn.datawin.service.KeepAlivedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hyygavin on 2018/9/18.
 */
@RestController
public class IndexController {

    @Autowired
    private KeepAlivedService keepAlivedService;

    @PostMapping("/exec")
    public String group_info(Model model,String command) {
        String result;
        try {
            keepAlivedService.exec(command);
            result = "0";
        } catch (Exception e) {
            e.printStackTrace();
            result = "1";
        }
        return result;
    }


}
