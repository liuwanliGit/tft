package com.tft.framework.config;

import com.tft.framework.common.Filter.MyFilter;
import com.tft.framework.common.Interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {


  @Value("${CorsMap.allowedOrigins}")
  private String origins;
  @Value("${CorsMap.url}")
  private String urls;

  @Autowired
  @Qualifier("myInterceptor")
  private MyInterceptor myInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    //测试拦截器
    registry.addInterceptor(myInterceptor).addPathPatterns("/api/menuRes/loadAllMenuRes");
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping(urls)
//            .allowedMethods("GET, POST, PUT, DELETE")
            .allowedOrigins(origins)
            .allowCredentials(true);
  }

  //websocket在建立连接时将HTTPSession放到websocket的session中
  @Autowired
  @Qualifier("requestListener")
  private RequestListener requestListener;

  @Bean
  public ServletListenerRegistrationBean<RequestListener> servletListenerRegistrationBean() {
    ServletListenerRegistrationBean<RequestListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
    servletListenerRegistrationBean.setListener(requestListener);
    return servletListenerRegistrationBean;
  }
  @Bean
  public FilterRegistrationBean filterRegistrationBean(){
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    filterRegistrationBean.setFilter(new MyFilter());
    //测试过滤器
    filterRegistrationBean.addUrlPatterns("/api/menuRes/loadAllMenuRes");
    filterRegistrationBean.addInitParameter("token", "123");//添加默认参数
    filterRegistrationBean.setName("MyFilter");//设置优先级
    filterRegistrationBean.setOrder(1);//设置优先级
    return filterRegistrationBean;
  }

}