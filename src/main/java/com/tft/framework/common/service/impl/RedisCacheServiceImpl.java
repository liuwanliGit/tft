package com.tft.framework.common.service.impl;

import com.tft.framework.common.service.TftCacheService;
import com.tft.framework.common.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/10/12 14:15
 *
 * @ClassName RedisCacheServiceImpl
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Service("redisCacheService")
public class RedisCacheServiceImpl implements TftCacheService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void saveCache(String key, Object value, long l, TimeUnit timeUnit) {
        //将授权信息缓存到Redis中
        redisTemplate.boundValueOps(key).set(JsonUtil.objToJson(value),l,timeUnit);
    }

    @Override
    public void saveCache(String key, Object value) {
        //将授权信息缓存到Redis中
        redisTemplate.boundValueOps(key).set(JsonUtil.objToJson(value));
    }

    @Override
    public Object getChae(String key,Class clazz) {
        Object value = redisTemplate.boundValueOps(key).get();
        if(value!=null){
            return JsonUtil.jsonToBean(value.toString(),clazz);
        }
        return null;
    }
}
