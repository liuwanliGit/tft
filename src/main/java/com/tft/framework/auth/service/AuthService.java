package com.tft.framework.auth.service;

import java.util.Map;

/**
 * <br>类描述：权限管理service接口
 * <br>author： lwl liuwanli_eamil@163.com	2018/10/11 23:25
 *
 * @ClassName AuthService
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public interface AuthService {
    
    
    /**
    <br>功能描述:  系统启动时加载菜单和action的请求权限配置信息，缓存到Redis中
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/10/11 23:30
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param []
     * @throws 
     * @return void
     * @see #
     */
    public Map<String,String> loadUrlAuthInfo()throws Exception;

    /**
    <br>功能描述:  重新加载url授权信息
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/10/12 16:56
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param []
     * @throws 
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @see #
     */
    public Map<String,String> reLoadUrlAuthInfo()throws Exception;
}
