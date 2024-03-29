package com.manage.system.redis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.ssl}")
    private String ssl;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.pool.max-wait}")
    private long maxWaitMillis;

    @Bean
    public JedisPool redisPoolFactory() {
        logger.info("[**********************************************]");
        logger.info("开发测试Dev JedisPool注入成功！！");
        logger.info("redis地址：" + host + ":" + port);
        logger.info("[**********************************************]");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        JedisPool jedisPool = null;
        if (password == null || password.equals("")) {
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, Boolean.valueOf(ssl));
        } else {
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password, Boolean.valueOf(ssl));
        }

        return jedisPool;
    }

}