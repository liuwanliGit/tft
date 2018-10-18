package com.tft.forum.topic.dao.impl;

import com.tft.forum.topic.bean.TopicType;
import com.tft.framework.common.bean.QueryCondition;
import com.tft.framework.common.core.TftBaseDaoImpl;
import com.tft.framework.common.core.TftQuery;
import com.tft.framework.common.tftEnum.MatchType;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/30 14:36
 *
 * @ClassName TopicTypeRepositoryImpl
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public class TopicTypeRepositoryImpl extends TftBaseDaoImpl<TopicType> {
    @Override
    public TftQuery<TopicType> createQuery(TopicType topicType) {
        QueryCondition condition = new QueryCondition();
        if(topicType!=null){
            condition.addItemFilterEmpty("name",MatchType.LIKE,"%"+topicType.getName()+"%");
            condition.addItemFilterEmpty("code",MatchType.EQ,topicType.getCode());
            condition.addItemFilterEmpty("parentCode",MatchType.EQ,topicType.getParentCode());
            return new TftQuery<TopicType>(condition);
        }
        return null;
    }
}
