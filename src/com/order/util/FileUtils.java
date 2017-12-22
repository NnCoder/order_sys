package com.order.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {
	/**
	 * 复制文件，并返回复制文件
	 * @param uploadFile
	 * @param toFile
	 * @throws IOException
	 */
	public static File copyFile(File uploadFile,File toFile) throws IOException{
		//查看是否有重名文件
		toFile = checkSameNameFile(toFile);
		//复制
		FileInputStream fInputStream = new FileInputStream(uploadFile);
		FileOutputStream fOutputStream = new FileOutputStream(toFile);
		byte[] buffer = new byte[1024];
		while(fInputStream.read(buffer)!=-1){
			fOutputStream.write(buffer);
			fOutputStream.flush();
		}
		//关闭输入输出流
		fInputStream.close();
		fOutputStream.close();
		
		return toFile;
	}
	
	//复制文件时不检测是否有重名文件
	public static File copyFileWithNoCheck(File uploadFile,File toFile) throws IOException{
		FileInputStream fInputStream = new FileInputStream(uploadFile);
		FileOutputStream fOutputStream = new FileOutputStream(toFile);
		byte[] buffer = new byte[1024];
		while(fInputStream.read(buffer)!=-1){
			fOutputStream.write(buffer);
			fOutputStream.flush();
		}
		//关闭输入输出流
		fInputStream.close();
		fOutputStream.close();
		
		return toFile;
	}
	
	/**
	 * 将文件名md5加密去除中文,检查是否有重名文件,并返回新的文件
	 * @return
	 */
	public static File checkSameNameFile(File file){
		//md5加密去除中文,防止中文问题导致一堆bug
		File parentFile = file.getParentFile();
		String fileStrs[] = file.getName().split("\\.");
		String md5 = "";
		for(int i=0;i<fileStrs.length-1;i++){
			md5 = md5 + MD5Util.getMD5(fileStrs[i]);
		}
		md5 = md5 + "." +fileStrs[fileStrs.length-1];
		file = new File(parentFile,md5);
		//判断文件重名问题
		int index = 0;
		while(file.exists()){
			index++;
			String newName = fileStrs[0]+index;
			for(int i=1;i<fileStrs.length;i++){
				newName = newName + "." + fileStrs[i];
			}
			file = new File(parentFile,newName);
		}
		return file;
	}
	
	public static boolean delete(File file){
		return file.delete();
	}
}
