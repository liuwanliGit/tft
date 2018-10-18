package com.tft.framework.menuRes.bean;

import com.tft.framework.common.bean.BasicBean;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

/**
 * <br>类描述：菜单资源实体类
 * <br>author： lwl liuwanli_eamil@163.com	2018/6/15 16:15
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Entity
@Table(name = "TFT_MENU_MENURES")
public class MenuRes extends BasicBean{

    @NotEmpty(message = "菜单名称不能为空")
    @Length(max=32,message = "菜单名称长度不能超过32位")
    private String name;//菜单名称

    private String url;//页面地址

    private String type;//页面类型  10主页面、20内嵌页面、30.folder页面。

    @NotEmpty(message="请选择父节点")
    @Length(max=32,message = "系统错误，请联系管理员")
    private String pid;//父页面id

    @NotEmpty(message = "系统错误，请联系管理员")
    @Length(max=2,message = "系统错误，请联系管理员")
    private String isCommon;//授权方式

    private String isUseable;//是否启用

    private Integer sort;//排序

    private String remark;	//备注

    private String icon;  //菜单图标

    @Transient
    private Boolean haveChild;//该节点是否有子节点

    @Transient
    private String pName;//父菜单名称

    public String getName() {
        return name;
    }
    

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getIsCommon() {
        return isCommon;
    }

    public void setIsCommon(String isCommon) {
        this.isCommon = isCommon;
    }

    public String getIsUseable() {
        return isUseable;
    }

    public void setIsUseable(String isUseable) {
        this.isUseable = isUseable;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getHaveChild() {
        return haveChild;
    }

    public void setHaveChild(Boolean haveChild) {
        this.haveChild = haveChild;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }
}
