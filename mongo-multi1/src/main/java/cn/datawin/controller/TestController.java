package cn.datawin.controller;

import cn.datawin.mongodb.MongoHelper;
import com.alibaba.fastjson.JSON;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by hyygavin on 2018/11/30.
 */
@RestController
public class TestController {

    @Resource(name="slaveMongoHelper")
    private MongoHelper slaveMongoHelper;


    @RequestMapping("/getStateLog")
    public String getStateLog(){

        MongoCollection stateLogCol = slaveMongoHelper.getCollection(new Date(), "state_log");
        Document query = new Document();
        Document sort = new Document();
        sort.put("_id",1);
        List<Document> list = slaveMongoHelper.findList(stateLogCol, query, sort, 1000);
        return JSON.toJSONString(list);
    }
}
