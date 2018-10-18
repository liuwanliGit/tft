package com.tft.framework.codeItem.bean;

import com.tft.framework.common.bean.BasicBean;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <br>类描述：代码项类别
 * <br>author： lwl liuwanli_eamil@163.com	2018/6/26 19:32
 *
 * @ClassName codeType
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Entity
@Table(name = "TFT_CODE_CODETYPE")
public class CodeType extends BasicBean {

    private String name;//类型名称

    private String code;//类型编码

    private String parentCode;//父类型编码

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
