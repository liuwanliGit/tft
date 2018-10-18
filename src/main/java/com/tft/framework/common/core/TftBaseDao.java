package com.tft.framework.common.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
<br>功能描述:  框架的基础dao，继承jpa，不能被springBoot扫描到  不然T会导致报错
<br>处理逻辑:  
<br>作者: lwl liuwanli_eamil@163.com 2018/6/30 20:51
<br>修改记录: {修改人 修改原因 修改时间}
 * @param 
 * @throws 
 * @return 
 * @see #
 */
public interface TftBaseDao<T> extends JpaRepository<T,String>,JpaSpecificationExecutor<T> {
    public TftQuery<T> createQuery(T t);
}
