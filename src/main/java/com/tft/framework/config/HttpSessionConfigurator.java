package com.tft.framework.config;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/9/16 12:37
 *
 * @ClassName HttpSessionConfigurator
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public class HttpSessionConfigurator extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
        super.modifyHandshake(sec, request, response);
    }
}
