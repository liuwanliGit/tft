package com.tft.framework.common.util;

import com.tft.framework.common.bean.MsgPromptException;
import com.tft.framework.common.bean.TftBaseBizException;
import com.tft.framework.common.bean.TftPage;
import com.tft.framework.common.bean.TftSort;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.internal.util.StringHelper;
import org.springframework.http.HttpStatus;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <pre>职责: controller层数据接收和发送工具类
 * 使用方法：
 * 1.
 * public void doFun(HttpServletRequest request,HttpServletResponse response) throws Exception{
 * HttpIO httpIO = new HttpIO(request, response);
 * try {
 * //doGetFun   接收数据
 *
 * //doSeveviceFun  调用service层方法处理业务，准备返回数据
 *
 * //doSetFun   设置要返回的数据
 * httpIO.setSuccessMsg("**成功");
 * } catch (Exception e) {
 * //			e.printStackTrace();
 * //			logger.error(e.getMessage(), e);
 * httpIO.setFailedMsg("**失败", e);
 * } finally {
 * httpIO.Out();
 * }
 * }
 *
 * 2.导出文件
 * public void doExportDutyPlan(HttpServletRequest request,HttpServletResponse response) throws Exception{
 * HttpIO httpIO = new HttpIO(request, response);
 * try {
 * //取出planId
 * String planId = request.getParameter("planId");
 *
 * //模板路径
 * String tempFileName = request.getSession().getServletContext().getRealPath("/")+DutyConstants.DPEXCELTEMPNAME;
 *
 * //获取模板文件后缀
 * String suffix = tempFileName.substring(tempFileName.lastIndexOf('.'));
 *
 * //导出文件名称
 * String name = "";
 * if (StringHelper.isNotEmpty(planId)) {
 * DutyPlan dutyPlan = dutyService.searchDutyPlan(planId);
 * if (dutyPlan != null) {
 * name = dutyPlan.getName()+suffix;
 * }
 * } else {
 * name = "值班安排"+suffix;
 * }
 *
 * //获取导出的workbook
 * Workbook workbook = dutyService.exportDutyPlan(planId,tempFileName);
 *
 * //导出
 * httpIO.outWorkbook(workbook, name);
 * } catch (Exception e) {
 * logger.error(e.getMessage(), e);
 * e.printStackTrace();
 * httpIO.setFailedMsg("导出失败", e);
 * httpIO.outAjaxDate();
 * }
 * }
 *
 * </pre>
 *
 * @author lwl 2016-11-2 上午9:33:26
 * @see #
 * @since <pre>修改记录（修改时间、修改人、修改内容、修改原因）</pre>
 */
public class HttpIO {

    private HttpServletRequest request;

    private HttpServletResponse response;

    private Map<String, Object> result = new HashMap<String, Object>();

    public HttpIO(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
    /**
    <br>功能描述:  返回状态码
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/10/13 14:13
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [httpStausCode]
     * @throws 
     * @return void
     * @see #
     */
    public void setHttpStatusCode(HttpStatus httpStausCode){
        this.result.put("status",httpStausCode);
    }

    /**
     * <pre>功能描述: 获取queryCondition，带时间格式
     * 使用方法：
     * 使用约束：
     * 逻辑：
     * </pre>
     * @return
     * @see #
     * @since <pre>create lwl 2016-11-2 上午9:24:25
     * 修改记录（修改时间、修改人、修改内容、修改原因）
     * </pre>
     */
    public Map<String, String> getQueryCondition() {
        String queryCondition = request.getParameter("queryCondition");
        return JsonUtil.jsonToMap(queryCondition);
    }

    /**
     * <pre>功能描述: 查询queryCondition中的value
     * 使用方法：
     * 使用约束：
     * 逻辑：
     * </pre>
     *
     * @param name
     * @return
     * @see #
     * @since <pre>create liuwa 2016-11-11 下午12:26:15
     * 修改记录（修改时间、修改人、修改内容、修改原因）
     * </pre>
     */
    public String getQueryConditionValue(String name) {
        Map<String, String> map = this.getQueryCondition();
        if (null == map) {
            return null;
        }
        return map.get(name);
    }

    public String getObjectId() {
        return request.getParameter("objectId");
    }

    /**
     * <pre>功能描述: 获取object
     * 使用方法：首先从object中取，如果没有则去queryCondition中取
     * 使用约束：
     * 逻辑：
     * </pre>
     *
     * @return
     * @see #
     * @since <pre>create lwl 2016-11-2 上午9:26:50
     * 修改记录（修改时间、修改人、修改内容、修改原因）
     * </pre>
     */
    public <T> T getObject(Class<T> clazz) {

        String object = request.getParameter("object");
        if (StringHelper.isEmpty(object)) {
            object = request.getParameter("queryCondition");
        }

        return JsonUtil.jsonToBean(object, clazz);
    }

    /**
     * <pre>功能描述: 获取objects
     * 使用方法：
     * 使用约束：
     * 逻辑：
     * </pre>
     *
     * @return
     * @see #
     * @since <pre>create lwl 2016-11-2 上午9:27:05
     * 修改记录（修改时间、修改人、修改内容、修改原因）
     * </pre>
     */
    public <T> List<T> getObjects(Class<T> clazz) {

        String objects = request.getParameter("objects");

        return JsonUtil.jsonToBeans(objects, clazz);
    }

    /**
     * <pre>功能描述: 获取pageInfo
     * 使用方法：
     * 使用约束：
     * 逻辑：
     * </pre>
     *
     * @return
     * @see #
     * @since <pre>create lwl 2016-11-2 上午9:27:22
     * 修改记录（修改时间、修改人、修改内容、修改原因）
     * </pre>
     */
    public TftPage getPageInfo() {

        String page = request.getParameter("page");

        if (StringHelper.isEmpty(page)) {
            page = request.getParameter("pageInfo");
        }
        TftPage tftPage = JsonUtil.jsonToBean(page, TftPage.class);
        return tftPage;
    }
    /**
    <br>功能描述:  获取排序信息
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/8 0:37
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param []
     * @throws 
     * @return com.tft.framework.common.bean.TftPage
     * @see #
     */
    public TftSort getSort() {

        String page = request.getParameter("sort");

        if (StringHelper.isEmpty(page)) {
            page = request.getParameter("sort");
        }
        TftSort tftSort = JsonUtil.jsonToBean(page, TftSort.class);
        if(tftSort == null) tftSort = new TftSort();
        return tftSort;
    }

    /**
     * <pre>功能描述: 设置返回的pageInfo
     * 使用方法：
     * 使用约束：
     * 逻辑：
     * </pre>
     *
     * @param page
     * @see #
     * @since <pre>create lwl 2016-11-2 上午9:32:03
     * 修改记录（修改时间、修改人、修改内容、修改原因）
     * </pre>
     */
    public void setPageInfo(TftPage page) {
        page.setData(null);
        result.put("pageInfo", page);
    }

    /**
     * <pre>功能描述: 设置返回的otherDatas
     * 使用方法：
     * 使用约束：
     * 逻辑：
     * </pre>
     *
     * @param otherDatas
     * @see #
     * @since <pre>create lwl 2016-11-2 上午9:31:48
     * 修改记录（修改时间、修改人、修改内容、修改原因）
     * </pre>
     */
    public void setOtherDatas(String otherDatas) {
        result.put("otherDatas", otherDatas);
    }

    /**
     * <pre>功能描述: 从request中获取文件
     * 使用方法：   文件流和文件通过参数返回,inputSteams不能传null;files可以传null;
     * 使用约束：
     * 逻辑：
     * </pre>
     *
     * @param inputSteams
     * @param files
     * @throws Exception
     * @see #
     * @since <pre>create lwl 2016-11-2 下午4:49:13
     * 修改记录（修改时间、修改人、修改内容、修改原因）
     * </pre>
     */
    @SuppressWarnings("unchecked")
    public void getFileInputStream(List<InputStream> inputSteams, List<File> files) throws Exception {

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(-1);//设置上传文件限制大小,-1无上限

        //获取文件流
        List<FileItem> list = upload.parseRequest(request);
        for (FileItem item : list) {
            if (!item.isFormField()) {            //不是普通表单类型，则是文件类型
                InputStream inputStream = item.getInputStream();
                inputSteams.add(inputStream);

                if (files != null) {
                    String value = item.getName();    //文件名称
                    files.add(new File(value));
                }
            }
        }
    }

    /**
     * <pre>功能描述: 设置result结果为true
     * 使用方法：
     * 使用约束：
     * 逻辑：
     * </pre>
     *
     * @see #
     * @since <pre>create lwl 2016-11-2 上午9:30:07
     * 修改记录（修改时间、修改人、修改内容、修改原因）
     * </pre>
     */
    public void setResultTrue() {
        result.put("result", true);
    }

    /**
     * <pre>功能描述: 设置result结果为false
     * 使用方法：
     * 使用约束：
     * 逻辑：
     * </pre>
     *
     * @see #
     * @since <pre>create lwl 2016-11-2 上午9:30:35
     * 修改记录（修改时间、修改人、修改内容、修改原因）
     * </pre>
     */
    public void setResultFalse() {
        result.put("result", false);
    }

    /**
     * <pre>功能描述: 设置返回信息msg
     * 使用方法：
     * 使用约束：
     * 逻辑：
     * </pre>
     *
     * @param msg
     * @see #
     * @since <pre>create lwl 2016-11-2 上午9:30:50
     * 修改记录（修改时间、修改人、修改内容、修改原因）
     * </pre>
     */
    public void setMsg(String msg) {
        result.put("msg", msg);
    }

    /**
     * <pre>功能描述: 设置操作成功返回的msg
     * 使用方法：
     * 使用约束：
     * 逻辑：
     * </pre>
     *
     * @param msg
     * @see #
     * @since <pre>create lwl 2016-11-2 上午9:58:52
     * 修改记录（修改时间、修改人、修改内容、修改原因）
     * </pre>
     */
    public void setSuccessMsg(String msg) {
        this.setResultTrue();
        result.put("msg", msg);
    }

    /**
     * <pre>功能描述: 设置操作失败返回的msg
     * 使用方法：
     * 使用约束：
     * 逻辑：
     * </pre>
     *
     * @param msg
     * @param e
     * @see #
     * @since <pre>create lwl 2016-11-2 上午9:59:09
     * 修改记录（修改时间、修改人、修改内容、修改原因）
     * </pre>
     */
    public void setFailedMsg(String msg, Exception e) {
        this.setResultFalse();
        if (e instanceof TftBaseBizException) {
            //回滚的事务
            this.setMsg(msg + "！" + e.getMessage());
        } else if (e instanceof MsgPromptException) {
            //不会滚的事务
            this.setMsg(e.getMessage());
        } else {
            this.setMsg(msg + "！");
        }
    }

    /**
     * <pre>功能描述: 设置返回的object
     * 使用方法：
     * 使用约束：
     * 逻辑：
     * </pre>
     *
     * @param obj
     * @see #
     * @since <pre>create lwl 2016-11-2 上午9:31:06
     * 修改记录（修改时间、修改人、修改内容、修改原因）
     * </pre>
     */
    public <T> void setObject(T obj) {
        if (null == obj) {
            result.put("object", "");
        }
        result.put("object", obj);
    }

    /**
     * <pre>功能描述: 设置返回的objects
     * 使用方法：
     * 使用约束：
     * 逻辑：
     * </pre>
     *
     * @param objs
     * @see #
     * @since <pre>create lwl 2016-11-2 上午9:31:21
     * 修改记录（修改时间、修改人、修改内容、修改原因）
     * </pre>
     */
    public <T> void setObjects(List<T> objs) {
        if (null == objs) {
            objs = new ArrayList<T>();
        }
        result.put("objects", objs);
    }

    /**
     * <pre>功能描述: ajax返回数据 默认返回数据"text/plain"
     * 使用方法：
     * 使用约束：
     * 逻辑：
     * </pre>
     *
     * @throws IOException
     * @see #
     * @since <pre>create lwl 2016-11-2 上午9:32:19
     * 修改记录（修改时间、修改人、修改内容、修改原因）
     * </pre>
     */
    public void outAjaxDate() throws IOException {
        this.response.setContentType("text/plain;charset=utf-8");
        PrintWriter out = this.response.getWriter();

        if (out != null) {
            out.print(JsonUtil.objToJson(result));
            out.flush();
            out.close();
        }
    }

    /**
     * <pre>功能描述: 导出excel文件
     * 使用方法：
     * 使用约束：
     * 逻辑：
     * </pre>
     *
     * @param workbook
     * @param workbookName
     * @throws Exception
     * @see #
     * @since <pre>create lwl 2016-11-2 下午4:06:46
     * 修改记录（修改时间、修改人、修改内容、修改原因）
     * </pre>
     */
    public void outWorkbook(Workbook workbook, String workbookName) throws Exception {
        this.response.setContentType("application/vnd.ms-excel");
        this.response.setHeader("content-disposition", "attachment;filename=" + new String(workbookName.getBytes("gb2312"), "ISO8859-1"));
        this.response.setCharacterEncoding("UTF-8");
        ServletOutputStream out = this.response.getOutputStream();
        workbook.write(out);

        if (out != null) {
            out.flush();
            out.close();
        }
    }
    /**
    <br>功能描述:  将图像往前端送
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/25 16:37
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [image, suffix, cache]
     * @throws 
     * @return void
     * @see #
     */
    public void outImgBuffer(BufferedImage image,String suffix,Boolean cache)throws Exception{
        if(cache!=null && true == cache){
            this.response.setHeader("Pragma", "no-cache");
            this.response.setHeader("Cache-Control", "no-cache");
            this.response.setDateHeader("Expires", 0);
        }
        ServletOutputStream out = this.response.getOutputStream();
        ImageIO.write(image,suffix,out);
        if (out != null) {
            out.flush();
            out.close();
        }
    }
    /**
     *
     * <pre>功能描述: 下载文件
     * 使用方法：
     * 使用约束：
     * 逻辑：
     * </pre>
     * @param file
     * @param name
     * @throws Exception
     * @see #
     * @since
     * <pre>create liuwa 2018-8-15 下午5:06:02
     * 修改记录（修改时间、修改人、修改内容、修改原因）
     * </pre>
     */
    public void outFile(File file,String name)throws Exception{
        this.response.setContentType("application/*");
        this.response.setHeader("content-disposition", "attachment;filename=" + new String(name.getBytes("gb2312"), "ISO8859-1"));
        this.response.setCharacterEncoding("UTF-8");
        ServletOutputStream out = this.response.getOutputStream();
        FileInputStream fis = new FileInputStream(file);
        int j = 0;
        byte[] buffer = new byte[1024];
        while((j = fis.read(buffer)) > 0){
            out.write(buffer, 0, j);
        }
        // 关闭流
        fis.close();
        if(out!=null){
            out.flush();
            out.close();
        }
    }
    /**
     *
     * <pre>功能描述: 下载压缩文件
     * 使用方法：
     * 使用约束：
     * 逻辑：
     * </pre>
     * @param entryMap {下载的压缩文件名，文件对象}
     * @throws Exception
     * @see #
     * @since
     * <pre>create liuwa 2018-8-15 下午5:00:45
     * 修改记录（修改时间、修改人、修改内容、修改原因）
     * </pre>
     */
    public void outZipEntry(Map<String,File> entryMap)throws Exception{
        ServletOutputStream outputStream = response.getOutputStream();
        ZipOutputStream out = new ZipOutputStream(outputStream);
        for(String fileName:entryMap.keySet()){
            FileInputStream fis = new FileInputStream(entryMap.get(fileName));
            ZipEntry entry = new ZipEntry(fileName);
            out.putNextEntry(entry);
            // 进行写操作
            int j = 0;
            byte[] buffer = new byte[1024];
            while((j = fis.read(buffer)) > 0){
                out.write(buffer, 0, j);
            }
            // 关闭输入流
            out.closeEntry();
            fis.close();
        }
        if(out!=null){
            out.flush();
            out.close();
        }
    }
    /**
     * <pre>功能描述: 将返回数据打印出来看
     * 使用方法：
     * 使用约束：
     * 逻辑：
     * </pre>
     *
     * @see #
     * @since <pre>create lwl 2016-11-2 上午10:33:21
     * 修改记录（修改时间、修改人、修改内容、修改原因）
     * </pre>
     */
    public void printOutInfo() {
        System.out.println(JsonUtil.objToJson(result));
    }

    public void setOtherData(Map<String, Object> otherData) {
        result.put("otherData", otherData);
    }

    /**
     * <pre>功能描述: 放置返回结果
     * 使用方法：
     * 使用约束：
     * 逻辑：
     * </pre>
     *
     * @param key
     * @param value
     * @see #
     * @since <pre>create liuwl 2016-12-26 上午10:04:50
     * 修改记录（修改时间、修改人、修改内容、修改原因）
     * </pre>
     */
    public void setKeyValue(String key, Object value) {
        result.put(key, value);
    }

    /**
     * <pre>功能描述: 通过request.getParameter(key)取值
     * 使用方法：
     * 使用约束：
     * 逻辑：
     * </pre>
     *
     * @param key
     * @return
     * @see #
     * @since <pre>create liuwl 2016-12-26 上午10:26:07
     * 修改记录（修改时间、修改人、修改内容、修改原因）
     * </pre>
     */
    public String getValue(String key) {
        return request.getParameter(key);
    }

    /**
     * <pre>功能描述: 从httpRequest中获取文件流集合
     * 使用方法：默认文件存储路径在upload下，缓冲区大小伟1024*1024，文件大小不限制
     * 使用约束：
     * 逻辑：
     * </pre>
     *
     * @return object[0]:文件流列表    object[1]:文件列表
     * @throws Exception
     * @see #
     * @since <pre>create liuwl 2016-12-30 下午4:53:53
     * 修改记录（修改时间、修改人、修改内容、修改原因）
     * </pre>
     */
    public Object[] getInputStreams() throws Exception {
        return this.getInputStreams("upload", 1024 * 1024, -1);
    }

    /**
     * <pre>功能描述: 从httpRequest中获取文件流集合
     * 使用方法：
     * 使用约束：
     * 逻辑：
     * </pre>
     *
     * @param saveDirectory
     * @param bufferSize
     * @param fileMaxSize
     * @throws Exception
     * @return object[0]:文件流列表 object[1]:文件列表
     * @see #
     * @since <pre>create liuwl 2016-12-30 下午5:03:04
     * 修改记录（修改时间、修改人、修改内容、修改原因）
     * </pre>
     */
    @SuppressWarnings("unchecked")
    public Object[] getInputStreams(String saveDirectory, int bufferSize, int fileMaxSize) throws Exception {

        DiskFileItemFactory factory = new DiskFileItemFactory();
        HttpSession session = request.getSession();
        saveDirectory = session.getServletContext().getRealPath("/" + saveDirectory);
        factory.setRepository(new File(saveDirectory));
        factory.setSizeThreshold(bufferSize);//设置创建缓冲大小
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(fileMaxSize);//设置上传文件限制大小,-1无上限

        //获取文件流
        List<InputStream> excels = new ArrayList<InputStream>();
        List<File> files = new ArrayList<File>();
        List<FileItem> list = upload.parseRequest(request);
        for (FileItem item : list) {
            if (item.isFormField()) {            //判断是否是文件流
//				String va = item.getString("UTF-8");  
            } else {
                String value = item.getName();//会将完整路径名传过来
                InputStream excel = item.getInputStream();
                files.add(new File(value));
                excels.add(excel);
            }
        }
        Object objs[] = {excels, files};
        return objs;
    }


}
