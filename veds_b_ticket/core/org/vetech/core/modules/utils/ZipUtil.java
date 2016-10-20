package org.vetech.core.modules.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * zip压缩工具 <功能详细描述>
 * 
 * @author Administrator
 * @version [版本号, Jun 8, 2012]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class ZipUtil {
	/**
	 * 压缩
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static byte[] compressByte(byte[] str) throws Exception {
		if ((str == null) || (str.length == 0)) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		gzip.write(str);
		gzip.close();
		return out.toByteArray();
	}

	/**
	 * 解压缩
	 */
	public static byte[] uncompressByte(byte[] bytes) throws IOException {
		if (bytes == null || bytes.length == 0) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		GZIPInputStream gunzip = new GZIPInputStream(in);
		byte[] buffer = new byte[256];
		int n;
		while ((n = gunzip.read(buffer)) >= 0) {
			out.write(buffer, 0, n);
		}
		out.close();
		in.close();
		return out.toByteArray();
	}

	/**
	 * 字符串压缩，返回经过Base64编码的文本
	 * 
	 * @param source
	 * @param encode
	 * @return
	 * @throws Exception
	 */
	public static String compressStrBase64(String source, String encode) throws Exception {
		if (StringUtils.isBlank(source)) {
			return null;
		}
		byte[] bs = source.getBytes(encode);
		byte[] bt = compressByte(bs);
		return Base64.encodeBase64String(bt);
	}

	/**
	 * 把通过base64编码的字符串解压缩
	 * 
	 * @param soruce
	 * @param encode
	 * @return
	 * @throws Exception
	 */
	public static String uncompressStrBase64(String source, String encode) throws Exception {
		if (StringUtils.isBlank(source)) {
			return null;
		}
		byte[] dbyte = Base64.decodeBase64(source);
		byte[] unbyte = uncompressByte(dbyte);
		return new String(unbyte, encode);
	}

	/**
	 * 把文件以GZIP数据流读出来
	 * 
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public static byte[] fileGzip(String filename) throws Exception {
		BufferedInputStream bis = null;
		GZIPOutputStream gzip = null;
		File f = new File(filename);
		FileInputStream fis = new FileInputStream(f);
		bis = new BufferedInputStream(fis);
		byte[] buf = new byte[1024];
		int len;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		gzip = new GZIPOutputStream(bos);
		while ((len = bis.read(buf)) != -1) {
			gzip.write(buf, 0, len);
			gzip.flush();
		}
		bis.close();
		gzip.close();
		return bos.toByteArray();
	}

	/**
	 * 把文件压缩为zip，并返回byte
	 * 
	 * @param filePath
	 * @return
	 * @throws Exception
	 *             [参数说明]
	 * 
	 * @return byte[] [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static byte[] zipFile(String filePath) throws IOException {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		ZipOutputStream zos = null;
		File f = new File(filePath);
		FileInputStream fis = new FileInputStream(f);
		bis = new BufferedInputStream(fis);
		byte[] buf = new byte[1024];
		int len;
		ByteArrayOutputStream fos = new ByteArrayOutputStream();
		bos = new BufferedOutputStream(fos);
		zos = new ZipOutputStream(bos);// 压缩包
		ZipEntry ze = new ZipEntry(f.getName());// 这是压缩包名里的文件名
		zos.putNextEntry(ze);// 写入新的ZIP文件条目并将流定位到条目数据的开始处
		while ((len = bis.read(buf)) != -1) {
			zos.write(buf, 0, len);
			zos.flush();
		}
		bis.close();
		zos.close();
		bos.close();
		return fos.toByteArray();
	}

	/**
	 * 把字符串压缩为zip字节流
	 * 
	 * @param str
	 *            需要压缩的字符串
	 * @param filename
	 *            压缩后 zip文件中的文件名
	 * @return
	 * @throws IOException
	 */
	public static byte[] zipString(String str, String filename) throws IOException {
		ZipOutputStream zos = null;
		BufferedOutputStream bos = null;
		ByteArrayOutputStream fos = null;
		fos = new ByteArrayOutputStream();
		bos = new BufferedOutputStream(fos);
		zos = new ZipOutputStream(bos);// 压缩包
		ZipEntry ze = new ZipEntry(filename);// 这是压缩包名里的文件名
		zos.putNextEntry(ze);// 写入新的ZIP文件条目并将流定位到条目数据的开始处
		zos.write(str.getBytes("UTF-8"));
		zos.flush();

		zos.close();
		bos.close();
		fos.close();
		return fos.toByteArray();

	}

	public static byte[] readStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		return outSteam.toByteArray();
	}

	/**
	 * 读取一个Zip压缩的文件中文本
	 * 
	 * @param ff
	 * @param encode
	 * @return
	 * @throws Exception
	 */
	public static String readZipFile(File ff, String encode) throws Exception {
		String str = "";
		ZipFile zipFile = new ZipFile(ff);
		Enumeration<ZipEntry> entrys = (Enumeration<ZipEntry>) zipFile.entries();
		while (entrys.hasMoreElements()) {
			ZipEntry entry = entrys.nextElement();
			InputStream dataIn = zipFile.getInputStream(entry);
			byte[] b = ZipUtil.readStream(dataIn);
			str = new String(b, encode);
			dataIn.close();
			break;
		}
		zipFile.close();
		return str;
	}

	/***
	 * 解压Zip
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static byte[] unZipByte(byte[] data) throws IOException {
		byte[] b = null;
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		ZipInputStream zip = new ZipInputStream(bis);
		while (zip.getNextEntry() != null) {
			byte[] buf = new byte[1024];
			int num = -1;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((num = zip.read(buf, 0, buf.length)) != -1) {
				baos.write(buf, 0, num);
			}
			b = baos.toByteArray();
			baos.flush();
			baos.close();
		}
		zip.close();
		bis.close();

		return b;
	}

	/**
	 * ZIP压缩功能. 压缩fromFile 为 toZipFile
	 * 
	 * @throws Exception
	 */

	public static void zipFile(File fromFile, File toZipFile) throws Exception {
		InputStream is = null;
		ZipOutputStream zos = null;
		zos = new ZipOutputStream(new FileOutputStream(toZipFile));
		byte[] buf = new byte[1024];
		int readLen = 0;
		ZipEntry ze = new ZipEntry(fromFile.getName());
		ze.setSize(fromFile.length());
		ze.setTime(fromFile.lastModified());
		zos.putNextEntry(ze);
		is = new BufferedInputStream(new FileInputStream(fromFile));
		while ((readLen = is.read(buf, 0, 1024)) != -1) {
			zos.write(buf, 0, readLen);
		}
		is.close();
		zos.close();
	}
	/**
	 * 把多个文件压缩为zip文件
	 * @param orgFilePathArr 要压缩的文件
	 * @param zipPath 压缩文件的路径，如：D:/test/yasuo.zip
	 * @param deleteOrgFile 是否删除原文件
	 * 
	 * @throws Exception [参数说明]
	 * @return void [返回类型说明]
	 */
	public static void zipFile(String[] orgFilePathArr,String zipPath,boolean deleteOrgFile) throws Exception {
		ZipOutputStream zos = null;
		zos = new ZipOutputStream(new FileOutputStream(zipPath));// 压缩包
		List<File> files=new ArrayList<File>();
		for(String filePath : orgFilePathArr){
			File f = new File(filePath);
			files.add(f);
			ZipEntry ze = new ZipEntry(f.getName());// 这是压缩包名里的文件名
			zos.putNextEntry(ze);// 写入新的ZIP文件条目并将流定位到条目数据的开始处
			FileInputStream fis = new FileInputStream(f);
			BufferedInputStream bis = new BufferedInputStream(fis);
			byte[] buf = new byte[1024];
			int len;
			while ((len = bis.read(buf)) != -1) {
				zos.write(buf, 0, len);
			}
			bis.close();
			fis.close();
		}
		zos.flush();
		zos.close();
		if(deleteOrgFile){//删除原文件
			try {
				for (File f : files) {
					f.deleteOnExit();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	// 测试方法
	public static void main(String[] args) throws Exception {
		String a = FileUtils.readFileToString(new File("d:/temp/pay2016-05-12.log"), "GBK");
//		byte[] c = compressByte(a.getBytes("UTF-8"));
//		FileUtils.writeByteArrayToFile(new File("d:/temp/111.zip"), c);
//		c = zipString(a, "aaa.log");
//		FileUtils.writeByteArrayToFile(new File("d:/temp/aaa.zip"), c);
		
		byte[] a0 =compressByte(a.getBytes());
		FileUtils.writeByteArrayToFile(new File("d:/temp/new0.zip"), a0);
		
		String a1 = compressStrBase64(a, "UTF-8");
		//System.out.println(a1);
		FileUtils.write(new File("d:/temp/new1.txt"), a1);
//		
 		String a2 = uncompressStrBase64(a1, "UTF-8");
//				System.out.println(a2);
 		
 		FileUtils.write(new File("d:/temp/new2.txt"), a2);
	}

}
