package com.tft.framework.auth.bean;

import com.tft.framework.common.bean.BasicBean;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <br>类描述：请求路径授权
 * <br>author： lwl liuwanli_eamil@163.com	2018/10/11 23:06
 *
 * @ClassName ActionAuth
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Entity
@Table(name = "TFT_AUTH_ACTIONAUTH")
public class ActionAuth extends BasicBean {

    private String action;  //请求路径，期望不由数据库存储来，直接可以通过扫描代码得到所有action

    private String roleCode;    //授权角色编码，多个角色编码以逗号分隔，如：user，htgly，sjry

    @Transient
    private String roleName;    //授权角色名称，不持久化数据库

    private Boolean isCommon;   //标记该action是否为公开的,默认为否

    private String describe;    //请求路径描述

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Boolean getCommon() {
        return isCommon;
    }

    public void setCommon(Boolean common) {
        isCommon = common;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
