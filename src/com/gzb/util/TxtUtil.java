package com.gzb.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TxtUtil {

	
	private static final String fileName = "D://log.txt";
	
	public static void log(String mes){
		
		FileWriter fWriter = null;
		BufferedWriter bwWriter = null;
		
		try{
			bwWriter = new BufferedWriter(new FileWriter(fileName,true));
			bwWriter.write(mes+"\n");
			
		}catch(Exception e){
			e.toString();
		}finally{
			if(bwWriter!=null){
				try {
					bwWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(fWriter!=null){
				try {
					fWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
		
	}
	
	public static void main(String[] args){
		 Runtime runtime = Runtime.getRuntime();
	        System.out.println(runtime.totalMemory());
	        System.out.println(runtime.freeMemory());
	}
	
	
}
