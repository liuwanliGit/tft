package com.tft.framework.common.bean;

import com.tft.framework.common.tftEnum.MatchType;
import org.hibernate.internal.util.StringHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/6/30 11:04
 *
 * @ClassName QueryCondition
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public class QueryCondition<T> {
    public List<QueryConditionItem> items = new ArrayList<>();
    public class QueryConditionItem{
        public String fieldName;
        public Object queryValue;
        public MatchType matchType;

        public String getFieldName() { return fieldName; }
        public Object getQueryValue() {
            return queryValue;
        }
        public MatchType getMatchType() {
            return matchType;
        }
        public QueryConditionItem(String fieldName, MatchType matchType, Object queryValue){
            this.fieldName = fieldName;
            this.queryValue = queryValue;
            this.matchType = matchType;
        }
    }
    public QueryCondition<T> addItem(String fieldName, MatchType matchType, Object queryValue){
        items.add(new QueryConditionItem(fieldName,matchType,queryValue));
        return this;
    }
    public QueryCondition<T> addItemFilterEmpty(String fieldName, MatchType matchType, Object queryValue){
        if(queryValue==null){
            return this;
        }
        if(queryValue instanceof String && StringHelper.isEmpty((String)queryValue)){
            return this;
        }
        items.add(new QueryConditionItem(fieldName,matchType,queryValue));
        return this;
    }
    public List<QueryConditionItem> getItems(){
        return this.items;
    }
    public Class getClazz(T t){
        return t.getClass();
    }
}
