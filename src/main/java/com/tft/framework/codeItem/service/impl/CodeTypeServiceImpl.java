package com.tft.framework.codeItem.service.impl;

import com.tft.framework.codeItem.bean.CodeType;
import com.tft.framework.codeItem.dao.CodeTypeRepository;
import com.tft.framework.codeItem.service.CodeTypeService;
import com.tft.framework.common.bean.TftSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/7 0:18
 *
 * @ClassName CodeTypeServiceImpl
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Service("codeTypeService")
public class CodeTypeServiceImpl implements CodeTypeService {
    @Autowired
    private CodeTypeRepository codeTypeRepository;

    /**
     * <br>功能描述:  按条件加载文件夹类型
     * <br>处理逻辑:
     * <br>作者: lwl liuwanli_eamil@163.com 2018/6/27 23:57
     * <br>修改记录: {修改人 修改原因 修改时间}
     *
     * @param codeType@throws
     * @return java.util.List<com.tft.framework.codeItem.bean.CodeType>
     * @see #
     */
    @Override
    public List<CodeType> searchCodeType(CodeType codeType,TftSort tftSort) {
        return codeTypeRepository.findAll(codeTypeRepository.createQuery(codeType),tftSort.toSort());
    }

    /**
     * <br>功能描述:  新增或修改代码项类型
     * <br>处理逻辑:
     * <br>作者: lwl liuwanli_eamil@163.com 2018/6/27 23:59
     * <br>修改记录: {修改人 修改原因 修改时间}
     *
     * @param codeType@throws
     * @return void
     * @see #
     */
    @Override
    public CodeType createOrModifyCodeType(CodeType codeType) {
        codeTypeRepository.save(codeType);
        return codeType;
    }

    /**
     * <br>功能描述:  移除代码类型
     * <br>处理逻辑:
     * <br>作者: lwl liuwanli_eamil@163.com 2018/6/28 0:02
     * <br>修改记录: {修改人 修改原因 修改时间}
     *
     * @param codeType@throws
     * @return void
     * @see #
     */
    @Override
    public void removeCodeType(CodeType codeType) {
        codeTypeRepository.delete(codeType);
    }
}
