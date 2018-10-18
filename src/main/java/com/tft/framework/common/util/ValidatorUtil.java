package com.tft.framework.common.util;

import org.apache.poi.util.StringUtil;
import org.hibernate.validator.internal.util.StringHelper;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;



/**
 * <pre>职责: 对象验证工具类
 * 使用方法：
 * </pre>
 * @see #
 * @author tianay  2016-10-10 上午11:47:44
 * @since
 * <pre>修改记录（修改时间、修改人、修改内容、修改原因）</pre>
 */
public class ValidatorUtil {
	private static ValidatorFactory validatorFactory;
	
	static{
		validatorFactory=Validation.buildDefaultValidatorFactory();
	}
	/**
	 * <pre>功能描述: 验证对象
	 * 使用方法：
	 * 使用约束：
	 * 逻辑：返回Map<字段,错误信息>
	 * </pre>
	 * @param validator
	 * @param object
	 * @return		返回Map<字段,错误信息>
	 * @see #
	 * @since
	 * <pre>create by tianay 2016-10-10 上午11:51:14
	 * 修改记录（修改时间、修改人、修改内容、修改原因）
	 * </pre>
	 */
	public static Map<String,String> validateObjectToMap(Object object){
		Validator validator=validatorFactory.getValidator();
		Set<ConstraintViolation<Object>> constraintViolations=validator.validate(object);
		
		Map<String,String> errors=new HashMap<String, String>();
		
		for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
			errors.put(constraintViolation.getPropertyPath().toString(),constraintViolation.getMessage());
		}
		return errors;
	}
	
	/**
	 * <pre>功能描述: 验证对象
	 * 使用方法：
	 * 使用约束：
	 * 逻辑：返回错误信息拼接的字符串
	 * </pre>
	 * @param validator
	 * @param object
	 * @return 错误信息拼接的字符串
	 * @see #
	 * @since
	 * <pre>create by tianay 2016-10-10 上午11:52:19
	 * 修改记录（修改时间、修改人、修改内容、修改原因）
	 * </pre>
	 */
	public static String validateObjectToStr(Object object){
		Validator validator=validatorFactory.getValidator();
		
		Set<ConstraintViolation<Object>> constraintViolations=validator.validate(object);
		
		StringBuffer sb=new StringBuffer();
		
		for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
			sb.append(constraintViolation.getMessage()).append(";");
		}
		
		if(sb.length()>0){
			return sb.substring(0,sb.length()-1)+".";
		}
		return null;
	}
}
