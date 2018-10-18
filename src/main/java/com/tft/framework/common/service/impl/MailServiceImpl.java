package com.tft.framework.common.service.impl;

import com.tft.framework.common.bean.TftBaseBizException;
import com.tft.framework.common.service.MailService;
import com.tft.framework.common.util.CheckCodeUtil;
import com.tft.framework.common.util.TftWebContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.util.List;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/26 17:10
 *
 * @ClassName MailServiceImpl
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Service("mailService")
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;//spring 提供的邮件发送类

    @Value("${spring.mail.username}")
    private String from;
    @Value("${spring.mail.fromName}")
    private String fromName;

    /**
     * <br>功能描述:  发送简单邮件
     * <br>处理逻辑:
     * <br>作者: lwl liuwanli_eamil@163.com 2018/7/26 17:02
     * <br>修改记录: {修改人 修改原因 修改时间}
     *
     * @param to
     * @param subject
     * @param content
     * @return void
     * @throws
     * @see #
     */
    @Override
    public void sendSimpleEmail(String to, String subject, String content) throws Exception{
        SimpleMailMessage message = new SimpleMailMessage();//创建简单邮件消息
        message.setFrom(from);//设置发送人
        message.setTo(to);//设置收件人
        message.setSubject(subject);//设置主题
        message.setText(content);//设置内容
        try {
            mailSender.send(message);//执行发送邮件
        } catch (Exception e) {
            throw new TftBaseBizException("邮件发送异常！");
        }
    }

    /**
     * <br>功能描述:  发送带html格式的邮件
     * <br>处理逻辑:
     * <br>作者: lwl liuwanli_eamil@163.com 2018/7/26 17:03
     * <br>修改记录: {修改人 修改原因 修改时间}
     *
     * @param to
     * @param subject
     * @param content
     * @return void
     * @throws
     * @see #
     */
    @Override
    public void sendHtmlEmail(String to, String subject, String content)throws Exception {
        MimeMessage message = mailSender.createMimeMessage();//创建一个MINE消息
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress(fromName+" <"+from+">"));
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new TftBaseBizException("邮件发送异常！");
        }

    }

    /**
     * <br>功能描述:  发送带附件的邮件
     * <br>处理逻辑:
     * <br>作者: lwl liuwanli_eamil@163.com 2018/7/26 17:05
     * <br>修改记录: {修改人 修改原因 修改时间}
     *
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     * @return void
     * @throws
     * @see #
     */
    @Override
    public void sendAttachmentEmail(String to, String subject, String content,  List<File> fileList)throws Exception {
        MimeMessage message = mailSender.createMimeMessage();//创建一个MINE消息
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);// true表示这个邮件是有附件的

            for(File file:fileList){
                helper.addAttachment(MimeUtility.encodeWord(file.getName()), file);//添加附件
            }
            mailSender.send(message);
        } catch (Exception e) {
            throw new TftBaseBizException("邮件发送异常！");
        }
    }

    /**
     * <br>功能描述:  给用户发送验证码
     * <br>处理逻辑:
     * <br>作者: lwl liuwanli_eamil@163.com 2018/7/26 21:57
     * <br>修改记录: {修改人 修改原因 修改时间}
     *
     * @param to@throws
     * @return void
     * @see #
     */
    @Override
    public void sendCheckCode(String to) throws Exception {
        String code = CheckCodeUtil.createCodeChar();
        String subject = "tft验证码通知";
        String content = "您的验证码是【<span style='color:#007DDB; font-weight:100'>"+code+"</span>】";
        TftWebContext.putValue("checkCode",code);
        this.sendHtmlEmail(to,subject,content);
    }
}
