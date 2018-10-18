package com.tft.framework.config.securityHandle.bean;

import com.tft.framework.auth.bean.AuthRole;
import com.tft.framework.user.bean.User;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/10/8 15:32
 *
 * @ClassName LoginUser
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public class LoginUser extends User implements UserDetails {

    public LoginUser(User user){
        super.setUserName(user.getUserName());
        super.setPassWord(user.getPassWord());
        super.setEmail(user.getEmail());
        super.setBirthday(user.getBirthday());
        super.setHeadIcon(user.getHeadIcon());
        super.setSex(user.getSex());
        super.setSignature(user.getSignature());
        super.setTel(user.getTel());
    }

    private String loginUserName;       //用户名

    private String status;      //账号状态，启用禁用

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private List<AuthRole> roles;

    public List<AuthRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AuthRole> roles) {
        this.roles = roles;
    }

    private String token;

    /** 登陆时间戳（毫秒） */
    private Long loginTime;
    /** 过期时间戳 */
    private Long expireTime;

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserName(String userName) {
        this.loginUserName = userName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        try {
            if(roles!=null&&roles.size()>0){
                for(int i=0;i<roles.size();i++){
                    String roleCode = roles.get(i).getRoleCode();
                    authorities.add(new SimpleGrantedAuthority("ROLE_"+roleCode.toUpperCase()));
                }
            }
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("用户授权信息加载失败，请联系管理员");
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassWord();
    }

    @Override
    public String getUsername() {
        return loginUserName;
    }
    
    /**
    <br>功能描述:  账号是否未过期
    <br>处理逻辑:
     true:已过期
     false: 未过期
    <br>作者: lwl liuwanli_eamil@163.com 2018/10/13 15:29
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param []
     * @throws 
     * @return boolean
     * @see #
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    /**
    <br>功能描述:  账号是否未锁住
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/10/13 15:30
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param []
     * @throws 
     * @return boolean
     * @see #
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    /**
    <br>功能描述:  证书是否未过期
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/10/13 15:31
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param []
     * @throws 
     * @return boolean
     * @see #
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**
    <br>功能描述:  账号是否可用
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/10/13 15:32
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param []
     * @throws 
     * @return boolean
     * @see #
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
