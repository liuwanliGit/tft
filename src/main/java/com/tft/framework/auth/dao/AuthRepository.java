package com.tft.framework.auth.dao;

import com.tft.framework.common.bean.BasicBean;
import com.tft.framework.common.core.TftBaseDao;
import org.springframework.data.jpa.repository.Query;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/10/11 23:36
 *
 * @ClassName AuthRepository
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public interface AuthRepository extends TftBaseDao<BasicBean> {

    @Query(value = "select action,roleCode from TFT_AUTH_ACTIONAUTH " +
            "union " +
            "select m.url,u.roleCode from TFT_MENU_MENURES m ,TFT_AUTH_MENURESAUTH u where m.id = u.menuResId",nativeQuery = true
    )
    public String[][] searchAction();

}
