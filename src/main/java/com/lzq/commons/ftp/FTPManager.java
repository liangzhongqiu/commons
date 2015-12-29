package com.lzq.commons.ftp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.lzq.commons.PrintLog;
import com.lzq.commons.charset.Coding;
/**
 * FTP中转器
 * @author zhongqiu
 * @date 2015年12月23日
 * @time 下午1:00:39
 */
public class FTPManager extends PrintLog{
	/**ftp服务器地址*/
	private String host;
	/**ftp服务器监听端口*/
	private Integer port;
	/**登录ftp用户名*/
	private String loginName;
	/**登录ftp用户密码*/
	private String loginPwd;
	/**FTP客户端*/
	private FTPClient ftpClient;
	/**文件类型*/
	private Integer fileType = FTPClient.BINARY_FILE_TYPE;
	/**是否已登录*/
	private boolean hasLogin = false;
	/**是否已打开连接*/
	private boolean hasConnect = false;
	/**字符集*/
	private Coding coding;
	
	public Integer getFileType() {
		return fileType;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	public boolean upload(String dir,String fileName,InputStream in){
		return extract(dir, fileName, in, null,true);
	}
	
	public boolean download(String pathname,OutputStream out){
		return extract(null,pathname,null, out,false);
	}
	private synchronized boolean extract(String dir,String fileName,InputStream in,OutputStream out,boolean upload){
		boolean success = false;
		try {
			if(init()){
				setFTPClientFileds();
				if(upload){
					ftpClient.makeDirectory(dir);
					ftpClient.changeWorkingDirectory(dir);
					success = ftpClient.storeFile(fileName, in);
				}else{
					success = ftpClient.retrieveFile(fileName,out);
				}
				logout();
			}
		} catch (IOException e) {
			log.error("FTP出错>>>",e);
		}finally{
			disconnect();
		}
		return success;
	}
	
	private void setFTPClientFileds() throws IOException {
		if(coding != null){
			ftpClient.setControlEncoding(coding.getCharsetName());
		}
		if(fileType != null){
			ftpClient.setFileType(fileType);
		}
	}
	private boolean init(){
		connect();
		login();
		return hasConnect && hasLogin;
	}
	
	public boolean disconnect(){
		try {
			if(ftpClient.isConnected()){
				ftpClient.disconnect();
			}
		} catch (IOException e) {
			log.error("关闭FTP连接出错>>>",e);
		}
		hasConnect = false;
		hasLogin = false;
		return hasConnect;
	}
	
	private boolean connect(){
		try {
			if(!isHasConnect()){
				ftpClient.connect(host, port);
				hasConnect = true;
			}
		} catch (IOException e) {
			log.error("启动FTP连接出错>>>",e);
		}
		return hasConnect;
	}
	private boolean login(){
		try {
			if(!isHasLogin()){
				hasLogin = ftpClient.login(loginName, loginPwd);
				int replyCode = ftpClient.getReplyCode();
				log.info("FTP reply code >>>"+replyCode);
				if(!FTPReply.isPositiveCompletion(replyCode)){
					disconnect();
					hasLogin = false;
				}
			}
		} catch (IOException e) {
			hasLogin = false;
			log.error("登录FTP出错>>>",e);
		}
		return hasLogin;
	}
	private boolean logout(){
		boolean logout = false;
		try {
			if(isHasLogin()){
				logout = ftpClient.logout();
				hasLogin = !logout;
			}
		} catch (IOException e) {
			log.error("FTP退出登录出错>>>",e);
		}
		return logout;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public FTPClient getFtpClient() {
		return ftpClient;
	}
	public void setFtpClient(FTPClient ftpClient) {
		this.ftpClient = ftpClient;
	}

	public boolean isHasLogin() {
		return hasLogin;
	}

	public boolean isHasConnect() {
		return hasConnect;
	}

	public Coding getCoding() {
		return coding;
	}

	public void setCoding(Coding coding) {
		this.coding = coding;
	}
	
	
	
	
}
