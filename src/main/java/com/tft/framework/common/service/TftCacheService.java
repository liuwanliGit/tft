package com.tft.framework.common.service;

import java.util.concurrent.TimeUnit;

/**
 * <br>类描述：缓存管理接口
 * <br>author： lwl liuwanli_eamil@163.com	2018/10/12 14:07
 *
 * @ClassName TftCacheService
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public interface TftCacheService {

    public void saveCache(String key, Object value, long l, TimeUnit timeUnit);

    public void saveCache(String key,Object value);

    public Object getChae(String key,Class clazz);
}
