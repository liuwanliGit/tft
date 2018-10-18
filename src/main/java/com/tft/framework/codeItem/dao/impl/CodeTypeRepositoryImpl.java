package com.tft.framework.codeItem.dao.impl;

import com.tft.framework.codeItem.bean.CodeType;
import com.tft.framework.common.bean.QueryCondition;
import com.tft.framework.common.core.TftBaseDaoImpl;
import com.tft.framework.common.core.TftQuery;
import com.tft.framework.common.tftEnum.MatchType;
import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/6/28 0:04
 *
 * @ClassName CodeTypeRepositoryImpl
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public class CodeTypeRepositoryImpl extends TftBaseDaoImpl<CodeType> {
    @Autowired
    private EntityManager entityManager;

    public TftQuery createQuery(CodeType codeType){
        QueryCondition condition = new QueryCondition();
        if(codeType!=null){
            //name  eq
            if(StringHelper.isNotEmpty(codeType.getName())){
                condition.addItem("name",MatchType.LIKE,"%"+codeType.getName()+"%");
            }
            //类型编码 eq
            if(StringHelper.isNotEmpty(codeType.getCode())){
                condition.addItem("code",MatchType.EQ,codeType.getCode());
            }
            // 父类型编码
            if(StringHelper.isNotEmpty(codeType.getParentCode())){
                condition.addItem("parentCode",MatchType.EQ,codeType.getParentCode());
            }
            return new TftQuery<CodeType>(condition);
        }
        return null;
    }
}
