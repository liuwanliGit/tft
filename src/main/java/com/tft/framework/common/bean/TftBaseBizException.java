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
 * 职责: 业务异常基类，抛出该异常时必须提供异常信息
 * 使用方法：
 * </pre>
 * @see #
 * @author lihz180410 Apr 28, 2011 8:21:11 AM123123, Julian Yang, 3rd Jun, 2014
 * @since 2.3.2
 * <pre>修改记录（修改时间、修改人、修改内容、修改原因）</pre>
 */
public class TftBaseBizException extends TftBaseRuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 默认构造方法，必须提供异常信息
	 * @author Julian Yang, 3rd Jun, 2014
	 * @since 2.3.2
	 * @param msg
	 */
	public TftBaseBizException(String msg) {
		super(msg);
	}
	
	/**
	 * 构造方法，指定自定义的异常处理类
	 * @author Julian Yang, 3rd Jun, 2014
	 * @since 2.3.2
	 * @param msg
	 * @param clazz
	 */
	public TftBaseBizException(String msg, Class<?> clazz) {
		super(msg, clazz);
	}
	
	/**
	 * 构造方法
	 * @author Julian Yang, 3rd Jun, 2014
	 * @since 2.3.2
	 * @param cause
	 */
	public TftBaseBizException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * 构造方法，指定自定义的异常处理类
	 * @author Julian Yang, 3rd Jun, 2014
	 * @since 2.3.2
	 * @param cause
	 * @param clazz
	 */
	public TftBaseBizException(Throwable cause, Class<?> clazz) {
		super(cause, clazz);
	}

	/**
	 * 构造方法
	 * @author Julian Yang, 3rd Jun, 2014
	 * @since 2.3.2
	 * @param msg
	 * @param cause
	 */
	public TftBaseBizException(String msg, Throwable cause) {
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
	public TftBaseBizException(String msg, Throwable cause, Class<?> clazz) {
		super(msg, cause, clazz);
	}
}
