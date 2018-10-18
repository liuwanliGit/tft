package com.tft.framework.user.dao;

import com.tft.framework.common.core.TftBaseDao;
import com.tft.framework.common.core.TftQuery;
import com.tft.framework.user.bean.User;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/24 22:08
 *
 * @ClassName UserRepository
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public interface UserRepository extends TftBaseDao<User> {
    public TftQuery createQuery(User user);
}
