package com.tft.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/8/23 12:38
 *
 * @ClassName WebSocketConfig
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Configuration
public class WebSocketConfig{
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
