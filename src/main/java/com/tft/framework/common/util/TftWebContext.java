package com.tft.framework.common.util;

import com.tft.framework.user.bean.User;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author lwl liuwanli_eamil@163.com	2018/6/15 14:06
 * @Description
 * @ClassName TftWebContext
 * @Modifier {修改人、修改时间、修改事由}
 * @see #
 */
public class TftWebContext {
    
    /**
     *
     * @Title: getHttpServletRequest
     * @Description:  获取HttpServletRequest
     * @处理逻辑 
     * @param []
     * @throws 
     * @return javax.servlet.http.HttpServletRequest
     * @author lwl liuwanli_eamil@163.com 2018/6/15 14:29
     * @modify {修改人 修改原因 修改时间}
     * @see #
     *
     */
    public static HttpServletRequest getHttpServletRequest() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();

        return (attributes != null) ? ((ServletRequestAttributes) attributes).getRequest() : null;
    }
    /** 
     * @Title: getHttpSession
     * @Description:  
     * @处理逻辑 
     * @param []
     * @throws 
     * @return javax.servlet.http.HttpSession
     * @author lwl liuwanli_eamil@163.com 2018/6/15 14:47
     * @modify {修改人 修改原因 修改时间}
     * @see #
     */
    public static HttpSession getHttpSession() {
        HttpServletRequest request = getHttpServletRequest();

        return (request == null) ? null : request.getSession();
    }
    /**
    <br>功能描述:  往session中存数据
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/24 22:39
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [key, obj]
     * @throws 
     * @return void
     * @see #
     */
    public static void putValue(String key,Object obj){
        HttpSession session = getHttpSession();
        if(session==null) return;
        session.setAttribute(key,obj);
    }

    /**
    <br>功能描述: 从session中取数据
    <br>处理逻辑:
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/24 22:40
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [key]
     * @throws
     * @return java.lang.Object
     * @see #
     */
    public static Object getValue(String key){
        HttpSession session = getHttpSession();
        if(session==null) return null;
        System.out.println(session.getId());
        return session.getAttribute(key);
    }
    /**
    <br>功能描述:  存储当前登录人
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/9/12 21:26
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [user]
     * @throws 
     * @return void
     * @see #
     */
    public static void setLoginUser(User user){
        TftWebContext.putValue("loginUser",user);
    }
    
    /**
    <br>功能描述:  获取当前登录人
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/9/12 21:28
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param []
     * @throws 
     * @return com.tft.framework.user.bean.User
     * @see #
     */
    public static User getLoginUser(){
        return (User) TftWebContext.getValue("loginUser");
    }
}
