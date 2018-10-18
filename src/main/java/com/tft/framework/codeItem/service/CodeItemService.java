package com.tft.framework.codeItem.service;

import com.tft.framework.codeItem.bean.CodeItem;
import com.tft.framework.common.bean.TftPage;
import com.tft.framework.common.bean.TftSort;

import java.util.List;

/**
 * <br>类描述：代码项service层接口
 * <br>author： lwl liuwanli_eamil@163.com	2018/6/28 0:04
 *
 * @ClassName CodeItemRepositoryImpl
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public interface CodeItemService {
    /**
    <br>功能描述:  按条件查询代码项
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/6/27 23:25
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [codeItem]
     * @throws 
     * @return java.util.List<com.tft.framework.codeItem.bean.CodeItem>
     * @see #
     */
    TftPage searchCodeItem(CodeItem codeItem, TftPage page,TftSort tftSort);

    /**
    <br>功能描述:  新增或修改代码项
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/6/28 0:00
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [codeItem]
     * @throws 
     * @return void
     * @see #
     */
    CodeItem createOrModifyCodeItem(CodeItem codeItem);

    /**
    <br>功能描述:  删除代码项
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/6/28 0:01
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [codeItems]
     * @throws 
     * @return void
     * @see #
     */
    void removeCodeItems(List<CodeItem> codeItems);
}
