package com.tft.framework.config.securityHandle.service.impl;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/10/10 10:20
 *
 * @ClassName TftAccessDecisionManager
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Service("tftAccessDecisionManager")
public class TftAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        FilterInvocation filterInvocation = (FilterInvocation) o;
        String url = filterInvocation.getRequestUrl();//获取发起请求的url
        //如果没有配置访问权限，则不通过
        if( configAttributes == null) {
            throw new AccessDeniedException("未配置访问权限");
        }
        //直接放行的资源
        if(this.isCommonResource(url)){
            return;
        }
        //数据库中存储的路径没有最前面的‘/’
        if(url.length()>1&&url.charAt(0)=='/'){
            url = url.substring(1);
        }
        //去除url后面的参数
        if(url.indexOf("?")>0){
            url = url.substring(0,url.indexOf("?"));
        }
//        //url权限集
//        Map<String, String> menuAccess = TftWebContext.getMenuAccess();
//        String permitAllUrl = ","+menuAccess.get("PERMITALL")+",";
//        if(permitAllUrl.contains(","+url+",")){
//            return;
//        }
//
//        //如果Principal的类型是user的类型，表示用户已登录
//        if(authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User){
//            User user = TftWebContext.getLoginUser();
//            if(user == null){
//                String emailOrMobile = ((org.springframework.security.core.userdetails.User)authentication.getPrincipal()).getUsername();
//                try {
//                    user = userService.searchByEmailOrMobile(emailOrMobile);
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                if(user==null){
//                    throw new UsernameNotFoundException("没有访问权限");
//                }
//                TftWebContext.putObjectToSession("user", user);
//            }
//
//            List<Role> roles = user.getRole();
//            if(roles==null||roles.size()<1){
//                throw new AccessDeniedException("没有访问权限");
//            }
//
//            for(int i=0;i<roles.size();i++){
//                String roleCode = roles.get(i).getCode().toUpperCase();
//                String urls = ","+menuAccess.get(roleCode)+",";
//                //如果该用户拥有的角色能访问的url中包含请求url，则放行
//                if(urls.contains(","+url+",")){
//                    return;
//                }
//            }
//            throw new AccessDeniedException("没有访问权限");
//        }else{
//            throw new UsernameNotFoundException("请先登录");
//        }
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    /**
    <br>功能描述:  配置公共资源
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/10/10 10:39
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [url]
     * @throws 
     * @return boolean
     * @see #
     */
    private boolean isCommonResource(String url){
//        boolean isCR =
//                url.indexOf("/views/common/")==0||
//                        url.indexOf("pushlet.srv")>0||
//                        url.indexOf("webSocket")>0||
//                        url.indexOf(".test")>0||
//                        (url.indexOf("/views/")==0&&url.indexOf("/static/")>0);
//        //action的控制交给controller层注解控制，如果有时间不嫌麻烦也可以像菜单一样存入数据进行控制
//        return isCR;
        return true;
    }
}
