package com.tft.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/10/12 11:07
 *
 * @ClassName RedisConfig
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Configuration
public class RedisConfig {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Bean("redisTemplate")
    public RedisTemplate redisTemplate(@Lazy RedisConnectionFactory connectionFactory) {
        RedisTemplate redis = new RedisTemplate();
        GenericToStringSerializer<String> keySerializer = new GenericToStringSerializer<String>(String.class);
        redis.setKeySerializer(keySerializer);
        redis.setHashKeySerializer(keySerializer);
        GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer();
        redis.setValueSerializer(valueSerializer);
        redis.setHashValueSerializer(valueSerializer);
        redis.setConnectionFactory(connectionFactory);

        return redis;
    }


}
