package cn.datawin.config;

import cn.datawin.mongodb.MongoHelper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMongoRepositories(basePackages = "cn.datawin.dao.manager",
        mongoTemplateRef = "managerMongoHelper")
public class ManagerMongoConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Bean(name = "managerMongoSettingsProperties")
    @ConfigurationProperties(
            prefix = "spring.data.mongodb.manager")
    MongoSettingsProperties mongoSettingsProperties() {
        return new MongoSettingsProperties();
    }

    // 覆盖默认的MongoDbFactory
    @Bean(name = "managerMongoDbFactory")
    MongoDbFactory mongoDbFactory(@Qualifier("managerMongoSettingsProperties")MongoSettingsProperties mongoSettingsProperties) {
        //客户端配置（连接数、副本集群验证）
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.connectionsPerHost(mongoSettingsProperties.getConnectionsPerHost());
        builder.minConnectionsPerHost(mongoSettingsProperties.getMinConnectionsPerHost());
        if (mongoSettingsProperties.getReplicaSet() != null) {
            builder.requiredReplicaSetName(mongoSettingsProperties.getReplicaSet());
        }
        MongoClientOptions mongoClientOptions = builder.build();

        // MongoDB地址列表
        List<ServerAddress> serverAddresses = new ArrayList<>();
        for (String host : mongoSettingsProperties.getHosts()) {
            Integer index = mongoSettingsProperties.getHosts().indexOf(host);
            Integer port = mongoSettingsProperties.getPorts().get(index);

            ServerAddress serverAddress = new ServerAddress(host, port);
            serverAddresses.add(serverAddress);
        }
        logger.info("mongoServerAddresses:" + serverAddresses.toString());

        // 连接认证
        List<MongoCredential> mongoCredentialList = new ArrayList<>();
        if (mongoSettingsProperties.getUsername() != null) {
            mongoCredentialList.add(MongoCredential.createScramSha1Credential(
                    mongoSettingsProperties.getUsername(),
                    mongoSettingsProperties.getAuthenticationDatabase() != null ? mongoSettingsProperties.getAuthenticationDatabase() : mongoSettingsProperties.getDatabase(),
                    mongoSettingsProperties.getPassword().toCharArray()));
        }
        logger.info("mongoCredentialList:" + mongoCredentialList.toString());

        //创建客户端和Factory
        MongoClient mongoClient = new MongoClient(serverAddresses, mongoCredentialList, mongoClientOptions);
        SimpleMongoDbFactory simpleMongoDbFactory =new SimpleMongoDbFactory(mongoClient, mongoSettingsProperties.getDatabase());
        return simpleMongoDbFactory;
    }

    @Bean(name="managerMongoHelper")
    public MongoHelper mongoTemplate(@Qualifier("managerMongoDbFactory") MongoDbFactory mongoDbFactory) throws Exception {
        return new MongoHelper(mongoDbFactory);
    }

}