package com.tft.framework.common.core;

import com.tft.framework.common.bean.QueryCondition;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/5 23:07
 *
 * @ClassName TftQuery
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public class TftQuery<T>  implements Specification<T> {

    private QueryCondition<T> queryCondition;

    private List<Predicate> predicates = new ArrayList<>();

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        for(QueryCondition.QueryConditionItem item:queryCondition.getItems()){
            switch (item.getMatchType()){
                case EQ:
                    predicates.add(criteriaBuilder.equal(root.get(item.getFieldName()),item.getQueryValue()));
                    break;
                case IN:
                    predicates.add(criteriaBuilder.in(root.get(item.getFieldName())).in(item.getQueryValue()));
                    break;
                case LIKE:
                    predicates.add(criteriaBuilder.like(root.get(item.getFieldName()),(String)(item.getQueryValue())));
                    break;
                case NULL:
                    predicates.add(criteriaBuilder.isNull(root.get(item.getFieldName())));
                    break;
                case Empty:
                    predicates.add(criteriaBuilder.isEmpty(root.get(item.getFieldName())));
                    break;
                case NotEQ:
                    predicates.add(criteriaBuilder.notEqual(root.get(item.getFieldName()),item.getQueryValue()));
                    break;
                case NotIN:
                    predicates.add(criteriaBuilder.not(criteriaBuilder.in(root.get(item.getFieldName())).in(item.getQueryValue())));
                    break;
                case NotLIKE:
                    predicates.add(criteriaBuilder.notLike(root.get(item.getFieldName()),(String) item.getQueryValue()));
                    break;
                case NotNULL:
                    predicates.add(criteriaBuilder.isNotNull(root.get(item.getFieldName())));
                    break;
                case NotEmpty:
                    predicates.add(criteriaBuilder.isNotEmpty(root.get(item.getFieldName())));
                    break;
                case GE:
                case GT:
                case LE:
                case LT:
                    this.setPredicate(item,root,criteriaQuery,criteriaBuilder);
                    break;
            }
        }
        if(predicates.size()>0){
           criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
         }
        return criteriaQuery.getRestriction();
    }

    public TftQuery(QueryCondition<T> queryCondition){
        this.queryCondition = queryCondition;
    }

    private <K> void  setPredicate(QueryCondition.QueryConditionItem item, Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder){
        if(item.getQueryValue() instanceof Integer){
            switch (item.getMatchType()){
                case GE:
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(item.getFieldName()),(Integer)(item.getQueryValue())));
                    break;
                case GT:
                    predicates.add(criteriaBuilder.greaterThan(root.get(item.getFieldName()),(Integer)(item.getQueryValue())));
                    break;
                case LE:
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(item.getFieldName()),(Integer)(item.getQueryValue())));
                    break;
                case LT:
                    predicates.add(criteriaBuilder.lessThan(root.get(item.getFieldName()),(Integer)(item.getQueryValue())));
                    break;
            }
        }
        else if((item.getQueryValue()) instanceof String){
            switch (item.getMatchType()){
                case GE:
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(item.getFieldName()),(String)(item.getQueryValue())));
                    break;
                case GT:
                    predicates.add(criteriaBuilder.greaterThan(root.get(item.getFieldName()),(String)(item.getQueryValue())));
                    break;
                case LE:
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(item.getFieldName()),(String)(item.getQueryValue())));
                    break;
                case LT:
                    predicates.add(criteriaBuilder.lessThan(root.get(item.getFieldName()),(String)(item.getQueryValue())));
                    break;
            }
        }
        else if((item.getQueryValue()) instanceof Date){
            switch (item.getMatchType()){
                case GE:
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(item.getFieldName()),(Date)(item.getQueryValue())));
                    break;
                case GT:
                    predicates.add(criteriaBuilder.greaterThan(root.get(item.getFieldName()),(Date)(item.getQueryValue())));
                    break;
                case LE:
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(item.getFieldName()),(Date)(item.getQueryValue())));
                    break;
                case LT:
                    predicates.add(criteriaBuilder.lessThan(root.get(item.getFieldName()),(Date)(item.getQueryValue())));
                    break;
            }
        }
    }
}
