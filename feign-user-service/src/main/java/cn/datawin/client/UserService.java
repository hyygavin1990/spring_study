package cn.datawin.client;

import cn.datawin.bean.User;
import org.springframework.stereotype.Service;

/**
 * Created by hyygavin on 2019/1/28.
 */
@Service
public class UserService {

    public User findById( Integer id){
        User user = new User();
        user.setId(id);
        user.setName("Tom");
        return user;
    }
}
