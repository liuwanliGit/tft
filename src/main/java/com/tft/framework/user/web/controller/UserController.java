package com.tft.framework.user.web.controller;

import com.tft.framework.common.util.HttpIO;
import com.tft.framework.common.util.TftWebContext;
import com.tft.framework.user.bean.User;
import com.tft.framework.user.service.UserService;
import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/24 22:43
 *
 * @ClassName UserController
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@RestController
@RequestMapping("/api/user/*")
public class UserController {

    @Autowired
    private UserService userService;
    /**
    <br>功能描述:  新增或修改用户
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/24 22:45
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [request, response]
     * @throws
     * @return void
     * @see #
     */
    @RequestMapping("saveUser")
    public void saveUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpIO httpIO = new HttpIO(request,response);
        try {
            User user = httpIO.getObject(User.class);
            if(user==null){
                httpIO.setResultFalse();
                httpIO.setMsg("请传入用户信息");
                return;
            }
            if(StringHelper.isEmpty(user.getId())){
                user = userService.createUser(user);
            }else{
                user = userService.modifyUser(user);
            }
            httpIO.setObject(user);
            httpIO.setSuccessMsg("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            httpIO.setFailedMsg("保存失败",e);
        } finally {
            httpIO.outAjaxDate();
        }
    }

    /**
    <br>功能描述:  用户登录
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/24 22:51
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [request, response]
     * @throws 
     * @return void
     * @see #
     */
    @RequestMapping("login")
    public void doLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpIO httpIO = new HttpIO(request,response);
        try {
            String userName = httpIO.getValue("userName");
            String passWord = httpIO.getValue("passWord");
            User user = new User();
            user.setUserName(userName);
            user.setPassWord(passWord);
            boolean result = userService.commitLogin(user);
            if(result){
                httpIO.setResultTrue();
            }else{
                httpIO.setResultFalse();
                httpIO.setMsg("用户名或密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            httpIO.setFailedMsg("登录失败",e);
        } finally {
            httpIO.outAjaxDate();
        }
    }
    /**
    <br>功能描述:  校验用户名是否唯一
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/24 22:52
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [request, response]
     * @throws 
     * @return void
     * @see #
     */
    @RequestMapping("isOnlyName")
    public void doIsOnlyName(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpIO httpIO = new HttpIO(request,response);
        try {
            String userName = httpIO.getValue("userName");
            boolean result = userService.commitIsOnlyName(userName);
            if(result){
                httpIO.setResultTrue();
            }else{
                httpIO.setResultFalse();
                httpIO.setMsg("用户名已被使用");
            }
        } catch (Exception e) {
            e.printStackTrace();
            httpIO.setFailedMsg("校验失败",e);
        } finally {
            httpIO.outAjaxDate();
        }
    }
    /**
    <br>功能描述:  获取当前登录人信息
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/24 22:57
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [request, response]
     * @throws 
     * @return void
     * @see #
     */
    @RequestMapping("getUserInfo")
    public void doGetUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpIO httpIO = new HttpIO(request,response);
        try {
            User user = TftWebContext.getLoginUser();
            httpIO.setObject(user);
            httpIO.setResultTrue();
        } catch (Exception e) {
            e.printStackTrace();
            httpIO.setFailedMsg("获取登录人信息失败",e);
        } finally {
            httpIO.outAjaxDate();
        }
    }
    /**
    <br>功能描述:  退出登录
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/24 23:21
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [request, response]
     * @throws 
     * @return void
     * @see #
     */
    @RequestMapping("unlogin")
    public void doUnlogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpIO httpIO = new HttpIO(request,response);
        try {
            User user = httpIO.getObject(User.class);
            userService.commitUnLogin(user);
            httpIO.setResultTrue();
        } catch (Exception e) {
            e.printStackTrace();
            httpIO.setFailedMsg("退出登录失败",e);
        } finally {
            httpIO.outAjaxDate();
        }
    }
}
