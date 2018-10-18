package com.tft.framework.codeItem.service.impl;

import com.tft.framework.codeItem.bean.CodeItem;
import com.tft.framework.codeItem.dao.CodeItemRepository;
import com.tft.framework.codeItem.service.CodeItemService;
import com.tft.framework.common.bean.TftPage;
import com.tft.framework.common.bean.TftSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/6/26 20:10
 *
 * @ClassName CodeItemServiceImpl
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Service("codeItemService")
public class CodeItemServiceImpl implements CodeItemService {

    @Autowired
    private CodeItemRepository codeItemRepository;

    /**
     * <br>功能描述:  按条件查询代码项
     * <br>处理逻辑:
     * <br>作者: lwl liuwanli_eamil@163.com 2018/6/27 23:25
     * <br>修改记录: {修改人 修改原因 修改时间}
     *
     * @param codeItem@throws
     * @return java.util.List<com.tft.framework.codeItem.bean.CodeItem>
     * @see #
     */
    @Override
    public TftPage searchCodeItem(CodeItem codeItem, TftPage page, TftSort sort) {
        if(page == null){
            List<CodeItem> codeItems = codeItemRepository.findAll(codeItemRepository.createQuery(codeItem));
            page = new TftPage(codeItems);
        }else{
            Page<CodeItem> codeItemPage = codeItemRepository.findAll(codeItemRepository.createQuery(codeItem),page.toQueryPage(sort));
            page.setPageInfo(codeItemPage);
        }
        return page;
    }


    /**
     * <br>功能描述:  新增或修改代码项
     * <br>处理逻辑:
     * <br>作者: lwl liuwanli_eamil@163.com 2018/6/28 0:00
     * <br>修改记录: {修改人 修改原因 修改时间}
     *
     * @param codeItem@throws
     * @return void
     * @see #
     */
    @Override
    public CodeItem createOrModifyCodeItem(CodeItem codeItem) {
        codeItemRepository.save(codeItem);
        return codeItem;
    }

    /**
     * <br>功能描述:  删除代码项
     * <br>处理逻辑:
     * <br>作者: lwl liuwanli_eamil@163.com 2018/6/28 0:01
     * <br>修改记录: {修改人 修改原因 修改时间}
     *
     * @param codeItems@throws
     * @return void
     * @see #
     */
    @Override
    public void removeCodeItems(List<CodeItem> codeItems) {
        codeItemRepository.deleteAll(codeItems);
    }

}
