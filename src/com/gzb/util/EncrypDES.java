package com.gzb.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class EncrypDES {
	
	//KeyGenerator 提供对称密钥生成器的功能，支持各种算法
	private KeyGenerator keygen;
	//SecretKey 负责保存对称密钥
	private SecretKey deskey;
	//Cipher负责完成加密或解密工作
	private Cipher c;
	//该字节数组负责保存加密的结果
	private byte[] cipherByte;
	
	public EncrypDES() throws NoSuchAlgorithmException, NoSuchPaddingException{
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		//实例化支持DES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)
		keygen = KeyGenerator.getInstance("DESede");
		//生成密钥
		deskey = keygen.generateKey();
		//生成Cipher对象,指定其支持的DES算法
		c = Cipher.getInstance("DESede");
	}
	
	/**
	 * 对字符串加密
	 * 
	 * @param str
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public byte[] Encrytor(String str) throws InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		// 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
		c.init(Cipher.ENCRYPT_MODE, deskey);
		byte[] src = str.getBytes();
		// 加密，结果保存进cipherByte
		cipherByte = c.doFinal(src);
		return cipherByte;
	}

	/**
	 * 对字符串解密
	 * 
	 * @param buff
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public byte[] Decryptor(byte[] buff) throws InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {
		// 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示加密模式
		c.init(Cipher.DECRYPT_MODE, deskey);
		cipherByte = c.doFinal(buff);
		return cipherByte;
	}

	/**
	 * @param args
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidKeyException 
	 */
	public static void main(String[] args) throws Exception {
		EncrypDES de1 = new EncrypDES();

		String msg ="郭XX-搞笑相声全集";
		byte[] encontent = de1.Encrytor(msg);
		byte[] decontent = de1.Decryptor(encontent);
		
		for(byte b:encontent){
			System.out.print(b+",");
		}
		System.out.println();
		for(byte b:new String(encontent).getBytes("GBK")){
			System.out.print(b+",");
		}
		System.out.println();
		System.out.println(encontent);
		System.out.println("明文是:" + msg);
		System.out.println("加密后:" + new String(encontent));
		System.out.println("解密后:" + new String(decontent));
		new String("鱦?茷?y．?葼;╨n冗j");
	}

	
	public static String getenCord(String str){
		StringBuffer sbBuffer = new StringBuffer();
		for(byte b:str.getBytes()){
			sbBuffer.append(b);
		}
		return sbBuffer.toString();
	}
	
	public static String getdecCord(String str){
//		EncrypDES de1;
//		byte[] encontent = null;
//		try {
//			de1 = new EncrypDES();
//			encontent = de1.Decryptor(str.getBytes());
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
//		return new String(encontent);
		
		StringBuffer sbBuffer = new StringBuffer();
		for(byte b:str.getBytes()){
			sbBuffer.append(b);
		}
		return sbBuffer.toString();
	}
	
	
}
