package com.tft.framework.user.dao.impl;

import com.tft.framework.common.bean.QueryCondition;
import com.tft.framework.common.core.TftQuery;
import com.tft.framework.common.tftEnum.MatchType;
import com.tft.framework.user.bean.User;
import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/24 22:10
 *
 * @ClassName UserRepositoryImpl
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public class UserRepositoryImpl {
    @Autowired
    private EntityManager entityManager;

    public TftQuery<User> createQuery(User user){
        QueryCondition condition = new QueryCondition();
        if(user!=null){
            if(StringHelper.isNotEmpty(user.getUserName())){
                condition.addItem("userName",MatchType.EQ,user.getUserName());
            }
            return new TftQuery<User>(condition);
        }
        return null;
    }
}
