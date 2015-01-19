package com.xzymon.elearning.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class HashUtils {
	public static MessageDigest getSHA256Algorithm(){
		MessageDigest md = null;
		try{
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException ex){
			
		}
		if(md==null){
			System.out.println("md is null!");
		}
		return md;
	}
	
	public static String getHexStringFromBytes(byte[] bytes){
		return Hex.encodeHexString(bytes);
	}
	
	public static String getBase64StringFromBytes(byte[] bytes){
		return Base64.encodeBase64String(bytes);
	}
	
	public static String getSHA256HashAsBase64String(String stringToHash){
		MessageDigest md = getSHA256Algorithm();
		return getBase64StringFromBytes(md.digest(stringToHash.getBytes()));
	}
}
