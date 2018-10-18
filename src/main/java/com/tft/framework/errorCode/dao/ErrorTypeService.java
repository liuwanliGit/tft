package com.tft.framework.errorCode.dao;

import com.tft.framework.errorCode.bean.ErrorType;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/26 15:46
 *
 * @ClassName ErrorTypeService
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public interface ErrorTypeService {

    /**
    <br>功能描述:  新增或修改一个错误码类别
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/26 15:57
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [errorType]
     * @throws 
     * @return com.tft.framework.errorCode.bean.ErrorType
     * @see #
     */
    public ErrorType createOrModifyErrorType(ErrorType errorType);

    /**
    <br>功能描述:
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/26 16:25
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [errorType]
     * @throws 
     * @return void
     * @see #
     */
    public void removeErrorType(ErrorType errorType);
}
