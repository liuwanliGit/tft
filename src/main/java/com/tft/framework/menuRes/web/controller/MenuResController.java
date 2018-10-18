package com.tft.framework.menuRes.web.controller;

import com.tft.framework.common.bean.TftSort;
import com.tft.framework.common.util.HttpIO;
import com.tft.framework.menuRes.bean.MenuRes;
import com.tft.framework.menuRes.service.MenuResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author lwl liuwanli_eamil@163.com	2018/5/27 23:37
 * @Description
 * @ClassName MenuResController
 * @Modifier {修改人、修改时间、修改事由}
 * @see #
 */
@RestController
@RequestMapping("/api/menuRes/*")
public class MenuResController {
     @Autowired
    private MenuResService menuResService;
    /**
    <br>功能描述:加载菜单
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/6/15 15:53
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [request, response]
     * @throws 
     * @return void
     * @see #
     */
     @RequestMapping("loadAllMenuRes")
    public void loadAllMenuRes(HttpServletRequest request, HttpServletResponse response) throws Exception {
         HttpIO httpIO = new HttpIO(request,response);
        try {
            MenuRes menuRes = httpIO.getObject(MenuRes.class);
            TftSort sort = httpIO.getSort();
            List<MenuRes> menuRess = menuResService.searchMenusRes(menuRes,sort);
            httpIO.setObjects(menuRess);
            httpIO.setSuccessMsg("加载成功");
        } catch (Exception e) {
            e.printStackTrace();
            httpIO.setFailedMsg("加载失败",e);
        } finally {
            httpIO.outAjaxDate();
        }
    }
    /**
    <br>功能描述:  保存菜单资源
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/6/15 16:51
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [request, response]
     * @throws 
     * @return void
     * @see #
     */
    @RequestMapping("saveMenuRes")
    public void saveMenuRes(HttpServletRequest request,HttpServletResponse response)throws Exception{
        HttpIO httpIO = new HttpIO(request,response);
        try {
            MenuRes menuRes = httpIO.getObject(MenuRes.class);
            menuResService.createOrModifyMenusRes(menuRes);
            httpIO.setObject(menuRes);
            httpIO.setSuccessMsg("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            httpIO.setFailedMsg("保存失败",e);
        } finally {
            httpIO.outAjaxDate();
        }
    }
    /**
    <br>功能描述:  查询菜单详情
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/6/18 20:35
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [request, response]
     * @throws 
     * @return void
     * @see #
     */
    @RequestMapping("searchMenuResById")
    public void searchMenuResById(HttpServletRequest request,HttpServletResponse response)throws Exception{
        HttpIO httpIO = new HttpIO(request,response);
        try {
            String id = httpIO.getObjectId();
            MenuRes menuRes = menuResService.searchById(id);
            httpIO.setObject(menuRes);
            httpIO.setSuccessMsg("加载成功");
        } catch (Exception e) {
            e.printStackTrace();
            httpIO.setFailedMsg("加载失败",e);
        } finally {
            httpIO.outAjaxDate();
        }
    }
    /**
    <br>功能描述:  通过父菜单加载子菜单
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/6/23 23:28
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [request, response]
     * @throws 
     * @return void
     * @see #
     */
    @RequestMapping("searchMenuResByPid")
    public void searchMenuResByPid(HttpServletRequest request,HttpServletResponse response)throws Exception{
        HttpIO httpIO = new HttpIO(request,response);
        try {
            String pId = httpIO.getObjectId();
            List menuRess = menuResService.searchByParent(pId);
            httpIO.setObjects(menuRess);
            httpIO.setSuccessMsg("加载成功");
        } catch (Exception e) {
            e.printStackTrace();
            httpIO.setFailedMsg("加载失败",e);
        } finally {
            httpIO.outAjaxDate();
        }
    }


}
