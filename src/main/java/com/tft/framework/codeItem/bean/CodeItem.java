package com.tft.framework.codeItem.bean;

import com.tft.framework.common.bean.BasicBean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <br>类描述：代码项
 * <br>author： lwl liuwanli_eamil@163.com	2018/6/26 19:39
 *
 * @ClassName CodeItem
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Entity
@Table(name = "TFT_CODE_CODEITEM")
public class CodeItem extends BasicBean {

    private String typeCode;// 代码分类编码

    private String name;// 代码项名称

    private String code;// 代码项值

    private String alias;// 查询码

    private String featureCode;// 特征码

    private Integer isAvailable;// 是否可选

    private Integer sort;// 分类下排序号

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public Integer getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Integer isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
