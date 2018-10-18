package com.tft.framework.codeItem.service;

import com.tft.framework.codeItem.bean.CodeType;
import com.tft.framework.common.bean.TftSort;

import java.util.List;

/**
 * <br>类描述：代码项类别service层接口
 * <br>author： lwl liuwanli_eamil@163.com	2018/6/28 0:04
 *
 * @ClassName CodeItemRepositoryImpl
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public interface CodeTypeService {

    /**
     <br>功能描述:  按条件加载文件夹类型
     <br>处理逻辑:
     <br>作者: lwl liuwanli_eamil@163.com 2018/6/27 23:57
     <br>修改记录: {修改人 修改原因 修改时间}
     * @param [codeType]
     * @throws
     * @return java.util.List<com.tft.framework.codeItem.bean.CodeType>
     * @see #
     */
    List<CodeType> searchCodeType(CodeType codeType, TftSort tftSort);

    /**
     <br>功能描述:  新增或修改代码项类型
     <br>处理逻辑:
     <br>作者: lwl liuwanli_eamil@163.com 2018/6/27 23:59
     <br>修改记录: {修改人 修改原因 修改时间}
     * @param [codeType]
     * @throws
     * @return void
     * @see #
     */
    CodeType  createOrModifyCodeType(CodeType codeType);

    /**
     <br>功能描述:  移除代码类型
     <br>处理逻辑:
     <br>作者: lwl liuwanli_eamil@163.com 2018/6/28 0:04
     <br>修改记录: {修改人 修改原因 修改时间}
     * @param [codeType]
     * @throws
     * @return void
     * @see #
     */
    void removeCodeType(CodeType codeType);
}
