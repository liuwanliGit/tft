package com.tft.framework.user.service;

import com.tft.framework.user.bean.User;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/24 22:11
 *
 * @ClassName UserService
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public interface UserService {

    /**
    <br>功能描述:  创建user对象
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/24 22:12
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [user]
     * @throws 
     * @return com.tft.framework.user.bean.User
     * @see #
     */
    public User createUser(User user);
    
    /**
    <br>功能描述:  修改用户信息
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/24 22:13
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [user]
     * @throws 
     * @return com.tft.framework.user.bean.User
     * @see #
     */
    public User modifyUser(User user);
    /**
    <br>功能描述:  通过用户名加载用户
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/24 22:14
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [userName]
     * @throws 
     * @return com.tft.framework.user.bean.User
     * @see #
     */
    public User searchUserByName(String userName);

    /**
    <br>功能描述:  用户名可用性校验
    <br>处理逻辑:  判断userName是不是唯一的，若不是则返false
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/24 22:17
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [userName, id]
     * @throws 
     * @return boolean
     * @see #
     */
    public boolean commitIsOnlyName(String userName);

    /**
    <br>功能描述:  用户登录
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/24 22:18
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [user]
     * @throws 
     * @return boolean
     * @see #
     */
    public boolean commitLogin(User user);

    /**
    <br>功能描述:  用户注销登录
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/24 22:19
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [user]
     * @throws 
     * @return boolean
     * @see #
     */
    public void commitUnLogin(User user);
    /**
    <br>功能描述:  通过邮箱或手机号查询用户信息
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/10/8 15:25
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [emailOrMobile]
     * @throws 
     * @return com.tft.framework.user.bean.User
     * @see #
     */
    public User searchByEmailOrMobile(String emailOrMobile);
}
