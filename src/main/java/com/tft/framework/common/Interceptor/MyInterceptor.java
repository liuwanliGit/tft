package com.tft.framework.common.Interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/10/15 21:28
 *
 * @ClassName MyInterceptor
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Component("myInterceptor")
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("【MyInterceptor】:****************preHandle***************");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("【MyInterceptor】:****************postHandle***************");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("【MyInterceptor】:****************afterCompletion***************");
    }
}
