package com.tft.framework.auth.bean;

import com.tft.framework.common.bean.BasicBean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <br>类描述：菜单资源角色授权
 * <br>author： lwl liuwanli_eamil@163.com	2018/10/11 22:38
 *
 * @ClassName MenuResAuth
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Entity
@Table(name = "TFT_AUTH_MENURESAUTH")
public class MenuResAuth extends BasicBean {
    private String menuResId;   //菜单id

    private String roleCode;    //角色编码

    public String getMenuResId() {
        return menuResId;
    }

    public void setMenuResId(String menuResId) {
        this.menuResId = menuResId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
