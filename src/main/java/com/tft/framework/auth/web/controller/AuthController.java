package com.tft.framework.auth.web.controller;

import com.tft.framework.auth.service.AuthService;
import com.tft.framework.common.util.HttpIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/10/11 23:57
 *
 * @ClassName AuthController
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@RestController
@RequestMapping("/api/auth/*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping("loadActions")
    public void loadAllMenuRes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpIO httpIO = new HttpIO(request,response);
        try {
            Map<String,String> map = authService.loadUrlAuthInfo();
            httpIO.setSuccessMsg("加载成功");
            httpIO.setObject(map);
        } catch (Exception e) {
            e.printStackTrace();
            httpIO.setFailedMsg("加载失败",e);
        } finally {
            httpIO.outAjaxDate();
        }
    }

    @RequestMapping("reLoadActions")
    public void reLoadAllMenuRes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpIO httpIO = new HttpIO(request,response);
        try {
            Map<String,String> map = authService.reLoadUrlAuthInfo();
            httpIO.setSuccessMsg("加载成功");
            httpIO.setObject(map);
        } catch (Exception e) {
            e.printStackTrace();
            httpIO.setFailedMsg("加载失败",e);
        } finally {
            httpIO.outAjaxDate();
        }
    }
}
