package com.dong.buddy.redis;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.redisson.spring.session.config.EnableRedissonHttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

@Configuration
@EnableConfigurationProperties(RedissonProperties.class)
@EnableRedissonHttpSession
public class RedissonConfig
{

    private final Logger logger = LoggerFactory.getLogger(RedissonConfig.class);

    @Autowired
    private RedissonProperties redissonProperties;

    public Config configJson() throws IOException
    {
        URL url = ResourceUtils.getURL(redissonProperties.getConfigFile().getJson());
        URLConnection connection = url.openConnection();
        // File file = ResourceUtils.getFile();
        return Config.fromJSON(connection.getInputStream());
    }

    public Config configYaml() throws IOException
    {
        File file = ResourceUtils.getFile(redissonProperties.getConfigFile().getYaml());
        return Config.fromYAML(file);
    }

    @Bean
    @ConditionalOnMissingBean
    public Config config() throws IOException
    {
        if (!StringUtils.isEmpty(redissonProperties.getConfigFile().getJson()))
        {
            return configJson();
        }
        else if (!StringUtils.isEmpty(redissonProperties.getConfigFile().getYaml()))
        {
            return configYaml();
        }
        else
        {
            throw new RuntimeException("please offer the config file by json/yaml");
        }
    }

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean
    public RedissonClient redissonClient(Config config) throws IOException
    {
        logger.info("create RedissonClient, config is : {}", config.toJSON());
        config.setCodec(StringCodec.INSTANCE);
        return Redisson.create(config);
    }

}
