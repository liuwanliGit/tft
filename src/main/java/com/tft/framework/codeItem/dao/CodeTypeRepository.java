package com.tft.framework.codeItem.dao;

import com.tft.framework.codeItem.bean.CodeType;
import com.tft.framework.common.core.TftBaseDao;
import com.tft.framework.common.core.TftQuery;

/**
 * <br>类描述：代码项类别dao层接口
 * <br>author： lwl liuwanli_eamil@163.com	2018/6/28 0:04
 *
 * @ClassName CodeItemRepositoryImpl
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public interface CodeTypeRepository extends TftBaseDao<CodeType> {
    public TftQuery createQuery(CodeType codeType);
}
