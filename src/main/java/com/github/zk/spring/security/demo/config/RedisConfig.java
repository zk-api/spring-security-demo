package com.github.zk.spring.security.demo.config;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Redis 配置
 *
 * @author zk
 * @date 2021/2/7 11:13
 */
@Configuration
@EnableRedisHttpSession
public class RedisConfig {

    @Bean
    public LettuceConnectionFactory connectionFactory(RedisProperties properties) {
        RedisStandaloneConfiguration redisConfiguration = new RedisStandaloneConfiguration();
        String hostName = properties.getHost();
        int port = properties.getPort();
        String password = properties.getPassword();
        redisConfiguration.setPassword(RedisPassword.of(password));
        redisConfiguration.setHostName(hostName);
        redisConfiguration.setPort(port);
        return new LettuceConnectionFactory(redisConfiguration);
    }

}
