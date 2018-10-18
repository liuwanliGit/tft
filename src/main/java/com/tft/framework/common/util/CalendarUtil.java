package com.tft.framework.common.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * <pre>职责: 对日历的操作	【注】每周开始时间为星期一
 * 使用方法：
 * </pre>
 * @see http://blog.csdn.net/enterys/article/details/49911609
 * @author lwl 2016-11-21 下午3:11:20
 * @since
 * <pre>修改记录（修改时间、修改人、修改内容、修改原因）</pre>
 */
public class CalendarUtil {
	/**
	 * 
	 * <pre>功能描述: 获取某月第一天的日期
	 * 使用方法：
	 * 使用约束：
	 * 逻辑：
	 * </pre>
	 * @param year
	 * @param month
	 * @return
	 * @throws ParseException
	 * @see #
	 * @since
	 * <pre>create liuwa 2016-11-21 下午3:17:10
	 * 修改记录（修改时间、修改人、修改内容、修改原因）
	 * </pre>
	 */
	public static Date getFirstDayOfMonth(int year,int month) throws ParseException{  
		String monthStr = month < 10 ? "0" + month : String.valueOf(month);  
		return DateUtil.stringOfyMdToDate(year+"-"+monthStr+"-01");
	}  
	/**
	 * 
	 * <pre>功能描述: 获取某月最后一天的日期
	 * 使用方法：
	 * 使用约束：
	 * 逻辑：
	 * </pre>
	 * @param year
	 * @param month
	 * @return
	 * @see #
	 * @since
	 * <pre>create liuwa 2016-11-21 下午3:17:34
	 * 修改记录（修改时间、修改人、修改内容、修改原因）
	 * </pre>
	 */
	public static Date getLastDayOfMonth(int year,int month){  
		Calendar calendar = Calendar.getInstance();  
		calendar.set(Calendar.YEAR , year);  
		
		calendar.set(Calendar.MONTH , month - 1);  
		calendar.set(Calendar.DATE , 1);  
		calendar.add(Calendar.MONTH, 1);  
		calendar.add(Calendar.DAY_OF_YEAR , -1);  
		return  calendar.getTime();
	}  
	/**
	 * 
	 * <pre>功能描述: 获取某年的日历
	 * 使用方法：
	 * 使用约束：
	 * 逻辑：
	 * </pre>
	 * @param year
	 * @return
	 * @see #
	 * @since
	 * <pre>create liuwa 2016-11-21 下午3:17:51
	 * 修改记录（修改时间、修改人、修改内容、修改原因）
	 * </pre>
	 */
	private static Calendar getCalendarFormYear(int year){  
		Calendar cal = Calendar.getInstance();  
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);        
		cal.set(Calendar.YEAR, year);  
		return cal;  
	}  
	/**
	 * 
	 * <pre>功能描述:获取某年第几周的开始日期 
	 * 使用方法：
	 * 使用约束：
	 * 逻辑：
	 * </pre>
	 * @param year
	 * @param weekNo
	 * @return
	 * @see #
	 * @since
	 * <pre>create liuwa 2016-11-21 下午3:18:21
	 * 修改记录（修改时间、修改人、修改内容、修改原因）
	 * </pre>
	 */
	public static Date getStartDayOfWeekNo(int year,int weekNo){  
		Calendar cal = getCalendarFormYear(year);  
		cal.set(Calendar.WEEK_OF_YEAR, weekNo);  
		return cal.getTime();
	}  
	/**
	 * 
	 * <pre>功能描述: 获取某年第几周的结束日期
	 * 使用方法：
	 * 使用约束：
	 * 逻辑：
	 * </pre>
	 * @param year
	 * @param weekNo
	 * @return
	 * @see #
	 * @since
	 * <pre>create liuwa 2016-11-21 下午3:18:52
	 * 修改记录（修改时间、修改人、修改内容、修改原因）
	 * </pre>
	 */
	public static Date getEndDayOfWeekNo(int year,int weekNo){  
		Calendar cal = getCalendarFormYear(year);  
		cal.set(Calendar.WEEK_OF_YEAR, weekNo);  
		cal.add(Calendar.DAY_OF_WEEK, 6); 
		return cal.getTime();
	}  
	/**
	 * 
	 * <pre>功能描述: 获取该日期是星期几    星期日 0 ；星期一 1；……；星期六 6
	 * 使用方法：
	 * 使用约束：
	 * 逻辑：
	 * </pre>
	 * @param date
	 * @return
	 * @see #
	 * @since
	 * <pre>create liuwa 2016-11-21 下午3:52:03
	 * 修改记录（修改时间、修改人、修改内容、修改原因）
	 * </pre>
	 */
	public static int getWeekIndex(Date date){
		 Calendar cal = Calendar.getInstance(); 
		 cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);        
		 cal.setTime(date);  
		 return cal.get(Calendar.DAY_OF_WEEK) - 1;  
	}
	/**
	 * 
	 * <pre>功能描述: 获取星期几 返回汉字（日、一、二、……）
	 * 使用方法：
	 * 使用约束：
	 * 逻辑：
	 * </pre>
	 * @param date
	 * @return
	 * @see #
	 * @since
	 * <pre>create liuwl 2016-11-28 上午11:35:14
	 * 修改记录（修改时间、修改人、修改内容、修改原因）
	 * </pre>
	 */
	public static String getWeekIndexString(Date date){
		int index = CalendarUtil.getWeekIndex(date);
		switch (index) {
			case 0:
				return "日";
			case 1:
				return "一";
			case 2:
				return "二";
			case 3:
				return "三";
			case 4:
				return "四";
			case 5:
				return "五";
			case 6:
				return "六";
			default:
				break;
		}
		return null;
	}
	/**
	 * 
	 * <pre>功能描述: 获取该日期是当年的第几周
	 * 使用方法：
	 * 使用约束：
	 * 逻辑：
	 * </pre>
	 * @param date
	 * @return
	 * @see #
	 * @since
	 * <pre>create liuwa 2016-11-22 上午10:41:43
	 * 修改记录（修改时间、修改人、修改内容、修改原因）
	 * </pre>
	 */
	public static int getWeekNum(Date date){
		Calendar cal= new GregorianCalendar();
		
		//edit by tianay 2016-12-28 上午10:42:46 for:该方法是将日期设置为指定周的某一天，这里是获取周数
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		//edit end
		
		cal.setTime(date);  
		int num = cal.get(Calendar.WEEK_OF_YEAR);
	    return num; 
	}
	
	public static int getYear(Date date){
		Calendar cal= new GregorianCalendar();
		cal.setTime(date);  
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		int month = cal.get(Calendar.MONTH);
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		//如果月份不是第一月，但周确实第一周，日历当然是第二年的第一周了，所以年需要加1
		if(month>1 && week == 1){
			return cal.get(Calendar.YEAR)+1;
		}
		return cal.get(Calendar.YEAR);
	}
	/**
	 * 
	 * <pre>功能描述: 获取一天的最小时间
	 * 使用方法：
	 * 使用约束：
	 * 逻辑：
	 * </pre>
	 * @param day
	 * @return
	 * @throws ParseException
	 * @see #
	 * @since
	 * <pre>create liuwl 2016-12-27 下午2:25:41
	 * 修改记录（修改时间、修改人、修改内容、修改原因）
	 * </pre>
	 */
	public static Date getMinTimeOfDay(Date day) throws ParseException{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(day);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	/**
	 * 
	 * <pre>功能描述: 获取一天的最大时间
	 * 使用方法：
	 * 使用约束：
	 * 逻辑：
	 * </pre>
	 * @param day
	 * @return
	 * @throws ParseException
	 * @see #
	 * @since
	 * <pre>create liuwl 2016-12-27 下午2:42:06
	 * 修改记录（修改时间、修改人、修改内容、修改原因）
	 * </pre>
	 */
	public static Date getMaxTimeOfDay(Date day) throws ParseException{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(day);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}
}
