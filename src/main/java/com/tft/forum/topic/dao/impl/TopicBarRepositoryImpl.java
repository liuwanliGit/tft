package com.tft.forum.topic.dao.impl;

import com.tft.forum.topic.bean.TopicBar;
import com.tft.framework.common.bean.QueryCondition;
import com.tft.framework.common.core.TftBaseDaoImpl;
import com.tft.framework.common.core.TftQuery;
import com.tft.framework.common.tftEnum.MatchType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/30 14:42
 *
 * @ClassName TopicBarRepositoryImpl
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public class TopicBarRepositoryImpl extends TftBaseDaoImpl<TopicBar> {

    @Autowired
    private DataSource dataSource;

    @Override
    public TftQuery<TopicBar> createQuery(TopicBar topicBar) {
        if(topicBar!=null){
            QueryCondition condition = new QueryCondition();
            condition.addItemFilterEmpty("name",MatchType.LIKE,"%"+topicBar.getName()+"%");
            condition.addItemFilterEmpty("type",MatchType.EQ,topicBar.getType());
            return new TftQuery<TopicBar>(condition);
        }
        return null;
    }
}
