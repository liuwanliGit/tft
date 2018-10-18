package com.tft.framework.common.service;

import java.io.File;
import java.util.List;

/**
 * <br>类描述：邮件接口
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/26 17:02
 *
 * @ClassName MailService
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public interface MailService {
    
    /**
    <br>功能描述:  发送简单邮件
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/26 17:02
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [to, subject, content]
     * @throws 
     * @return void
     * @see #
     */
    public void sendSimpleEmail(String to, String subject, String content)throws Exception;
    /**
    <br>功能描述:  发送带html格式的邮件
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/26 17:03
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [to, subject, content]
     * @throws 
     * @return void
     * @see #
     */
    public void sendHtmlEmail(String to, String subject, String content)throws Exception;
    /**
    <br>功能描述:  发送带附件的邮件
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/26 17:05
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [to, subject, content, rscPath, rscId]
     * @throws 
     * @return void
     * @see #
     */
    public void sendAttachmentEmail(String to, String subject, String content, List<File> fileList)throws Exception;
    /**
    <br>功能描述:  给用户发送验证码
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/27 9:29
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [to]
     * @throws 
     * @return void
     * @see #
     */
    public void sendCheckCode(String to)throws Exception;
}
