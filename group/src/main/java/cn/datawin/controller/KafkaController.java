package cn.datawin.controller;

import cn.datawin.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by hyygavin on 2018/9/18.
 */
@Controller
public class KafkaController {

    @Autowired
    private Environment environment;
    @Autowired
    private KafkaService kafkaService;

    @RequestMapping("/kafka/group/info")
    public String group_info(Model model) {
        String bootstrapServer =environment.getProperty("kafka.bootstrap-server");
        String groupId =environment.getProperty("kafka.group-id");
        List<List<String>> datasList = kafkaService.getDataList(bootstrapServer, groupId);
        model.addAttribute("groupid",groupId);
        model.addAttribute("datasList",datasList);
        return "/kafka/groupInfo";
    }

    @RequestMapping("/kafka/group/json")
    @ResponseBody
    public List<List<String>> groupInfo() {
        String bootstrapServer =environment.getProperty("kafka.bootstrap-server");
        String groupId =environment.getProperty("kafka.group-id");
        return kafkaService.getDataList(bootstrapServer, groupId);
    }


}
