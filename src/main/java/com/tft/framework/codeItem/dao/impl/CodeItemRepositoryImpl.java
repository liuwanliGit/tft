package com.tft.framework.codeItem.dao.impl;

import com.tft.framework.codeItem.bean.CodeItem;
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
 * @ClassName CodeItemRepositoryImpl
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public class CodeItemRepositoryImpl extends TftBaseDaoImpl<CodeItem> {
    @Autowired
    private EntityManager entityManager;
    
    /**
    <br>功能描述:  创建查询器
    <br>处理逻辑:
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/7 0:36
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [codeItem]
     * @throws 
     * @return com.tft.framework.common.core.TftQuery<com.tft.framework.codeItem.bean.CodeItem>
     * @see #
     */
    public TftQuery<CodeItem> createQuery(CodeItem codeItem){
        QueryCondition condition = new QueryCondition();
        if(codeItem!=null){
            //类型编码  eq
            if(StringHelper.isNotEmpty(codeItem.getTypeCode())){
                condition.addItem("typeCode",MatchType.EQ,codeItem.getTypeCode());
            }
            //代码项值  eq
            if(StringHelper.isNotEmpty(codeItem.getCode())){
                condition.addItem("code",MatchType.EQ,codeItem.getCode());
            }
            // 查询码  eq
            if(StringHelper.isNotEmpty(codeItem.getAlias())){
                condition.addItem("alias",MatchType.EQ,codeItem.getAlias());
            }
            //是否可选  eq
            if(codeItem.getIsAvailable()!=null){
                condition.addItem("isAvailable",MatchType.EQ,codeItem.getIsAvailable());
            }
            //name like
            if(StringHelper.isNotEmpty(codeItem.getName())){
                condition.addItem("name",MatchType.LIKE,"%"+codeItem.getName()+"%");
            }
            return new TftQuery<CodeItem>(condition);
        }
        return null;
    }

}
