package cn.datawin.service;

import cn.datawin.bean.User;
import cn.datawin.dao.ai.UserDao;
import cn.datawin.mongodb.MongoHelper;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by hyygavin on 2018/9/19.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Resource(name="aiMongoHelper")
    private MongoHelper aiMongoHelper;

    public void save(User user){
        aiMongoHelper.save(user);
    }
    public void insert(User user){
        aiMongoHelper.insert(user);
    }
    public void insertToMonthDB(User user){
        MongoCollection userCol = aiMongoHelper.getCollectionInMonthDb(new Date(), User.class);
        aiMongoHelper.insert(user,userCol);
    }

    public List<User> findByName(String name){
        return userDao.findByName(name);
    }

    public List<Document> getListInMonthDb(String name) {
        MongoCollection userCol = aiMongoHelper.getCollectionInMonthDb(new Date(), User.class);

        Document query = new Document();
        query.put("name",name);
        return aiMongoHelper.findList(userCol,query);
    }
}
