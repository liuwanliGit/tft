package com.tft.framework.config;

import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/9/16 12:41
 *
 * @ClassName RequestListener
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Component
public class RequestListener implements ServletRequestListener{
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
    }
    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        //将所有request请求都携带上httpSession
        ((HttpServletRequest) servletRequestEvent.getServletRequest()).getSession();
    }
}
