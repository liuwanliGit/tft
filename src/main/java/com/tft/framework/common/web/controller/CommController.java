package com.tft.framework.common.web.controller;

import com.tft.framework.common.service.MailService;
import com.tft.framework.common.util.CheckCodeUtil;
import com.tft.framework.common.util.HttpIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/25 16:12
 *
 * @ClassName CommController
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@RestController
@RequestMapping("/api/comm/*")
public class CommController {

    @Autowired
    private MailService mailService;

    /**
    <br>功能描述:  获取验证码
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/25 16:39
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [request, response]
     * @throws 
     * @return void
     * @see #
     */
    @RequestMapping("getCodeImg")
    public void doGetCodeImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpIO httpIO = new HttpIO(request, response);
        try {
            BufferedImage img = CheckCodeUtil.createCodeImg();
            httpIO.outImgBuffer(img,"jpeg",null);
        }catch (Exception e){
            e.printStackTrace();
            httpIO.setFailedMsg("获取验证码失败",e);
            httpIO.outAjaxDate();
        }
    }
    
    /**
    <br>功能描述:  通过邮件发送验证码
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/26 21:53
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [request, response]
     * @throws 
     * @return void
     * @see #
     */
    @RequestMapping("sendCodeByEmail")
    public void doSendCodeByEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpIO httpIO = new HttpIO(request, response);
        try {
            String to = httpIO.getValue("to");
            mailService.sendCheckCode(to);
            httpIO.setSuccessMsg("验证码发送成功");
        }catch (Exception e){
            e.printStackTrace();
            httpIO.setFailedMsg("获取验证码失败",e);
        }finally {
            httpIO.outAjaxDate();
        }
    }

    /**
    <br>功能描述:  核对验证码
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/25 16:53
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [request,  8response]
     * @throws 
     * @return void
     * @see #
     */
    @RequestMapping("checkCode")
    public void doCheckCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpIO httpIO = new HttpIO(request, response);
        try {
            String code = httpIO.getValue("code");
            httpIO.setKeyValue("checkResult",CheckCodeUtil.checkCode(code));
            httpIO.setResultTrue();
        }catch (Exception e){
            e.printStackTrace();
            httpIO.setFailedMsg("校验失败",e);
        }finally {
            httpIO.outAjaxDate();
        }
    }
}
