package com.hadoop.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

public class HdfsOperation {

	/**
	 * @throws IOException
	 * 上传文件到HDFS
	 */
	public static void uploadFile() throws IOException{
		String localsrc="E:/accesslogs/log";
		String dst="hdfs://localhost:9000/user/test";
		InputStream in = new BufferedInputStream(new FileInputStream(localsrc));
		Configuration conf=new Configuration();
		FileSystem fs=FileSystem.get(URI.create(dst), conf);
		OutputStream out=fs.create(new Path(dst),new Progressable(){
			  public void progress() {
				    System.out.print(".");
				   }
		});
		IOUtils.copyBytes(in, out, 4096, true);
	}

	/**
	 * @throws IOException 
	 * 从hdfs上读取文件
	 */
	public static void readFromHdfs() throws IOException{
		String dst = "hdfs://localhost:9000/user/test"; 
		  Configuration conf = new Configuration(); 
		  FileSystem fs = FileSystem.get(URI.create(dst), conf);
		  FSDataInputStream hdfsInStream = fs.open(new Path(dst));
		  OutputStream out = new FileOutputStream("E:/hadooplogs.txt/");
		  byte[] ioBuffer = new byte[1024];
		  int readLen = hdfsInStream.read(ioBuffer);
		  while(-1 != readLen){
		   out.write(ioBuffer, 0, readLen); 
		   readLen = hdfsInStream.read(ioBuffer);
		  }
		  out.close();
		  hdfsInStream.close();
		  fs.close();
	}
	
	public static void deleteFromHdfs() throws IOException{
		String dst = "hdfs://localhost:9000/user/test"; 
		  Configuration conf = new Configuration(); 
		  FileSystem fs = FileSystem.get(URI.create(dst), conf);
		  fs.deleteOnExit(new Path(dst));
		  fs.close();
	}
	public static void main(String[] args) throws IOException {
		uploadFile();
		//readFromHdfs();
		//deleteFromHdfs();
	}
}
