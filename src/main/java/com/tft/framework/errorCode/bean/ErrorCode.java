package com.tft.framework.errorCode.bean;

/**
 * <br>类描述：错误码
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/26 15:15
 *
 * @ClassName ErrorCode
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public class ErrorCode {

    private String errorCode;//错误编码

    private String describe;//描述

    private String typeCode;//类别编码

    private String solution;//解决方案

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
