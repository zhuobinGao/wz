package com.gzb.util;

import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ThreeDes {

    /**
     * ��ݲ������Key;
     * @param strKey
     */
    public  Key  getKey(String  strKey)
    {
        Key  key=null;
        try {
            KeyGenerator  _generator=KeyGenerator.getInstance("DES");
            _generator.init(new SecureRandom(strKey.getBytes()));
              key=_generator.generateKey();
            _generator=null;
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return key;
    }
    
    /**
     * ������string  �������룬string�������;
     * @param strMing
     * @return
     */
    public  String  getencString(String strMing,Key key)
    {
        byte[]  byteMi=null;
        byte[]  byteMing=null;
        String  strMi="";
        
        BASE64Encoder  encoder  =new BASE64Encoder();
        try {
            byteMing=strMing.getBytes("utf-8");
            byteMi=getEncCode(byteMing,key);
            strMi=encoder.encode(byteMi);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally
        {
            encoder=null;
            byteMi=null;
            byteMing=null;
        }
        return  strMi;
    }
    
    /**
     * ������string ��������,String �������;
     * @param strMi
     * @return
     */
    public  String  getDecString(String strMi,Key key)
    {
        BASE64Decoder  base64Decoder=new BASE64Decoder();
        byte[] byteMing=null;
        byte[] byteMi=null;
        String strMing="";
        try {
            byteMi=base64Decoder.decodeBuffer(strMi);
            byteMing=getDecCode(byteMi,key);
            strMing=new String(byteMing,"utf-8");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally
        {
            base64Decoder=null;
            byteMing=null;
            byteMi=null;
        }
        return strMing;
        
    }
    /**
     * ������byte[] �������룬byte[] �������;
     * @param byts
     * @return
     */
    private  byte[]  getEncCode(byte[] byts,Key key)
    {
        byte[]  byteFina=null;
        Cipher  cipher;
        try {
            cipher=Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byteFina=cipher.doFinal(byts);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally
        {
            cipher=null;
        }
        return  byteFina; 
    }
    /**
     * ������byte[] �������룬byte[] �������;
     * @param bytd
     * @return
     */
    private  byte[]  getDecCode(byte[] bytd,Key  key)
    {
        byte[] byteFina=null;
        Cipher cipher=null;
        try {
            cipher=Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byteFina=cipher.doFinal(bytd);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally
        {
            cipher=null;
        }
        return byteFina;
    }
  
    
    public static  void  main(String[] args)
    {
        ThreeDes  td=new ThreeDes();
        Key k=td.getKey("testkey4545212144");
        System.out.println(""+k);
        
        String  encyStr=td.getencString("it2259649",k);
        System.out.println("it2259649:"+encyStr);
        
        String  decyStr=td.getDecString(encyStr,k);
        System.out.println("decode:"+decyStr);
//        System.out.println("==============================");
//        System.out.println(td.getDecString("vPy0tC+IYcQbjZmxrjizVfzEAg20GyIUwQW7c3e5F7waJF+v/Yr0Sg==",k));
//        System.out.println(td.getDecString("L0kNDAKwV2w=",k));
//        System.out.println(td.getDecString("vPy0tC+IYcQbjZmxrjizVfzEAg20GyIURdqoaDZOc/izee6NNZx/+w==",k));
//        System.out.println(td.getDecString("",k));
//        
        
    }
}