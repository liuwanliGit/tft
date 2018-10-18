package com.tft.framework.config.securityHandle.service.impl;

import com.tft.framework.auth.bean.AuthRole;
import com.tft.framework.config.securityHandle.bean.LoginUser;
import com.tft.framework.user.bean.User;
import com.tft.framework.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
<br>功能描述:  用户登录处理
<br>处理逻辑:
 1.根据用户输入的账号（手机号/邮箱号/用户名等），加载用户信息；
 2.加载用户授权新
<br>作者: lwl liuwanli_eamil@163.com 2018/10/13 14:39
<br>修改记录: {修改人 修改原因 修改时间}
 * @param 
 * @throws 
 * @return 
 * @see #
 */
@Service("customUserDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserService userService;
	@Override
	public UserDetails loadUserByUsername(String emailOrMobile) throws UsernameNotFoundException {
		
		User user = null;
		try {
			user = userService.searchByEmailOrMobile(emailOrMobile);
		} catch (Exception e) {
			e.printStackTrace();
			 throw new UsernameNotFoundException("查询用户出错");
		}
		if(user==null){
            throw new UsernameNotFoundException("用户：【"+emailOrMobile+"】不存在");
        }
		LoginUser loginUser = new LoginUser(user);
		loginUser.setUserName(emailOrMobile);
		List<AuthRole> authRoles = new ArrayList<>();
		loginUser.setRoles(authRoles);
        return loginUser;
	}
}
