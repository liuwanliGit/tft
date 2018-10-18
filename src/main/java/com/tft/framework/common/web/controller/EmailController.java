package com.tft.framework.common.web.controller;

import com.tft.framework.common.service.MailService;
import com.tft.framework.common.util.HttpIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/26 17:25
 *
 * @ClassName EmailController
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@RestController
@RequestMapping("/api/mail/*")
public class EmailController {

    @Autowired
    private MailService mailService;

    @RequestMapping("sendMail")
    public void doSendMail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpIO httpIO = new HttpIO(request, response);
        try {
            String to = httpIO.getValue("to");
            String subject = httpIO.getValue("subject");
            String content = httpIO.getValue("content");
            mailService.sendHtmlEmail(to,subject,content);
            httpIO.setSuccessMsg("邮件发送成功");
        }catch (Exception e){
            e.printStackTrace();
            httpIO.setFailedMsg("邮件发送失败",e);
            httpIO.outAjaxDate();
        }
    }
}
