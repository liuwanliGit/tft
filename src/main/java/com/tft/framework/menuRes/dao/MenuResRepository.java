package com.tft.framework.menuRes.dao;

import com.tft.framework.common.core.TftBaseDao;
import com.tft.framework.menuRes.bean.MenuRes;

import java.util.List;

public interface MenuResRepository extends TftBaseDao<MenuRes> {

    public List findMenuResByPid(String pId);
    public List<MenuRes> searchMenuRes(MenuRes menuRes);
}
