package com.order.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	public static String getMD5(String message){
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] input = message.getBytes();
			byte[] output = messageDigest.digest(input);
			String hexString = "";
			for(byte b:output){
				int temp = b&255;
				if(temp<16&&temp>=0){
					hexString = hexString + "0" +Integer.toHexString(temp);
				}else{
					hexString = hexString + Integer.toHexString(temp);
				}
			}
			return hexString;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
