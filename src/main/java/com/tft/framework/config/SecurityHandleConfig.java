package com.tft.framework.config;

import com.tft.framework.common.bean.Token;
import com.tft.framework.common.cons.CommConstant;
import com.tft.framework.common.service.TokenService;
import com.tft.framework.common.util.HttpIO;
import com.tft.framework.config.securityHandle.bean.LoginUser;
import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/10/13 14:01
 *
 * @ClassName SecurityHandleConfig
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Configuration
public class SecurityHandleConfig {

    @Autowired
    @Qualifier("tokenService")
    private TokenService tokenService;

    /**
     * 登陆成功，返回Token
     *
     * @return
     */
    @Bean("authenticationSuccessHandler")
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            
            /**
            <br>功能描述:  登录成功，返回token信息
            <br>处理逻辑:  
            <br>作者: lwl liuwanli_eamil@163.com 2018/10/13 14:10
            <br>修改记录: {修改人 修改原因 修改时间}
             * @param [request, response, authentication]
             * @throws 
             * @return void
             * @see #
             */
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {
                if (response.isCommitted()) {
                    System.out.println("Can't redirect");
                    return;
                }
                //返回token
                LoginUser loginUser = (LoginUser) authentication.getPrincipal();
                Token token = tokenService.saveToken(loginUser);
                HttpIO httpIO = new HttpIO(request,response);
                httpIO.setKeyValue("loginUser",loginUser);
                httpIO.setKeyValue("token",token);
                httpIO.setHttpStatusCode(HttpStatus.OK);
                httpIO.setSuccessMsg("登录成功");
                httpIO.outAjaxDate();
            }
        };
    }

    /**
     * 登陆失败
     *
     * @return
     */
    @Bean("authenticationFailureHandler")
    public AuthenticationFailureHandler loginFailureHandler() {
        return new AuthenticationFailureHandler() {

            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                                AuthenticationException exception) throws IOException, ServletException {
                String msg = null;
                if (exception instanceof BadCredentialsException) {
                    msg = "密码错误";
                } else {
                    msg = exception.getMessage();
                }
                HttpIO httpIO = new HttpIO(request,response);
                httpIO.setHttpStatusCode(HttpStatus.UNAUTHORIZED);
                httpIO.setResultFalse();
                httpIO.setMsg(msg);
                httpIO.outAjaxDate();
            }
        };

    }

    /**
     * 未登录，返回401	 *
     * @return
     */
    @Bean("authenticationEntryPoint")
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPoint() {

            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response,
                                 AuthenticationException authException) throws IOException, ServletException {
                HttpIO httpIO = new HttpIO(request,response);
                httpIO.setHttpStatusCode(HttpStatus.UNAUTHORIZED);
                httpIO.setResultFalse();
                httpIO.setMsg("请先登录");
                httpIO.outAjaxDate();
            }
        };
    }

    /**
     * 退出处理
     *
     * @return
     */
    @Bean("logoutSuccessHandler")
    public LogoutSuccessHandler logoutSussHandler() {
        return new LogoutSuccessHandler() {

            @Override
            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
                HttpIO httpIO = new HttpIO(request,response);
                httpIO.setHttpStatusCode(HttpStatus.OK);
                httpIO.setSuccessMsg("退出成功");
                String token = request.getParameter(CommConstant.TOKEN_KEY);
                if (StringHelper.isEmpty(token)) {
                    token = request.getHeader(CommConstant.TOKEN_KEY);
                }
                tokenService.deleteToken(token);
                httpIO.outAjaxDate();
            }
        };

    }

}
