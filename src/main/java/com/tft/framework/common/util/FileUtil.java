package com.tft.framework.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import com.tft.framework.common.bean.TftBaseBizException;
import org.hibernate.annotations.common.util.StringHelper;

/**
<br>功能描述:文件工具类
<br>处理逻辑:  
<br>作者: lwl liuwanli_eamil@163.com 2018/8/20 17:31
<br>修改记录: {修改人 修改原因 修改时间}
 * @param 
 * @throws 
 * @return 
 * @see #
 */
public class FileUtil {
	
	/**
	<br>功能描述:  解压gzip文件到ServletOutputStream中
	<br>处理逻辑:  
	<br>作者: lwl liuwanli_eamil@163.com 2018/8/20 17:32
	<br>修改记录: {修改人 修改原因 修改时间}
	 * @param [outputStream, filePath]
	 * @throws 
	 * @return void
	 * @see #
	 */
	public static void unGzipFile(ServletOutputStream outputStream, String filePath) throws Exception {
		File file = new File(filePath);
		if (!file.exists()) {
			throw new TftBaseBizException("对不起，您要下载的文件不存在");
		}
		
		FileInputStream fis = null;
		GZIPInputStream gzin = null;
		try {
			fis = new FileInputStream(file);
			gzin = new GZIPInputStream(fis);
			gzin.skip(8);
			int num = 0;
			int dataLength = 1024 * 100;
			byte data[] = new byte[dataLength];
			while ((num = gzin.read(data, 0, dataLength)) != -1){
				outputStream.write(data, 0, num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
	}
	

	/**
	<br>功能描述:  以gzip方式存储文件
	<br>处理逻辑:  
	<br>作者: lwl liuwanli_eamil@163.com 2018/8/20 17:32
	<br>修改记录: {修改人 修改原因 修改时间}
	 * @param [folderPath, fileName, content]
	 * @throws 
	 * @return void
	 * @see #
	 */
	public static void gzipFile(String folderPath, String fileName, byte[] content) throws Exception {
		if(StringHelper.isEmpty(folderPath) || StringHelper.isEmpty(fileName)) {
			throw new TftBaseBizException("请传入要保存的文件所在的文件夹路径和文件名称");
		}
		if(null == content) {
			throw new TftBaseBizException("请传入要保存的文件内容");
		}
		
		//1.创建文件
		File folder = new File(folderPath);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		//2.创建文件
		File file = new File(folderPath + fileName);
		FileOutputStream fos = null;
		GZIPOutputStream out = null;
		try {
			fos = new FileOutputStream(file);
			out = new GZIPOutputStream(fos);
			Random random = new Random();
			byte[] encryptByte = new byte[8];
			random.nextBytes(encryptByte);
			out.write(encryptByte);
			out.write(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}

	}

	/**
	<br>功能描述:  文件拷贝
	<br>处理逻辑:  
	<br>作者: lwl liuwanli_eamil@163.com 2018/8/20 17:32
	<br>修改记录: {修改人 修改原因 修改时间}
	 * @param [resFile, tarFilePath, tarFileName]
	 * @throws 
	 * @return void
	 * @see #
	 */
	public static void moveFile(File resFile, String tarFilePath, String tarFileName) throws Exception{

		int catchSize = 1024 * 4;
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try{
			if(!resFile.exists()) throw new TftBaseBizException("文件不存在");
			File tarFileDir = new File(tarFilePath);
			File tarFile = new File(tarFilePath + File.separator + tarFileName);
			if(!tarFileDir.exists()){
				try {
					tarFileDir.mkdirs();
				}
				catch (Exception e) {
					e.printStackTrace();
					throw new TftBaseBizException("文件路径错误");
				}
			}
			
			InputStream is = new FileInputStream(resFile);
			bis = new BufferedInputStream(is, catchSize);
			
			OutputStream os = new FileOutputStream(tarFile);
			bos = new BufferedOutputStream(os, catchSize);
			
			byte[] b = new byte[catchSize];
			int length = -1;
			while((length = bis.read(b)) != -1){
				bos.write(b, 0, length);
			}
			bos.flush();
			bos.close();
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(null != bis) bis.close();
			if (null != bos){
				bos.flush();
				bos.close();
			}
		}
	}
	
	/**
	<br>功能描述:  删除文件
	<br>处理逻辑:  
	<br>作者: lwl liuwanli_eamil@163.com 2018/8/20 17:33
	<br>修改记录: {修改人 修改原因 修改时间}
	 * @param [file]
	 * @throws 
	 * @return void
	 * @see #
	 */
	public static void removeFile(File file){
		if(null == file)return;
		file.deleteOnExit();
	}

	/**
	<br>功能描述:  保存文件
	<br>处理逻辑:  
	<br>作者: lwl liuwanli_eamil@163.com 2018/8/20 17:34
	<br>修改记录: {修改人 修改原因 修改时间}
	 * @param [path, name, inputStream]
	 * @throws 
	 * @return void
	 * @see #
	 */
	public static void saveFile(String path, String name, InputStream inputStream) throws Exception {
		BufferedOutputStream bos = null;
		BufferedInputStream bis = new BufferedInputStream(inputStream);
		int catchSize = 1024 * 4;
		try {
			File dir = new File(path);
			if(!dir.exists()){
				dir.mkdirs();
			}
			File file = new File(path + File.separator + name);
			
			OutputStream os = new FileOutputStream(file);
			bos = new BufferedOutputStream(os, catchSize);
			
			byte[] b = new byte[catchSize];
			int length = -1;
			while((length = bis.read(b)) != -1){
				bos.write(b, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(null != bis) bis.close();
			if(null != bos){
				bos.flush();
				bos.close();
			}
		}
		
	}
	
	/**
	<br>功能描述:  复制文件
	<br>处理逻辑:  
	<br>作者: lwl liuwanli_eamil@163.com 2018/8/20 17:34
	<br>修改记录: {修改人 修改原因 修改时间}
	 * @param [srcFileName, destFolderPath, destFileName]
	 * @throws 
	 * @return void
	 * @see #
	 */
    public static void copyFile(String srcFileName, String destFolderPath, String destFileName) throws Exception {  
        File srcFile = new File(srcFileName);  
  
        // 判断源文件是否存在  
        if (!srcFile.exists()) {  
        	throw new TftBaseBizException("文件路径错误");
        }
  
        // 判断目标文件夹是否存在  
        File destFolder = new File(destFolderPath);  
        if (!destFolder.exists()) {  
        	destFolder.mkdirs();
        }  
  
        // 复制文件  
        int byteread = 0; // 读取的字节数  
        InputStream in = null;  
        OutputStream out = null;  
  
        try {  
            in = new FileInputStream(srcFile);  
            out = new FileOutputStream(destFolderPath + File.separator + destFileName);  
            byte[] buffer = new byte[1024];  
  
            while ((byteread = in.read(buffer)) != -1) {  
                out.write(buffer, 0, byteread);  
            }  
        } catch (Exception e) {  
           throw e;
        } finally {
        	if (out != null) {
            	out.flush();
               	out.close();  
            }
        	if (in != null) {
            	in.close();  
        	}
        }  
    }  
	/**
	<br>功能描述:  文件下载时，文件名防乱码的格式化处理
	<br>处理逻辑:  
	<br>作者: lwl liuwanli_eamil@163.com 2018/8/20 17:34
	<br>修改记录: {修改人 修改原因 修改时间}
	 * @param [request, fileName]
	 * @throws 
	 * @return java.lang.String
	 * @see #
	 */
	public static String formatFileName4DifferentBrowser(HttpServletRequest request,
			String fileName) throws UnsupportedEncodingException {
		//判断浏览器
		String agent = request.getHeader("User-Agent");
		if (!agent.contains("MSIE")) {
			fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
		}
		fileName = toUtf8String(fileName);
		return fileName;
	}

	public static String toUtf8String(String s) {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			}
			else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				}
				catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0) {
						k += 256;
					}
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}
}
