package com.tft.framework.user.service.impl;

import com.tft.framework.common.bean.TftBaseBizException;
import com.tft.framework.common.util.TftWebContext;
import com.tft.framework.user.bean.User;
import com.tft.framework.user.dao.UserRepository;
import com.tft.framework.user.service.UserService;
import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/24 22:20
 *
 * @ClassName UserServiceImpl
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    /**
     * <br>功能描述:  创建user对象
     * <br>处理逻辑:
     * <br>作者: lwl liuwanli_eamil@163.com 2018/7/24 22:12
     * <br>修改记录: {修改人 修改原因 修改时间}
     *
     * @param user@throws
     * @return com.tft.framework.user.bean.User
     * @see #
     */
    @Override
    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

    /**
     * <br>功能描述:  修改用户信息
     * <br>处理逻辑:
     * <br>作者: lwl liuwanli_eamil@163.com 2018/7/24 22:13
     * <br>修改记录: {修改人 修改原因 修改时间}
     *
     * @param user@throws
     * @return com.tft.framework.user.bean.User
     * @see #
     */
    @Override
    public User modifyUser(User user) {
        userRepository.save(user);
        return user;
    }

    /**
     * <br>功能描述:  通过用户名加载用户
     * <br>处理逻辑:
     * <br>作者: lwl liuwanli_eamil@163.com 2018/7/24 22:14
     * <br>修改记录: {修改人 修改原因 修改时间}
     *
     * @param userName@throws
     * @return com.tft.framework.user.bean.User
     * @see #
     */
    @Override
    public User searchUserByName(String userName) {
        User user = new User();
        user.setUserName(userName);
        List<User> users = userRepository.findAll(userRepository.createQuery(user));
        if(users==null||users.size()<1){
            return null;
        }
        if(users.size()>1){
            throw new TftBaseBizException("数据异常,请联系管理员");
        }
        return users.get(0);
    }

    /**
     * <br>功能描述:  用户名可用性校验
     * <br>处理逻辑:  判断userName是不是唯一的，若不是则返false
     * <br>作者: lwl liuwanli_eamil@163.com 2018/7/24 22:17
     * <br>修改记录: {修改人 修改原因 修改时间}
     *
     * @param userName@throws
     * @return boolean
     * @see #
     */
    @Override
    public boolean commitIsOnlyName(String userName) {
        if(StringHelper.isEmpty(userName)){
            return false;
        }
        try {
            User user = this.searchUserByName(userName);
            if(user==null) return true;
            else return false;
        }catch (TftBaseBizException e){
            return false;
        }
    }

    /**
     * <br>功能描述:  用户登录
     * <br>处理逻辑:
     * <br>作者: lwl liuwanli_eamil@163.com 2018/7/24 22:18
     * <br>修改记录: {修改人 修改原因 修改时间}
     *
     * @param user@throws
     * @return boolean
     * @see #
     */
    @Override
    public boolean commitLogin(User user) {
        if(user==null) return false;
        User userTemp = this.searchUserByName(user.getUserName());
        if(userTemp==null) return false;
        if(userTemp.getPassWord().equals(user.getPassWord())){
            TftWebContext.setLoginUser(userTemp);
            return true;
        }
        return false;
    }

    /**
     * <br>功能描述:  用户注销登录
     * <br>处理逻辑:
     * <br>作者: lwl liuwanli_eamil@163.com 2018/7/24 22:19
     * <br>修改记录: {修改人 修改原因 修改时间}
     *
     * @param user@throws
     * @return boolean
     * @see #
     */
    @Override
    public void commitUnLogin(User user) {
        TftWebContext.putValue("user",null);
    }

    /**
     * <br>功能描述:  通过邮箱或手机号查询用户信息
     * <br>处理逻辑:
     * <br>作者: lwl liuwanli_eamil@163.com 2018/10/8 15:24
     * <br>修改记录: {修改人 修改原因 修改时间}
     *
     * @param emailOrMobile@throws
     * @return com.tft.framework.user.bean.User
     * @see #
     */
    @Override
    public User searchByEmailOrMobile(String emailOrMobile) {
        User loginUser = new User();
        loginUser.setEmail("1049545450@qq.com");
        loginUser.setPassWord("123");
        return loginUser;
    }
}
