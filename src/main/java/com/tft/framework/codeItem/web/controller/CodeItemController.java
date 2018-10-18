package com.tft.framework.codeItem.web.controller;

import com.tft.framework.codeItem.bean.CodeItem;
import com.tft.framework.codeItem.bean.CodeType;
import com.tft.framework.codeItem.service.CodeItemService;
import com.tft.framework.codeItem.service.CodeTypeService;
import com.tft.framework.common.bean.TftPage;
import com.tft.framework.common.bean.TftSort;
import com.tft.framework.common.util.HttpIO;
import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/8 11:10
 *
 * @ClassName CodeItemController
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Controller("codeItemControllder")
@RequestMapping("/api/codeItem/*")
public class CodeItemController {

    @Autowired
    private CodeItemService codeItemService;
    @Autowired
    private CodeTypeService codeTypeService;

    /**
    <br>功能描述:  分页加载代码项
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/8 13:48
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [request, response]
     * @throws 
     * @return void
     * @see #
     */
    @RequestMapping("loadCodeItemPage")
    public void doLoadCodeItemPage(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        HttpIO httpIO = new HttpIO(request,response);
        try {
            CodeItem codeItem = httpIO.getObject(CodeItem.class);
            TftPage tftPage = httpIO.getPageInfo();
            TftSort sort = httpIO.getSort();
            tftPage = codeItemService.searchCodeItem(codeItem,tftPage,sort);
            httpIO.setObjects((List<CodeItem>) tftPage.getData());
            httpIO.setPageInfo(tftPage);
            httpIO.setSuccessMsg("加载成功");
        }catch (Exception e){
            e.printStackTrace();
            httpIO.setFailedMsg("加载失败",e);
        }finally {
            httpIO.outAjaxDate();
        }
    }
    /**
    <br>功能描述:
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/21 10:47
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [request, response]
     * @throws 
     * @return void
     * @see #
     */
    @RequestMapping("loadCodeItemByType")
    public void doLoadCodeItemByType(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        HttpIO httpIO = new HttpIO(request,response);
        try {
            String type = httpIO.getValue("type");
            CodeItem codeItem = new CodeItem();
            codeItem.setTypeCode(type);
            TftPage page = codeItemService.searchCodeItem(codeItem,null,null);
            //将查到的代码项按type分组
            List<CodeItem> codeItems = (List<CodeItem>)page.getData();
            if(codeItems!=null){
                Map<String,List<CodeItem>> resultMap = new HashMap<>();
                for(CodeItem item:codeItems){
                    List<CodeItem> items = resultMap.get(item.getTypeCode());
                    if(items==null) {items = new ArrayList<>(); resultMap.put(item.getTypeCode(),items);}
                    items.add(item);
                }
                httpIO.setObject(resultMap);
            }
            httpIO.setSuccessMsg("加载成功");
        }catch (Exception e){
            e.printStackTrace();
            httpIO.setFailedMsg("加载失败",e);
        }finally {
            httpIO.outAjaxDate();
        }
    }
    /**
    <br>功能描述:  加载字典项类别
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/21 10:21
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [request, response]
     * @throws 
     * @return void
     * @see #
     */
    @RequestMapping("loadCodeTypeLZ")
    public void doLoadCodeTypeLZ(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        HttpIO httpIO = new HttpIO(request,response);
        try {
            CodeType type = httpIO.getObject(CodeType.class);
            if(StringHelper.isNotEmpty(type.getParentCode())){
                List types = codeTypeService.searchCodeType(type,null);
                httpIO.setObjects(types);
            }
            httpIO.setSuccessMsg("加载成功");
        }catch (Exception e){
            e.printStackTrace();
            httpIO.setFailedMsg("加载失败",e);
        }finally {
            httpIO.outAjaxDate();
        }
    }
}
