package com.tft.framework.common.bean;

/**
 * 
 * <pre>职责: 消息提醒异常类
 * 使用方法：抛出该异常时不做事务回滚
 * </pre>
 * @see #
 * @author sunsn 2016-10-10 下午5:31:12
 * @since
 * <pre>修改记录（修改时间、修改人、修改内容、修改原因）</pre>
 */
public class MsgPromptException extends TftBaseRuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * 构造方法，指定自定义的异常处理类
	 * @param msg
	 * @param clazz
	 */
	public MsgPromptException(String msg, Class<?> clazz) {

		super(msg, clazz);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 构造方法，指定自定义的异常处理类
	 * @param msg
	 * @param cause
	 * @param clazz
	 */
	public MsgPromptException(String msg, Throwable cause, Class<?> clazz) {

		super(msg, cause, clazz);
		// TODO Auto-generated constructor stub
	}

	public MsgPromptException(String msg, Throwable cause) {

		super(msg, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 默认构造方法，必须提供异常信息
	 * @param msg
	 */
	public MsgPromptException(String msg) {

		super(msg);
		// TODO Auto-generated constructor stub
	}

	public MsgPromptException(Throwable cause, Class<?> clazz) {

		super(cause, clazz);
		// TODO Auto-generated constructor stub
	}

	public MsgPromptException(Throwable cause) {

		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
}
