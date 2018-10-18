package com.tft.framework.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>职责: json与bean转换工具
 * 使用方法：
 * </pre>
 * @see #
 * @author ccc 2016-10-11 上午11:04:18
 * @since
 * <pre>修改记录（修改时间、修改人、修改内容、修改原因）</pre>
 */
public class JsonUtil {

	/**
	 * 
	 * <pre>功能描述: 将json字符串转换为对象
	 * 使用方法：
	 * 使用约束：
	 * 逻辑：
	 * </pre>
	 * @param jsonBean
	 * @param clazz
	 * @return
	 * @see #
	 * @since
	 * <pre>create ccc 2016-10-11 下午2:32:48
	 * 修改记录（修改时间、修改人、修改内容、修改原因）
	 * </pre>
	 */
	public static <T> T jsonToBean(String jsonBean,Class<T> clazz){
		if(null == jsonBean || jsonBean.isEmpty()) return null;
		T t = JSON.parseObject(jsonBean, clazz);
		return t;
	}
	
	/**
	 * 
	 * <pre>功能描述: 将json字符串转换为对象集合
	 * 使用方法：
	 * 使用约束：
	 * 逻辑：
	 * </pre>
	 * @param jsonList
	 * @param clazz
	 * @return
	 * @see #
	 * @since
	 * <pre>create ccc 2016-10-11 下午2:34:02
	 * 修改记录（修改时间、修改人、修改内容、修改原因）
	 * </pre>
	 */
	public static <T> List<T> jsonToBeans(String jsonList, Class<T> clazz){
		if(null == jsonList || jsonList.isEmpty()) return null;
		List<T> result = JSONArray.parseArray(jsonList, clazz);
		return result;
	}
	
	/**
	 * 
	 * <pre>功能描述: 将对象转换为json字符串
	 * 使用方法：
	 * 使用约束：默认日期格式 yyyy-MM-dd HH:mm:ss
	 * 逻辑：
	 * </pre>
	 * @param obj
	 * @return
	 * @see #
	 * @since
	 * <pre>create ccc 2016-10-11 下午2:34:29
	 * 修改记录（修改时间、修改人、修改内容、修改原因）
	 * </pre>
	 */
	public static String objToJson(Object obj){
		if(null == obj) return null;
		return objToJson(obj,DateFormat.DATE_TIME);
	}
	
	/**
	 * 
	 * <pre>功能描述: 将对象转换为json字符串
	 * 使用方法：
	 * 使用约束：
	 * 逻辑：
	 * </pre>
	 * @param obj
	 * @param formate JsonUtil.DateFormat
	 * @return
	 * @see #
	 * @since
	 * <pre>create ccc 2016-10-11 下午2:35:10
	 * 修改记录（修改时间、修改人、修改内容、修改原因）
	 * </pre>
	 */
	public static String objToJson(Object obj,DateFormat formate){
		if(null == obj) return null;
		if(null == formate) formate = DateFormat.DATE_TIME;
		return objToJson(obj, formate.getFormat());
	}
	
	/**
	 * 
	 * <pre>功能描述: 将对象转换为json字符串
	 * 使用方法：
	 * 使用约束：自定义日期格式
	 * 逻辑：
	 * </pre>
	 * @param obj
	 * @param dateFormat
	 * @return
	 * @see #
	 * @since
	 * <pre>create ccc 2016-10-11 下午2:36:10
	 * 修改记录（修改时间、修改人、修改内容、修改原因）
	 * </pre>
	 */
	public static String objToJson(Object obj,String dateFormat){
		if(null == obj) return null;
		SerializeConfig mapping = new SerializeConfig();
		mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
		mapping.put(Timestamp.class, new SimpleDateFormatSerializer(dateFormat));
		mapping.put(java.sql.Date.class, new SimpleDateFormatSerializer(dateFormat));
		return JSON.toJSONString(obj,mapping);
	}
	
	/**
	 * 
	 * <pre>功能描述: 将json转换为map<String,String>
	 * 使用方法：
	 * 使用约束：
	 * 逻辑：
	 * </pre>
	 * @param json
	 * @return
	 * @see #
	 * @since
	 * <pre>create ccc 2016-10-11 下午2:57:09
	 * 修改记录（修改时间、修改人、修改内容、修改原因）
	 * </pre>
	 */
	public static Map<String, String> jsonToMap(String json){
		if(null == json || json.isEmpty()) return null;
		return JSON.parseObject(json, new TypeReference<Map<String,String>>(){});
	}

	/**
	 * 
	 * <pre>职责: 日期格式
	 * 使用方法：
	 * </pre>
	 * @see #
	 * @author ccc 2016-10-11 下午2:24:24
	 * @since
	 * <pre>修改记录（修改时间、修改人、修改内容、修改原因）</pre>
	 */
	 public enum DateFormat{
		/** yyyy-MM-dd */
		DATE("yyyy-MM-dd"),
		/** yyyy-MM-dd HH:mm:ss */
		DATE_TIME("yyyy-MM-dd HH:mm:ss"),
		/** yyyy-MM-dd HH:mm:ss SSS */
		DATE_TIMEALL("yyyy-MM-dd HH:mm:ss SSS"),
		/** EEE MMM d HH:mm:ss 'CST' yyyy */
		EST_DATEFORMATE("EEE MMM d HH:mm:ss 'CST' yyyy");
		
		private String format ;
		
		private DateFormat(String formate){
			this.format = formate;
		}
		public String getFormat(){
			return format;
		}
	}
	 
}
