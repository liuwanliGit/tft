package com.tft.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * 
 * @ClassName: SecurityConfiguration 
 * @Description security配置文件
 * @author lwl liuwanli_eamil@163.com	2017年2月23日 下午3:25:12
 * @Modifier {修改人、修改时间、修改事由}	
 * @see #
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
	/**
	 * 配置用户验证service
	 */
	/***************SecurityHandleConfig.java  start*******************/
    @Autowired
    @Qualifier("authenticationSuccessHandler")
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    @Qualifier("authenticationFailureHandler")
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    @Qualifier("logoutSuccessHandler")
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    @Qualifier("authenticationEntryPoint")
    private AuthenticationEntryPoint authenticationEntryPoint;
    /***************SecurityHandleConfig.java  end*******************/

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("tftAccessDecisionManager")
    private AccessDecisionManager accessDecisionManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 基于token，所以不需要session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //自定义访问控制
    	http.authorizeRequests().accessDecisionManager(accessDecisionManager);

    	//资源请求由自定义访问控制管控，security对所有资源进行放权；
    	http.authorizeRequests().antMatchers("/**","*.html","*.css","*.js").permitAll();

        http.formLogin().loginPage("/login.html").loginProcessingUrl("/securty_login")
                .usernameParameter("emailOrMobile").passwordParameter("passWord")
                .successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler).and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
    	http.logout().logoutUrl("/securyty_logout").logoutSuccessUrl("/index.html").logoutSuccessHandler(logoutSuccessHandler);;
    	http.headers().frameOptions().disable();
    	http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new TftPasswordEncoder());
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}