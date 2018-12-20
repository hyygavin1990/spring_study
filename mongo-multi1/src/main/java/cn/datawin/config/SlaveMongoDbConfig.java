package cn.datawin.config;

import cn.datawin.mongodb.MongoHelper;
import cn.datawin.mongodb.MonthlyMongoDbFactory;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoClientFactory;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * 手动配置{@link MongoDbFactory}，从库
 *
 * @author fonlin
 * @date 2018/6/28
 */
@Configuration
//禁用spring boot的自动配置
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
//激活@Repository注解，并且设置dao扫描包
@EnableMongoRepositories(basePackages = "cn.datawin.dao.slave", mongoTemplateRef = "slaveMongoHelper")
public class SlaveMongoDbConfig {

    @Bean(name = "slaveMongoProperties")
    @ConfigurationProperties(prefix = "spring.data.mongodb.slave")
    @Primary
    public MongoProperties mongoProperties() {
        return new MongoProperties();
    }

    @Bean(name = "slaveMongoClientFactory")
    @Primary
    public MongoClientFactory mongoClientFactory(@Qualifier("slaveMongoProperties") MongoProperties properties, Environment environment) {
        return new MongoClientFactory(properties, environment);
    }

    @Bean(name = "slaveMongoClient")
    @Primary
    public MongoClient mongoClient(@Qualifier("slaveMongoClientFactory") MongoClientFactory factory) {
        return factory.createMongoClient(null);
    }


    @Bean(name = "slaveMongoDbFactory")
    @Primary
    public MonthlyMongoDbFactory mongoDbFactory(@Qualifier("slaveMongoClient") MongoClient mongoClient, @Qualifier("slaveMongoProperties") MongoProperties properties) {
        //这里new我们自己的MongoDbFactory即可
        return new MonthlyMongoDbFactory(mongoClient, properties.getDatabase());
    }

    @Bean(name = "slaveMongoHelper")
    @Primary
    public MongoHelper mongoTemplate(@Qualifier("slaveMongoDbFactory") MonthlyMongoDbFactory mongoDbFactory) {
        return new MongoHelper(mongoDbFactory);
    }
}
