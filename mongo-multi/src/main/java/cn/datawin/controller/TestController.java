package cn.datawin.controller;

import cn.datawin.bean.User;
import cn.datawin.mongodb.MongoHelper;
import cn.datawin.service.UserService;
import com.alibaba.fastjson.JSON;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
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
    @RequestMapping("/test2")
    public String test2(){
        User user = new User();
        user.setName("Tom");
        user.setGender("male");
        user.setInittime(new Date());
        userService.insert(user);
        List<User> userList = userService.findByName("Tom");
        return JSON.toJSONString(userList);
    }

    @RequestMapping("/test3")
    public String test3(){
        User user = new User();
        user.setName("Tom");
        user.setGender("male");
        user.setInittime(new Date());
        userService.insertToMonthDB(user);
        List<Document> userList = userService.getListInMonthDb("Tom");
        return JSON.toJSONString(userList);
    }

    @Resource(name="aiMongoHelper")
    private MongoHelper aiMongoHelper;

    @RequestMapping("/getStateLog")
    public String getStateLog(){

        MongoCollection stateLogCol = aiMongoHelper.getCollectionInMonthDb(new Date(), "state_log");
        Document query = new Document();
        Document sort = new Document();
        sort.put("_id",1);
        List<Document> list = aiMongoHelper.findList(stateLogCol, query, sort, 1000);


        return JSON.toJSONString(list);
    }
}
