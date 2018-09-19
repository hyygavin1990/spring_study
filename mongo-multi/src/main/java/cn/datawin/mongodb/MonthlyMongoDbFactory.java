package cn.datawin.mongodb;

import com.mongodb.client.MongoDatabase;
import jodd.util.StringUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 继承 {@link SimpleMongoDbFactory }，重写{@code getDB}方法，添加月库
 *
 * @author fonlin
 * @date 2018/6/28
 */
public class MonthlyMongoDbFactory  {


    private MongoDbFactory mongoDbFactory;
    private static final String MONTH_PATTERN = "yyyyMM";

    /**
     * 没有分月库的数据库
     */
    private String databaseName;


    public MonthlyMongoDbFactory(MongoDbFactory mongoDbFactory) {
        this.mongoDbFactory = mongoDbFactory;
        this.databaseName = mongoDbFactory.getDb().getName();
    }

    public MongoDatabase getDb(String dbName) throws DataAccessException {
        return mongoDbFactory.getDb(dbName);
    }

    public MongoDatabase getDb() throws DataAccessException {
        //获取当前ThreadLocal中的month
        String month = MonthSelector.getAndRemove();
        //如果没有，则设置当前月
        if (StringUtil.isEmpty(month)) {
            month = LocalDateTime.now().format(DateTimeFormatter.ofPattern(MONTH_PATTERN));
        }
        return getDbByMonth(month);
    }

    public MongoDatabase getDbByMonth(String month) throws DataAccessException {
        Assert.hasText(month, "month must not be empty.");
        String name = this.databaseName + "_" + month;
        return mongoDbFactory.getDb(name);
    }

}
