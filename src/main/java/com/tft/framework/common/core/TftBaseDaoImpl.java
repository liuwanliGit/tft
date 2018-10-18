package com.tft.framework.common.core;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/30 14:39
 *
 * @ClassName TftBaseDaoImpl
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public abstract class TftBaseDaoImpl<T>{
    public abstract TftQuery<T> createQuery(T t);
}
