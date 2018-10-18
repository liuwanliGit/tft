package com.tft.framework.menuRes.service.impl;

import com.tft.framework.common.bean.TftBaseRuntimeException;
import com.tft.framework.common.bean.TftSort;
import com.tft.framework.menuRes.bean.MenuRes;
import com.tft.framework.menuRes.dao.MenuResRepository;
import com.tft.framework.menuRes.service.MenuResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * @author lwl liuwanli_eamil@163.com	2018/5/17 23:08
 * @ClassName MenuResServiceImpl
 * @Description
 * @Modifier {修改人、修改时间、修改事由}
 * @see #
 */
@Service("menuResService")
public class MenuResServiceImpl implements MenuResService {

    @Autowired
    private MenuResRepository menuResRepository;

    /**
     * <br>功能描述:  删除菜单
     * <br>处理逻辑:  删除菜单必须先确定其下无子菜单
     * <br>作者: lwl liuwanli_eamil@163.com 2018/6/15 16:55
     * <br>修改记录: {修改人 修改原因 修改时间}
     *
     * @param menuRes@throws
     * @return void
     * @see #
     */
    @Override
    public void removeMenusRes(MenuRes menuRes) throws Exception {
        menuResRepository.delete(menuRes);
    }

    /**
     * <br>功能描述:  按条件查询菜单
     * <br>处理逻辑:
     * <br>作者: lwl liuwanli_eamil@163.com 2018/6/15 16:59
     * <br>修改记录: {修改人 修改原因 修改时间}
     *
     * @param menuRes@throws
     * @return void
     * @see #
     */
    @Override
    public List<MenuRes> searchMenusRes(MenuRes menuRes, TftSort sort) throws Exception {
        List<MenuRes> menuRess = menuResRepository.findAll(menuResRepository.createQuery(menuRes),sort.toSort());
        return menuRess;
    }

    /**
     * <br>功能描述:  通过id查询菜单详情
     * <br>处理逻辑:
     * <br>作者: lwl liuwanli_eamil@163.com 2018/6/15 17:00
     * <br>修改记录: {修改人 修改原因 修改时间}
     *
     * @param id
     * @throws
     * @return com.tft.framework.menuRes.bean.MenuRes
     * @see #
     */
    @Override
    public MenuRes searchById(String id) throws Exception {
        return menuResRepository.findById(id).orElse(new MenuRes());
    }

    /**
     * <br>功能描述:  通过url查询菜单详情
     * <br>处理逻辑:
     * <br>作者: lwl liuwanli_eamil@163.com 2018/6/15 17:00
     * <br>修改记录: {修改人 修改原因 修改时间}
     *
     * @param url@throws
     * @return com.tft.framework.menuRes.bean.MenuRes
     * @see #
     */
    @Override
    public List<MenuRes> searchByUrl(String url) throws Exception {
        return null;
    }

    /**
     * <br>功能描述:  通过父节点id查询子菜单列表（懒加载）
     * <br>处理逻辑:
     * <br>作者: lwl liuwanli_eamil@163.com 2018/6/15 17:00
     * <br>修改记录: {修改人 修改原因 修改时间}
     *
     * @param pId@throws
     * @return java.util.List<com.tft.framework.menuRes.bean.MenuRes>
     * @see #
     */
    @Override
    public List<MenuRes> searchByParent(String pId) throws Exception {

        return menuResRepository.findMenuResByPid(pId);
    }

    /**
     * <br>功能描述:  保存菜单
     * <br>处理逻辑:
     * <br>作者: lwl liuwanli_eamil@163.com 2018/6/15 16:40
     * <br>修改记录: {修改人 修改原因 修改时间}
     * @param menuRes
     * @throws
     * @return void
     * @see #
     */
    @Override
    public void createOrModifyMenusRes(MenuRes menuRes) throws Exception {
        if(menuRes==null){
            throw new TftBaseRuntimeException("请传入菜单信息");
        }
        menuResRepository.save(menuRes);
    }

    @Override
    public List<MenuRes> searchAllMenusRes()throws Exception{
        List<MenuRes> menuRes = menuResRepository.findAll();
        return menuRes;
    }
}
