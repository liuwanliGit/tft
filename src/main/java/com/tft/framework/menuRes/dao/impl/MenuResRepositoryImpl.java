package com.tft.framework.menuRes.dao.impl;

import com.tft.framework.common.bean.QueryCondition;
import com.tft.framework.common.core.TftQuery;
import com.tft.framework.common.tftEnum.MatchType;
import com.tft.framework.menuRes.bean.MenuRes;
import org.apache.poi.ss.formula.functions.T;
import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author lwl liuwanli_eamil@163.com	2018/5/26 14:25
 * @Description
 * @ClassName MenuResRepositoryImpl
 * @Modifier {修改人、修改时间、修改事由}
 * @see #
 */
public class MenuResRepositoryImpl {

    @Autowired
    private EntityManager entityManager;

    public List<MenuRes> searchMenuRes(MenuRes menuRes){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(MenuRes.class);
        Root root = criteriaQuery.from(MenuRes.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.like(root.get("name"),"%管理%"));
        predicates.add(criteriaBuilder.equal(root.get("type"),"10"));
        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public static Specification<T> createSpecification(Map<String,Objects> query){
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.like(root.get("name"),"%管理%"));
                predicates.add(criteriaBuilder.equal(root.get("type"),"10"));
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return criteriaQuery.getRestriction();
            }
        };
    }

    public TftQuery<MenuRes> createQuery(MenuRes menuRes){
        QueryCondition condition = new QueryCondition();
        if(menuRes!=null){

            if(StringHelper.isNotEmpty(menuRes.getName())){
                condition.addItem("name",MatchType.LIKE,"%"+menuRes.getName()+"%");
            }

            if(StringHelper.isNotEmpty(menuRes.getUrl())){
                condition.addItem("url",MatchType.EQ,menuRes.getUrl());
            }

            if(StringHelper.isNotEmpty(menuRes.getType())){
                condition.addItem("type",MatchType.IN,menuRes.getType().split(","));
            }

            if(StringHelper.isNotEmpty(menuRes.getPid())){
                condition.addItem("pid",MatchType.EQ,menuRes.getPid());
            }

            if(StringHelper.isNotEmpty(menuRes.getIsCommon())){
                condition.addItem("isCommon",MatchType.EQ,menuRes.getIsCommon());
            }
            if (StringHelper.isNotEmpty(menuRes.getIsUseable())){
                condition.addItem("isUseable",MatchType.EQ,menuRes.getIsUseable());
            }
            return new TftQuery<MenuRes>(condition);
        }
        return null;
    }
}
