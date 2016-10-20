package org.vetech.core.modules.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.AgeFileFilter;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.lang.StringUtils;

/**
 * 文件操作工具类
 * 
 * @author zl
 * @version [版本号, 2014-1-3]
 * @see [相关类/方法]
 * @since [CPS]
 */
public class FileOperate {

	private static String FILESPARA = "/";

	/**
	 * 获取到classes的绝对路径
	 * 
	 * @param RelativeFile
	 * @return
	 */
	public static String getAbsoluteFile(String RelativeFile) {
		String filePath = FileOperate.class.getResource("/").toString();
		if (filePath.startsWith("file:")) {
			filePath = filePath.substring("file:".length());
		}
		return filePath + RelativeFile;
	}

	public static void checkFile(String filepath) throws RuntimeException {
		String ff = StringUtils.trimToEmpty(filepath).toUpperCase();
		if (ff.indexOf("JSP") >= 0 || ff.indexOf("CLASS") >= 0 || ff.indexOf("WEB-INF") >= 0) {
			throw new RuntimeException("目录文件名不对");
		}
	}

	/**
	 * 文本文件读取
	 * 
	 * @param filePathAndName
	 *            文件
	 * 
	 * @return String [返回类型说明]
	 * @throws IOException
	 * @see [类、类#方法、类#成员]
	 */
	public static String readTxt(String filePathAndName) throws IOException {
		return readTxt(filePathAndName, "UTF-8");
	}

	/**
	 * 文本文件读取
	 * 
	 * @param filePathAndName
	 *            文件
	 * @param encoding
	 *            编码
	 * 
	 * @return String [返回类型说明]
	 * @throws IOException
	 * @see [类、类#方法、类#成员]
	 */
	public static String readTxt(String filePathAndName, String encoding) throws IOException {
		File file = new File(filePathAndName);
		return FileUtils.readFileToString(file, encoding);
	}

	/**
	 * 写TXT文件
	 * 
	 * @param path
	 *            新文件路径
	 * @param filename
	 *            新文件名
	 * @param fileContent
	 *            文件内容
	 * @throws IOException
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public static void writeTxt(String path, String filename, String fileContent) throws IOException {
		writeTxt(path, filename, fileContent, "UTF-8");

	}

	/**
	 * 写TXT文件
	 * 
	 * @param path
	 *            新文件路径
	 * @param filename
	 *            新文件名
	 * @param fileContent
	 *            文件内容
	 * @param encoding
	 *            编码
	 * @throws IOException
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public static void writeTxt(String path, String filename, String fileContent, String encoding) throws IOException {
		File file = new File(path);
		if (StringUtils.isNotBlank(filename)) {
			if (!file.exists()) {
				file.mkdirs();
			}
			file = new File(path + FILESPARA + filename);
		}
		FileUtils.writeStringToFile(file, fileContent, encoding);
	}

	/**
	 * 复制文件
	 * 
	 * @param sourceDir
	 *            原文件
	 * @param targetDir
	 *            新文件
	 * @throws IOException
	 *             [参数说明]
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public static void copyDir(String sourceDir, String targetDir) throws IOException {
		FileUtils.copyDirectory(new File(sourceDir), new File(targetDir));
	}

	/**
	 * 新建文件夹
	 * 
	 * @param folderPath
	 *            文件夹
	 * 
	 * @return String [返回类型说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static void createFolder(String folderPath) {
		File myFilePath = new File(folderPath);
		if (!myFilePath.exists()) {
			myFilePath.mkdirs();
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            文件
	 * 
	 * @return boolean [返回类型说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean delFile(String filePathAndName) {
		File myDelFile = new File(filePathAndName);
		return myDelFile.delete();
	}

	/**
	 * 删除文件夹
	 * 
	 * @param folderPath
	 *            文件夹
	 * @throws IOException
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public static void delFolder(String folderPath) throws IOException {
		File myFilePath = new File(folderPath);
		FileUtils.deleteDirectory(myFilePath);
	}

	/**
	 * 删除文件夹中所有文件
	 * 
	 * @param path
	 *            指定文件夹
	 * 
	 * @return boolean [返回类型说明]
	 * @throws IOException
	 * @see [类、类#方法、类#成员]
	 */
	public static void delAllFile(String path) throws IOException {
		File myFilePath = new File(path);
		FileUtils.deleteDirectory(myFilePath);
	}

	/**
	 * 文件复制
	 * 
	 * @param oldPathFile
	 *            原文件
	 * @param newPathFile
	 *            新文件
	 * 
	 * @return String [返回类型说明]
	 * @throws IOException
	 * @see [类、类#方法、类#成员]
	 */
	public static void copyFile(String srcFile, String destFile) throws IOException {
		FileUtils.copyFile(new File(srcFile), new File(destFile));
	}

	/**
	 * 文件写入
	 * 
	 * @param _file
	 *            需要写入的FILE
	 * @param newFilePath
	 *            新文件
	 * @throws Exception
	 *             [参数说明]
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public static void writeFile(File srcfile, String newFilePath) throws Exception {
		FileUtils.copyFile(srcfile, new File(newFilePath));
	}

	/**
	 * 移动文件
	 * 
	 * @param oldPath
	 *            原文件
	 * @param newPath
	 *            新文件
	 * @throws IOException
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public static void moveFile(String srcFile, String destFile) throws IOException {
		FileUtils.moveFile(new File(srcFile), new File(destFile));
	}

	/**
	 * 移动文件夹
	 * 
	 * @param sourceDir
	 *            原文件夹
	 * @param targetDir
	 *            新文件夹
	 * @throws Exception
	 *             [参数说明]
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public static void moveFolder(String srcDir, String destDir) throws IOException {
		FileUtils.moveDirectory(new File(srcDir), new File(destDir));
	}

	/**
	 * 将HTTP资源另存为文件
	 * 
	 * @param destUrl
	 *            String
	 * @param fileName
	 *            String
	 * @throws IOException
	 *             IOException
	 */
	public static void saveToFile(String sourceUrl, String filePath, String fileName) throws IOException {
		// 建立文件
		File pathFile = new File(filePath);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		FileUtils.copyURLToFile(new URL(sourceUrl), new File(filePath + FILESPARA + fileName));
	}

	/**
	 * 将传入byte[]另存文件
	 * 
	 * @param data
	 *            文件内容
	 * @param filePath
	 *            另存文件夹
	 * @param fileName
	 *            另存文件名
	 * @throws IOException
	 *             [参数说明]
	 * 
	 * @see [类、类#方法、类#成员]
	 */
	public static void saveToFile(byte[] data, String filePath, String fileName) throws IOException {
		File pathFile = new File(filePath);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		FileUtils.writeByteArrayToFile(new File(filePath + FILESPARA + fileName), data);
	}

	/**
	 * 得到文件的扩展名
	 * 
	 * @param filename
	 *            文件
	 * 
	 * @return String [返回类型说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String getFileExt(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');
			if ((i > 0) && (i < (filename.length() - 1))) {
				return filename.substring(i + 1);
			}
		}
		return "";
	}

	/**
	 * 删掉多少分钟前没有变化的文件和目录 包含子目录下的文件(子目录不删除)
	 * 
	 * @param srcDir
	 * @param min
	 * @return
	 */
	public static int delFile(File srcDir, int min) {
		if (!srcDir.exists()) {
			return 0;
		}
		long t = System.currentTimeMillis() - (60 * 1000 * min);
		IOFileFilter fileFilter1 = new AgeFileFilter(t, true);
		Collection<File> flist = FileUtils.listFiles(srcDir, fileFilter1, DirectoryFileFilter.DIRECTORY);
		int count = 0;
		if (flist != null) {
			for (File ff : flist) {
				ff.delete();
				count++;
			}
		}
		return count;
	}

	public static List<File> find(File zippath, String findstr, String encode) throws Exception {
		List<File> outlist = new ArrayList<File>();
		File[] flist = zippath.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if (name.indexOf("0604-10100010-150519163247-") >= 0) {
					return true;
				} else {
					return false;
				}
			}
		});
		if (flist != null) {
			for (File ff : flist) {
				ZipFile zipFile = new ZipFile(ff);
				Enumeration<ZipEntry> entrys = (Enumeration<ZipEntry>) zipFile.entries();
				while (entrys.hasMoreElements()) {
					ZipEntry entry = entrys.nextElement();
					InputStream dataIn = zipFile.getInputStream(entry);
					byte[] b = ZipUtil.readStream(dataIn);
					String str = new String(b, encode);
					if (str.indexOf(findstr) >= 0) {
						outlist.add(ff);
					}
					dataIn.close();
				}
			}
		}
		return outlist;
	}

	/**
	 * 下载文件
	 * 
	 * @param response
	 *            response
	 * @param displayname
	 *            下载时候显示的文件名
	 * @param path
	 *            要下载的路径
	 * @param filename
	 *            要下载的文件名
	 */
	public static void todownfile(HttpServletResponse response, String displayname, String path, String filename) {
		if (StringUtils.isBlank(displayname)) {
			displayname = "asms file " + filename;
		}
		try {
			// displayname = new String(displayname.getBytes(), "iso-8859-1");
			displayname = URLEncoder.encode(displayname, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		response.setContentType("application/x-download;charset=UTF-8");
		if (StringUtils.isNotBlank(filename)) {
			response.setHeader("content-disposition", "attachment;filename=" + displayname + "." + getFileExt(filename));
		} else {
			response.setHeader("content-disposition", "attachment;filename=" + displayname);
		}

		response.setContentType("application/x-download");
		FileInputStream in = null;
		OutputStream out = null;
		try {
			if (StringUtils.isNotBlank(filename)) {
				in = new FileInputStream(path + "/" + filename);
			} else {
				in = new FileInputStream(path);
			}
			out = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * if (false) { Map beans = new HashMap(); beans.put("left", leftlist); XLSTransformer transformer = new XLSTransformer(); String
		 * temppathfile=System.getProperty("java.io.tmpdir")+"/"+VeDate.getNo(4)+".xls"; try { String mkfile = Cp_user_hd_report_action.class.getResource("").getPath() + "Cp_user_hd_report.xls";
		 * transformer.transformXLS(mkfile, beans, temppathfile); } catch (Exception e) { e.printStackTrace(); }
		 * 
		 * FileOperate.todownfile(response, "出票核对报表" + VeDate.getStringDateShort(), "d:", temppathfile); return null; }
		 */
	}
	/**
	 * 读取class目录下的properties文件
	 * @param filename
	 * @param key
	 * @return String [返回类型说明]
	 */
	public static String getPro(String filename, String key) {
        InputStream is = FileOperate.class.getResourceAsStream("/" + filename);
        Properties dbProps = new Properties();
        try {
            dbProps.load(is);
            return dbProps.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
	
	/**
     * 根据文件最后修改时间，删除多少分钟前没有变化的文件【递归】
     * @param path  待删除的文件目录路径
     * @param min 指定分钟数
     * @return true表示删除成功 false不成功
     */
    public boolean delAllFile(String path, int min) {
        boolean bea = false;
        File file = new File(path);
        if (!file.exists()) {
            return bea;
        }
        if (!file.isDirectory()) {
            return bea;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                if (System.currentTimeMillis() - 1000L * 60L * min > temp.lastModified()) {
                    temp.delete();
                }
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i], min);
                delFolder(path + "/" + tempList[i], min);
                bea = true;
            }
        }
        return bea;
    }
    
    /**
     * 删除文件夹【结合delAllFile方法一起使用】
     * @param folderPath 
     * @param min [参数说明]
     */
    public void delFolder(String folderPath, int min) {
        try {
            delAllFile(folderPath, min);
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
		try {

			File srcDir = new File("D:\\workspace\\vecode\\WebRoot\\img\\ca1.bmp");
			byte[] b = FileUtils.readFileToByteArray(srcDir);
			System.out.println(Encodes.encodeBase64(b).length());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
