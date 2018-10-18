package com.tft.framework.common.util;

import com.tft.framework.common.bean.MsgPromptException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.internal.util.StringHelper;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Iterator;

/**
 * <br>类描述：处理office文档工具类
 * <br>author： lwl liuwanli_eamil@163.com	2018/7/31 9:46
 *
 * @ClassName PoiUtil
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
public class PoiUtil {
    /**
    <br>功能描述:  从数据流中获取workbook
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/31 10:17
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [excel, file]
     * @throws 
     * @return org.apache.poi.ss.usermodel.Workbook
     * @see #
     */
    public static Workbook getWorkbookFromInputStream(InputStream excel, File file)throws Exception {
        Workbook workbook = null;
        //1.用excel文件流创建一个HSSFWorkbook对象
        if(file==null||excel==null){
            throw new MsgPromptException("文件或数据流不能为空");
        }
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());

        if(suffix.equalsIgnoreCase("xls")){
            //03版
            try {
                workbook = new HSSFWorkbook(excel);
            }catch (Exception e) {
                e.printStackTrace();
                throw new MsgPromptException("文件格式错误");
            }
        }else{
            //07版
            try {
                workbook = new XSSFWorkbook(excel);
            }catch (Exception e) {
                e.printStackTrace();
                throw new MsgPromptException("文件格式错误");
            }
        }
        return workbook;
    }
    /**
    <br>功能描述:  从日期单元格中获取时间字符串
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/31 11:27
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [cell]
     * @throws 
     * @return java.lang.String
     * @see #
     */
    private static String getDateString(Cell cell){
        String dataFormat = cell.getCellStyle().getDataFormatString();
        SimpleDateFormat sdf_dateAndTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat sdf_time = new SimpleDateFormat("hh:mm:ss");
        String value = null;
        value = sdf_dateAndTime.format(cell.getDateCellValue());
        return value;
    }
    /**
    <br>功能描述:  判断行是否为空行
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/31 11:30
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [row]
     * @throws 
     * @return boolean
     * @see #
     */
   public static boolean isEmptyRow (Row row){
       if(row==null){
           return true;
       }
       Iterator<Cell> cellIterator = row.iterator();
       while (cellIterator.hasNext()){
           Cell cell = cellIterator.next();
           if(cell != null){
               String value = getValue(cell);
               //有一个cell非空，则行非空
               if(StringHelper.isNotEmpty(value)){
                   return false;
               }
           }
       }
       //遍历完所有cell都是empty，则是空行
       return true;
   }
    /**
    <br>功能描述:  获取指定单元格的值
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/31 11:27
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [sheet, i, j]
     * @throws 
     * @return java.lang.String
     * @see #
     */
    public static String getValue(Cell cell){
        String value = null;
        switch (cell.getCellTypeEnum()){
            case STRING:
                value = cell.getStringCellValue();
                break;
            case NUMERIC:
                if("General".equals(cell.getCellStyle().getDataFormatString())){
                    value = Double.toString(cell.getNumericCellValue());
                }else{
                    value = getDateString(cell);
                }
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue()?"1":"0";
                break;
            case BLANK:
                value = "";
                break;
            default:
                value = cell.toString();
                break;
        }
        return value;
    }
    /**
    <br>功能描述:
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/31 13:38
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [cell, value]
     * @throws 
     * @return void
     * @see #
     */
    public static  void setValue(Cell cell,String value)throws Exception{
        String dataFormat = cell.getCellStyle().getDataFormatString();
        switch (cell.getCellTypeEnum()){
            case NUMERIC:
                if("General".equals(dataFormat)){
                    cell.setCellValue(Double.parseDouble(value));
                }else{
                    cell.setCellValue(DateUtil.stringToDate(value));
                }
                break;
            case BOOLEAN:
                cell.setCellValue(!value.equals("0"));
                break;
            default:
                cell.setCellValue(value);
                break;
        }
    }
    /**
    <br>功能描述:  合并单元格
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/31 14:33
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [sheet, startRow, startCol, endRow, endCol]
     * @throws 
     * @return void
     * @see #
     */
    public static void mergeCell(Sheet sheet,int startRow,int startCol,int endRow,int endCol){
        CellRangeAddress region=new CellRangeAddress(startRow,endRow, startCol,endCol);
        sheet.addMergedRegion(region);
    }
    /**
    <br>功能描述:  复制单元格
    <br>处理逻辑:  
    <br>作者: lwl liuwanli_eamil@163.com 2018/7/31 15:58
    <br>修改记录: {修改人 修改原因 修改时间}
     * @param [srcCell, disCell]
     * @throws 
     * @return void
     * @see #
     */
    public static void copyCell(Cell srcCell,Cell disCell)throws Exception{
        String value = getValue(srcCell);
        disCell.setCellType(srcCell.getCellTypeEnum());
        disCell.setCellStyle(srcCell.getCellStyle());
        setValue(disCell,value);
    }
}
