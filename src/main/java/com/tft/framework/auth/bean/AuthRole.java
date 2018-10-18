package com.tft.framework.auth.bean;

import com.tft.framework.common.bean.BasicBean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <br>类描述：权限角色
 * <br>author： lwl liuwanli_eamil@163.com	2018/10/8 15:41
 * @ClassName AuthRole
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Entity
@Table(name = "TFT_AUTH_AUTHROLE")
public class AuthRole extends BasicBean {

    private String roleCode;  //角色编码

    private String roleName;  //角色名称

    private String roleType; //角色类型,保留字段

    private String describe;   //描述

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

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
