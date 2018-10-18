/******************************************************************
 *  计算机应用研究所软件中心                                          *
 *                                                                *
 *  Copyright (c) 2009 计算机应用软件中心.                           *
 *                                                                *
 *  All rights reserved. No part of this program or publication   *
 *  may be reproduced, transmitted, transcribed, stored in a      *
 *  retrieval system,or translated into any language or computer  *
 *  language, in any form or by any means, electronic, mechanical,*
 *  magnetic, optical, chemical,biological, or otherwise, without *
 *  the prior written permission.                                 *
 *                                                                *
*******************************************************************/

package com.tft.framework.common.bean;

/**
 * <pre>
 * 职责: 框架运行时异常基类，抛出该异常时必须提供异常信息
 * 使用方法：
 * </pre>
 * @see #
 * @author lihz180410 Apr 26, 2011 2:55:35 PM123123, Julian Yang, 3rd Jun, 2014
 * @since 2.3.2
 * <pre>修改记录（修改时间、修改人、修改内容、修改原因）</pre>
 */
public class TftBaseRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	protected Class<?> clazz; // 自定义的异常处理类
	
	public Class<?> getClazz() {
		return clazz;
	}
	
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	/**
	 * 默认构造方法，必须提供异常信息
	 * @author Julian Yang, 3rd Jun, 2014
	 * @since 2.3.2
	 * @param msg
	 */
	public TftBaseRuntimeException(String msg) {
		super(msg);
	}
	
	/**
	 * 构造方法，指定自定义的异常处理类
	 * @author Julian Yang, 3rd Jun, 2014
	 * @since 2.3.2
	 * @param msg
	 * @param clazz
	 */
	public TftBaseRuntimeException(String msg, Class<?> clazz) {
		super(msg);
		this.clazz = clazz;
	}
	
	/**
	 * 构造方法
	 * @author Julian Yang, 3rd Jun, 2014
	 * @since 2.3.2
	 * @param cause
	 */
	public TftBaseRuntimeException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * 构造方法，指定自定义的异常处理类
	 * @author Julian Yang, 3rd Jun, 2014
	 * @since 2.3.2
	 * @param cause
	 * @param clazz
	 */
	public TftBaseRuntimeException(Throwable cause, Class<?> clazz) {
		super(cause);
		this.clazz = clazz;
	}
	
	/**
	 * 构造方法
	 * @author Julian Yang, 3rd Jun, 2014
	 * @since 2.3.2
	 * @param msg
	 * @param cause
	 */
	public TftBaseRuntimeException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	/**
	 * 构造方法，指定自定义的异常处理类
	 * @author Julian Yang, 3rd Jun, 2014
	 * @since 2.3.2
	 * @param msg
	 * @param cause
	 * @param clazz
	 */
	public TftBaseRuntimeException(String msg, Throwable cause, Class<?> clazz) {
		super(msg, cause);
		this.clazz = clazz;
	}
	
	
	//TODO 以下是没有意义的封装，后续版本中将逐步移除
	//private Throwable cause;

//	public Throwable getCause() {
//		return getCause();
//	}

//	public String getMessage() {
//		return super.getMessage();
//	}

//	public void printStackTrace(PrintStream ps) {
//		if (getCause() == null) {
//			super.printStackTrace(ps);
//		}
//		else {
//			ps.println(this);
//			ps.print("Caused by: ");
//			getCause().printStackTrace(ps);
//		}
//	}
//
//	public void printStackTrace(PrintWriter pw) {
//		if (getCause() == null) {
//			super.printStackTrace(pw);
//		}
//		else {
//			pw.println(this);
//			pw.print("Caused by: ");
//			getCause().printStackTrace(pw);
//		}
//	}

	public Throwable getRootCause() {
		Throwable cause = getCause();
		if (cause instanceof TftBaseRuntimeException) {
			return ((TftBaseRuntimeException) cause).getRootCause();
		}
		else {
			return cause;
		}
	}

	public Throwable getMostSpecificCause() {
		Throwable rootCause = getRootCause();
		return (rootCause != null ? rootCause : this);
	}

	public boolean contains(Class<?> exType) {
		if (exType == null) {
			return false;
		}
		if (exType.isInstance(this)) {
			return true;
		}
		Throwable cause = getCause();
		if (cause instanceof TftBaseRuntimeException) {
			return ((TftBaseRuntimeException) cause).contains(exType);
		}
		else {
			return (cause != null && exType.isInstance(cause));
		}
	}
}
