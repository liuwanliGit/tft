package com.tft.framework.auth.bean;

import com.tft.framework.common.bean.BasicBean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <br>类描述：人员角色关联
 * <br>author： lwl liuwanli_eamil@163.com	2018/10/8 15:44
 *
 * @ClassName UserRole
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Entity
@Table(name = "TFT_MENU_USERROLE")
public class UserRole  extends BasicBean {
    private String userId;    //用户id

    private String roleCode;    //角色编码

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
