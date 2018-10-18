package com.tft.framework.common.util;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * <br>类描述：日期工具类
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/31 15:14
 *
 * @ClassName DateUtil
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public class DateUtil {

    /**
     * String格式与Date格式的数据转换。
     */
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    /**
    <br>功能描述:  将yyyy-MM-dd HH:mm:ss或yyyy-MM-ddTHH:mm:ss格式的时间字符串转换成Date
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/31 15:23
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [textDate]
     * @throws 
     * @return java.util.Date
     * @see #
     */
    public static Date stringToDate(String textDate) throws ParseException {
        if (textDate != null && !"".equals(textDate)) {
            // 如果日期形式为1991-01-01T00:00:00，则先通用去掉"T"
            if (textDate.indexOf("T") > -1) {
                textDate = textDate.replaceAll("T", " ");
            }
            DateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT);
            return dateFormat.parse(textDate);
        }
        else {
            return null;
        }
    }
    /**
    <br>功能描述:  将日期格式如"1991-01-01"的String类型的日期转换成Date类型。
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/31 15:24
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [textDate]
     * @throws 
     * @return java.util.Date
     * @see #
     */
    public static Date stringOfyMdToDate(String textDate) throws ParseException {

        if (textDate != null && !"".equals(textDate)) {
            DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            return dateFormat.parse(textDate);
        }
        else {
            return null;
        }
    }
    /**
    <br>功能描述:  将Date类型的日期格式转换如"1991-01-01 00:00:00"的String类型。
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/31 15:25
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [dateValue]
     * @throws 
     * @return java.lang.String
     * @see #
     */
    public static String dateToString(Date dateValue) {

        if (dateValue != null && !"".equals(dateValue)) {
            DateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT);
            return dateFormat.format(dateValue);
        }
        else {
            return null;
        }
    }
    /**
    <br>功能描述:  将Date类型的日期格式转换如"1991-01-01"的String类型。
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/31 15:26
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [dateValue]
     * @throws 
     * @return java.lang.String
     * @see #
     */
    public static String dateToStringOfyMd(Date dateValue) {

        if (dateValue != null && !"".equals(dateValue)) {
            DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            return dateFormat.format(dateValue);
        }
        else {
            return null;
        }
    }
}
