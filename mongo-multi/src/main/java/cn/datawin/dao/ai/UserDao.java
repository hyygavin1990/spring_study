package cn.datawin.dao.ai;

import cn.datawin.bean.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hyygavin on 2018/8/28.
 */
@Repository
public interface UserDao extends MongoRepository<User, String> {

    List<User> findByName(String name);
}
