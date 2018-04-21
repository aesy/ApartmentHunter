package org.aesy.apartment;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class MongoDbConfiguration extends AbstractMongoConfiguration {
    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private String port;

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(String.format("%s:%s", host, port));
    }

    @Override
    protected String getDatabaseName() {
        return "apartment";
    }
}
