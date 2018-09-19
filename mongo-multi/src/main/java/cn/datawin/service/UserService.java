package cn.datawin.service;

import cn.datawin.bean.User;
import cn.datawin.dao.ai.UserDao;
import cn.datawin.mongodb.MongoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public List<User> findByName(String name){
        return userDao.findByName(name);
    }

}
