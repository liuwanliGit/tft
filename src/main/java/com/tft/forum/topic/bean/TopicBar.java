package com.tft.forum.topic.bean;

import com.tft.framework.common.bean.BasicBean;

import javax.persistence.Entity;

/**
 * <br>类描述：主题吧
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/28 10:29
 *
 * @ClassName TopicBar
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Entity(name = "tft_forum_topicBar")
public class TopicBar extends BasicBean {

    private String name;//吧名

    private String type;//所属主题类别

    private String describe;//描述

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
