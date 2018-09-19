package cn.datawin.dao.manager;

import cn.datawin.bean.Goods;
import cn.datawin.bean.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hyygavin on 2018/8/28.
 */
@Repository
public interface GoodsDao extends MongoRepository<Goods, String> {

    List<User> findByName(String name);
}
