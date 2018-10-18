package com.tft.forum.topic.bean;

import com.tft.framework.common.bean.BasicBean;

import javax.persistence.Entity;

/**
 * <br>类描述：主题分类
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/28 10:29
 *
 * @ClassName TopicType
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Entity(name="tft_forum_topicType")
public class TopicType extends BasicBean {

    private String name;   //主题类别名称

    private String code;   //主题类别编码

    private String parentCode; //父类别编码

    private String describe;    //描述

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
