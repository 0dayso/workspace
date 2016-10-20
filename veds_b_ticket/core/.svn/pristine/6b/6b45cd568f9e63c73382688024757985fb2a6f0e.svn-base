package org.vetech.core.modules.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * 实现sftp传输
 * 
 * ssh-keygen -t rsa 生成秘钥对
 * 
 * 
 *  cat id_rsa.pub >> ~/.ssh/authorized_keys
 * 
 * @author zhanglei
 *
 */
public class Sftp {
	private String host;// sftp服务器ip 必须
	private String username;// 用户名 必须
	private String password;// 密码 有 privateKey 可以为空
	private File privateKeyFile;// 密钥文件路径 ssh-keygen -t rsa 生成密码，如果没有密码则下面可以不输入
	private byte[] privateKey;
	private String passphrase;// 密钥口令
	private int port = 22;// 默认的sftp端口号是22

	private ChannelSftp sftp;

	public Sftp(String host, String username, String password, File privateKeyFile, String passphrase, int port) {
		this.host = host;
		this.username = username;
		this.password = password;
		this.privateKeyFile = privateKeyFile;
		this.passphrase = passphrase;
		this.port = port;
	}

	public Sftp(String host, String username, String password, byte[] privateKey, String passphrase, int port) {
		this.host = host;
		this.username = username;
		this.password = password;
		this.privateKey = privateKey;
		this.passphrase = passphrase;
		this.port = port;

	}

	/**
	 * 获取连接
	 * 
	 * @return channel
	 */
	public void connect() throws Exception {
		JSch jsch = new JSch();
		Channel channel = null;
		try {
			if (privateKeyFile != null) {
				if (privateKeyFile.exists()) {
					if (StringUtils.isNotBlank(passphrase)) {
						jsch.addIdentity(privateKeyFile.getPath(), passphrase);
					} else {
						jsch.addIdentity(privateKeyFile.getPath());
					}
				}
			} else if (privateKey != null && privateKey.length > 0) {
				if (StringUtils.isNotBlank(passphrase)) {
					jsch.addIdentity("", privateKey, null, passphrase.getBytes());
				} else {
					jsch.addIdentity("", privateKey, null, null);
				}
			}

			Session session = jsch.getSession(username, host, port);
			if (password != null && !"".equals(password)) {
				session.setPassword(password);
			}
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");// do not verify host key
			session.setConfig(sshConfig);
			// session.setTimeout(timeout);
			session.setServerAliveInterval(92000);
			session.connect();
			// 参数sftp指明要打开的连接是sftp连接
			channel = session.openChannel("sftp");
			channel.connect();
		} catch (JSchException e) {
			e.printStackTrace();
			throw e;
		}
		sftp = (ChannelSftp) channel;
	}


	/**
	 * 上传文件
	 * 
	 * @param directory
	 *            上传的目录
	 * @param uploadFile
	 *            要上传的文件
	 * @param sftp
	 */
	public void upload(String directory, String uploadFile) throws Exception {
		try {
			sftp.cd(directory);
			File file = new File(uploadFile);
			sftp.put(new FileInputStream(file), file.getName());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件
	 * @param saveFile
	 *            存在本地的路径
	 * @param sftp
	 */
	public void download(String directory, String downloadFile, String saveFile) throws Exception {
		try {
			sftp.cd(directory);
			sftp.get(downloadFile, saveFile);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param directory
	 *            要删除文件所在目录
	 * @param deleteFile
	 *            要删除的文件
	 * @param sftp
	 */
	public void delete(String directory, String deleteFile) throws Exception {
		try {
			sftp.cd(directory);
			sftp.rm(deleteFile);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void disconnected() {
		if (sftp != null) {
			try {
				sftp.getSession().disconnect();
				sftp.disconnect();
			} catch (JSchException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		File f = new File("d:/temp/rsa/id_rsa");

		String strkey = FileUtils.readFileToString(f);

		Sftp s = new Sftp("192.168.1.71", "root", "", strkey.getBytes("UTF-8"), "", 22);
		try {
			s.connect();

			s.upload("/home/test", "d:/temp/pay2016-05-12.log");
			s.upload("/home/test", "d:/temp/国际票ER项系统5-2011.xls");
			s.download("/home/test", "20150703_000001.txt", "d:/a.txt");

		} finally {
			s.disconnected();
		}
	}
}
