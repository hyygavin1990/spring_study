package cn.datawin.init;

import cn.datawin.pojo.MyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

/**
 * Created by hyygavin on 2018/9/15.
 */
@Component
@Order(1)
public class initData implements CommandLineRunner {

    @Autowired
    MongoOperations mongo;

    @Override
    public void run(String... strings) throws Exception {
        mongo.dropCollection(MyEvent.class);    // 4
        mongo.createCollection(MyEvent.class, CollectionOptions.empty().size(100000).capped()); // 5
    }
}
