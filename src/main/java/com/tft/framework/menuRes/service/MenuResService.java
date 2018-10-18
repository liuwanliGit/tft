package com.tft.framework.menuRes.service;

import com.tft.framework.common.bean.TftSort;
import com.tft.framework.menuRes.bean.MenuRes;

import java.util.List;


public interface MenuResService {

    /**
     * @Title: searchMenuRes
     * @Description:  按条件加载菜单
     * @处理逻辑 
     * @param
     * @throws 
     * @return java.util.List<com.tft.framework.menuRes.bean.MenuRes>
     * @author lwl liuwanli_eamil@163.com 2018/5/17 23:32
     * @modify {修改人 修改原因 修改时间}
     * @see #
     */
    public List<MenuRes> searchAllMenusRes()throws Exception;

    /**
    <br>功能描述:  保存菜单
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/6/15 16:45
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [menuRes]
     * @throws 
     * @return void
     * @see #
     */
    public void createOrModifyMenusRes(MenuRes menuRes)throws Exception;
    
    /**
    <br>功能描述:  删除菜单
    <br>处理逻辑:  删除菜单必须先确定其下无子菜单
    <br>作者: lwl liuwanli_eamil@163.com 2018/6/15 16:55
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [menuRes]
     * @throws 
     * @return void
     * @see #
     */
    public void removeMenusRes(MenuRes menuRes)throws Exception;
    
    /**
    <br>功能描述:  按条件查询菜单
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/6/15 16:59
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [menuRes]
     * @throws 
     * @return void
     * @see #
     */
    public List<MenuRes> searchMenusRes(MenuRes menuRes,TftSort sort)throws Exception;
    /**
    <br>功能描述:  通过id查询菜单详情
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/6/15 17:00
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [id]
     * @throws 
     * @return com.tft.framework.menuRes.bean.MenuRes
     * @see #
     */
    public MenuRes searchById(String id)throws Exception;
    /**
     <br>功能描述:  通过url查询菜单详情
     <br>处理逻辑:
     <br>作者: lwl liuwanli_eamil@163.com 2018/6/15 17:00
     <br>修改记录: {修改人 修改原因 修改时间}
     * @param [id]
     * @throws
     * @return com.tft.framework.menuRes.bean.MenuRes
     * @see #
     */
    public List<MenuRes> searchByUrl(String url)throws Exception;
    /**
    <br>功能描述:  通过父节点id查询子菜单列表（懒加载）
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/6/15 17:00
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [pIdOrUrl]
     * @throws 
     * @return java.util.List<com.tft.framework.menuRes.bean.MenuRes>
     * @see #
     */
    public List<MenuRes> searchByParent(String pId)throws Exception;
}
