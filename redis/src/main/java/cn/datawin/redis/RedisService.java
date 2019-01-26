package cn.datawin.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by hyygavin on 2018/8/3.
 */
@Service
public class RedisService {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * redis set
     * @param key
     * @param value
     */
    public void set(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }
    /**
     * redis set
     * @param key
     * @param value
     * @param minute
     */
    public void set(String key,String value,long minute){
        redisTemplate.opsForValue().set(key,value,minute, TimeUnit.MINUTES);
    }

    /**
     * redis get
     * @param key
     * @return
     */
    public String get(String key){
        return redisTemplate.opsForValue().get(key).toString();
    }

    /**
     * redis delete
     * @param key
     * @return
     */
    public Boolean delete(String key){
        return redisTemplate.delete(key);
    }

    /**
     * redis 批量删除
     * @param pattern 通配符
     * @return
     */
    public void batchDel(String pattern){
        logger.info("redis_batch_del==="+pattern);
        Set<String> keys = redisTemplate.keys(pattern);
        Iterator<String> it = keys.iterator();
        while(it.hasNext()){
            String key = it.next();

            redisTemplate.delete(key);
        }
    }

    /**
     * redis inc
     * @param key
     * @return
     */
    public Long inc(String key, long offset){
//        Map<String,Object> param = new HashMap<>();
//        param.put("a", 0);
//        redisTemplate.opsForHash().putAll(key, param);
        return redisTemplate.opsForHash().increment(key,"a",offset);
    }


    //======================================通话状态机======================================
    //通话状态机map
//    public static ConcurrentHashMap<String,UntypedStateMachine> map=new ConcurrentHashMap<>();
    //通话状态机Context的map
//    public static ConcurrentHashMap<String,Context> contextmap=new ConcurrentHashMap<>();
    //记录拒绝次数的map
//    public static ConcurrentHashMap<String,JSONObject> statusMap=new ConcurrentHashMap<>();
    //记录matchJson队列的map
//    public static ConcurrentHashMap<String,List<JSONObject>> matchJsonMap=new ConcurrentHashMap<>();
    //用户关联状态规则记录的map
//    public static ConcurrentHashMap<String,List<JSONObject>> fromMatchJsonMap=new ConcurrentHashMap<>();
    //记录开放性问题次数(matchtype)的map
//    public static ConcurrentHashMap<String,JSONObject> emptyMap=new ConcurrentHashMap<>();
    //记录通话记录的map
//    public static ConcurrentHashMap<String,List<String>> voiceMap=new ConcurrentHashMap<>();

    //======================================规则状态机======================================
    //规则状态机map
//    public static ConcurrentHashMap<String,UntypedStateMachine> rulemap=new ConcurrentHashMap<>();
}
