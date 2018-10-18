package com.tft.framework.common.Filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <br>类描述：自定义过滤器
 * <br>author： lwl liuwanli_eamil@163.com	2018/10/15 22:06
 *
 * @ClassName MyFilter
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Component("myFilter")
public class MyFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("【myFilter】:*****************************");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
