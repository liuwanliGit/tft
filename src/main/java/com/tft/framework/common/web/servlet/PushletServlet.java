package com.tft.framework.common.web.servlet;

import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.servlet.Pushlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/8/23 14:42
 *
 * @ClassName PushletServlet
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@WebServlet(urlPatterns = "/pushlet.srv")
public class PushletServlet extends Pushlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    /**
     * Servlet GET request: handles event requests.
     *
     * @param request
     * @param response
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    /**
     * Servlet POST request: extracts event data from body.
     *
     * @param request
     * @param response
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    /**
     * Generic request handler (GET+POST).
     *
     * @param anEvent
     * @param request
     * @param response
     */
    @Override
    protected void doRequest(Event anEvent, HttpServletRequest request, HttpServletResponse response) {
        super.doRequest(anEvent, request, response);
    }
}
