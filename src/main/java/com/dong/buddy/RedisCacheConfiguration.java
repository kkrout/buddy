package com.dong.buddy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisCacheConfiguration
{
    private static Logger logger = LoggerFactory.getLogger(RedisCacheConfiguration.class);

    @Value("${spring.redis.host:47.104.65.176}")
    private String host;

    @Value("${spring.redis.port:6379}")
    private int port;

    @Value("${spring.redis.timeout:5000}")
    private int timeout;

    @Value("${spring.redis.pool.max-idle:100}")
    private int maxIdle;

    @Value("${spring.redis.pool.max-wait:20000}")
    private long maxWaitMillis;

    @Value("${spring.redis.pool.password:admin123}")
    private String password;

    @Bean
    public JedisPool redisPoolFactory()
    {
        logger.info("JedisPool注入成功！！");
        logger.info("redis地址：" + host + ":" + port);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        JedisPool jedisPool = null;
        if (StringUtils.isEmpty(password))
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
        else
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);

        return jedisPool;
    }
}
