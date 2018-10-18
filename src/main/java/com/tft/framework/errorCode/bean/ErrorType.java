package com.tft.framework.errorCode.bean;

/**
 * <br>类描述：错误类别
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/26 15:11
 *
 * @ClassName ErrorType
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public class ErrorType {
    private String name; //类别名称

    private String code;//类别编码

    private String parentCode;//父类别编码

    private String remark;//备注

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
