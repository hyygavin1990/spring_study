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
 * @author fonlin
 * @date 2018/6/28
 */
public class MyMongoDbFactory {

    private MongoDbFactory mongoDbFactory;

    /**
     * 没有分月库的数据库
     */
    private String databaseName;


    public MyMongoDbFactory(MongoDbFactory mongoDbFactory) {
        this.mongoDbFactory = mongoDbFactory;
        this.databaseName = mongoDbFactory.getDb().getName();
    }

    public MongoDatabase getDb(String dbName) throws DataAccessException {
        return mongoDbFactory.getDb(dbName);
    }

    public MongoDatabase getDb() throws DataAccessException {
        return getDb(databaseName);
    }

    public MongoDatabase getDbWithSuffix(String suffix) throws DataAccessException {
        Assert.hasText(suffix, "month must not be empty.");
        String name = this.databaseName + "_" + suffix;
        return mongoDbFactory.getDb(name);
    }



}
