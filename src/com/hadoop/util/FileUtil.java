package com.hadoop.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 文件操作�?
 * 
 * @author dai_guohong 2011-1-7
 * 
 */
public class FileUtil {
	/**
	 * 
	 * @param is
	 *            输入�?
	 * @param os
	 *            输出�?
	 * @param charSet
	 *            输出文件字符集编�?默认�?UTF-8
	 * @throws Exception
	 */
	public static void readAndWrite(InputStream is, OutputStream os,
			String charSet) throws IOException {
		if (null == charSet) {
			charSet = "UTF-8";
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(is,
				charSet));
		PrintWriter writer = new PrintWriter(
				new OutputStreamWriter(os, charSet));
		String inLine = null;
		while ((inLine = reader.readLine()) != null) {
			writer.println(inLine);
		}
		writer.flush();
		reader.close();
		writer.close();
	}

	/**
	 * 
	 * @param is
	 *            输入�?
	 * @param outPath
	 *            输出路径
	 * @param charSet
	 *            输出文件字符集编�?默认�?UTF-8
	 * @throws IOException
	 * @throws Exception
	 */
	public static void readAndWrite(InputStream is, String outPath,
			String charSet) throws IOException {
		if (null == charSet) {
			charSet = "UTF-8";
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(is,
				charSet));
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(
				new FileOutputStream(outPath), charSet));
		String inLine = null;
		while ((inLine = reader.readLine()) != null) {
			writer.println(inLine);
		}
		writer.flush();
		reader.close();
		writer.close();
	}

	/**
	 * 
	 * @param inPath
	 *            输入路径
	 * @param os
	 *            输出�?
	 * @param charSet
	 *            输出文件字符集编�?默认�?UTF-8
	 * @throws Exception
	 */
	public static void readAndWrite(String inPath, OutputStream os,
			String charSet) throws IOException {
		if (null == charSet) {
			charSet = "UTF-8";
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(inPath), charSet));
		PrintWriter writer = new PrintWriter(
				new OutputStreamWriter(os, charSet));
		String inLine = null;
		while ((inLine = reader.readLine()) != null) {
			writer.println(inLine);
		}
		writer.flush();
		reader.close();
		writer.close();
	}

	/**
	 * 
	 * @param inPath
	 *            读取文件路径
	 * @param outPath
	 *            输出文件路径
	 * @param charSet
	 *            输出文件字符集编�?默认�?UTF-8
	 * @throws Exception
	 */
	public static void readAndWrite(String inPath, String outPath,
			String charSet) throws IOException {
		if (null == charSet) {
			charSet = "UTF-8";
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(inPath), charSet));
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(
				new FileOutputStream(outPath), charSet));
		String inLine = null;
		while ((inLine = reader.readLine()) != null) {
			writer.println(inLine);
		}
		writer.flush();
		reader.close();
		writer.close();
	}
	
	/**
	 * 
	 * @param inFile
	 *            即将读的文件
	 * @param os
	 *            输出�?
	 * @param charSet
	 *            输出文件字符集编�?默认�?UTF-8
	 * @throws Exception
	 */
	public static void readAndWrite(File inFile, OutputStream os, String charSet)
			throws IOException {
		if (null == charSet) {
			charSet = "UTF-8";
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(inFile), charSet));
		PrintWriter writer = new PrintWriter(
				new OutputStreamWriter(os, charSet));
		String inLine = null;
		while ((inLine = reader.readLine()) != null) {
			writer.println(inLine);
		}
		writer.flush();
		reader.close();
		writer.close();
	}

	/**
	 * 
	 * @param is
	 *            输入�?
	 * @param outFile
	 *            输出文件
	 * @param charSet
	 *            输出文件字符集编�?默认�?UTF-8
	 * @throws Exception
	 */
	public static void readAndWrite(InputStream is, File outFile, String charSet)
			throws IOException {
		if (null == charSet) {
			charSet = "UTF-8";
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(is,
				charSet));
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(
				new FileOutputStream(outFile), charSet));
		String inLine = null;
		while ((inLine = reader.readLine()) != null) {
			writer.println(inLine);
		}
		writer.flush();
		reader.close();
		writer.close();
	}

	/**
	 * 
	 * @param inFile
	 *            读取文件
	 * @param outFile
	 *            输出文件
	 * @param charSet
	 *            输出文件字符集编�?默认�?UTF-8
	 * @throws Exception
	 */
	public static void readAndWrite(File inFile, File outFile, String charSet)
			throws IOException {
		if (null == charSet) {
			charSet = "UTF-8";
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(inFile), charSet));
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(
				new FileOutputStream(outFile), charSet));
		String inLine = null;
		while ((inLine = reader.readLine()) != null) {
			writer.println(inLine);
		}
		writer.flush();
		reader.close();
		writer.close();
	}

	/**
	 * 
	 * @param inFile
	 *            读取文件
	 * @param outPath
	 *            输出路径
	 * @param charSet
	 *            输出文件字符集编�?默认�?UTF-8
	 * @throws Exception
	 */
	public static void readAndWrite(File inFile, String outPath, String charSet)
			throws IOException {
		if (null == charSet) {
			charSet = "UTF-8";
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(inFile), charSet));
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(
				new FileOutputStream(outPath), charSet));
		String inLine = null;
		while ((inLine = reader.readLine()) != null) {
			writer.println(inLine);
		}
		writer.flush();
		reader.close();
		writer.close();
	}
	
	/**
	 * 
	 * @param inFile
	 *            读取文件
	 * @param outPath
	 *            输出路径
	 * @param charSet
	 *            输出文件字符集编�?默认�?UTF-8
	 * @throws Exception
	 */
	public static void readAndWriteByStream(File inFile, String outPath)
			throws IOException {
		

		BufferedInputStream br = new BufferedInputStream(
				new FileInputStream(inFile));
		BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(outPath));
		byte[] buf = new byte[1024];
		int len = 0;
		while ((len = br.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		/*while ((inLine = reader.readLine()) != null) {
			writer.println(inLine);
		}*/
		out.flush();
		br.close();
		out.close();
	}

	/**
	 * 
	 * @param inPath
	 *            读取路径
	 * @param outFile
	 *            输出文件
	 * @param charSet
	 *            输出文件字符集编�?默认�?UTF-8
	 * @throws Exception
	 */
	public static void readAndWrite(String inPath, File outFile, String charSet)
			throws IOException {
		if (null == charSet) {
			charSet = "UTF-8";
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(inPath), charSet));
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(
				new FileOutputStream(outFile), charSet));
		String inLine = null;
		while ((inLine = reader.readLine()) != null) {
			writer.println(inLine);
		}
		writer.flush();
		reader.close();
		writer.close();
	}

	/**
	 * 导出文件
	 */
	public static void doload(File f, OutputStream out) throws IOException {
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
		byte[] buf = new byte[1024];
		int len = 0;
		while ((len = br.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		br.close();
		out.close();
	}

	/**
	 * 导入文件
	 * 
	 * @param f
	 *            :上传的文�?path:导入文件放置的位�?
	 * 
	 */
	public static void upload(File f, String path) throws IOException {
		FileInputStream fis = new FileInputStream(f);
		FileOutputStream fos = new FileOutputStream(new File(path));
		BufferedInputStream bis = new BufferedInputStream(fis);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		byte[] buf = new byte[5];
		int len = -1;
		// 导入文件
		while ((len = bis.read(buf)) != -1) {
			bos.write(buf, 0, len);
		}

		bos.flush();
		bis.close();
		bos.close();
		fis.close();
		fos.close();
	}

	/**
	 * 证书文件上传
	 * 
	 * @param f
	 * @param path
	 * @author zb
	 */
	public static void importCerFile(File f, String path) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(f);
			File file=new File(path);
			FileUtil.ifFileNotExistCreate(file);
			fos = new FileOutputStream(file);
			byte[] buf = new byte[fis.available()];
			int len = -1;
			// 导入文件
			while ((len = fis.read(buf)) != -1) {
				fos.write(buf, 0, len);
//				new BASE64Encoder().encode(buf, fos);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * 导入根证书文�?
	 * 
	 * @param f
	 * @param path
	 * @param type
	 */
	public static void importLevelCerFile(File f, String path, String type) {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new FileReader(f));
            File file=new File(path);
			if(!file.exists()){
				file.createNewFile();
			}
			if (type.equals("root")) {
				bw = new BufferedWriter(new FileWriter(file));
			} else {
				bw = new BufferedWriter(new FileWriter(file, true));
			}
			String readStr = null;
			while ((readStr = br.readLine()) != null) {
				bw.write(readStr);
				bw.newLine();
			}
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 * @param file
	 * @return
	 */
	public static List<String> readFileToList(File file){
		List<String> list = new ArrayList<String>();
		BufferedReader br = null;
		try{
			
			
			br = new BufferedReader(new FileReader(file));
			String readStr = null;
			while((readStr=br.readLine())!=null){
			
				list.add(readStr);
				
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 判断文件是否存在，如不存在则创建
	 * @param file
	 */
	public static void ifFileNotExistCreate(File file){
		if(!file.exists()){
			File parentFile = file.getParentFile();
			if(parentFile != null){
				if(!parentFile.exists()){
					parentFile.mkdirs();
				}
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * 对文件夹中的文件按名称进行排�?
	 * @param file
	 */
	public static  Object[] GetFileName(File file){
		File[] files = file.listFiles();
		
		List<String> filelist=new ArrayList<String>();
		//获得�?��文件的文件名
		for(File f:files){
			filelist.add(f.getName());
		}
		Object obj[]=filelist.toArray();
		//进行冒泡排序
		String filename="";
		for(int i=1;i<obj.length;i++){
			for(int j=0;j<obj.length-i;j++){
				//获得当前文件名的数字
				if(Integer.valueOf(obj[j].toString().substring(4, obj[j].toString().lastIndexOf(".")))>
				Integer.valueOf(obj[j+1].toString().substring(4, obj[j+1].toString().lastIndexOf(".")))){
					filename=obj[j].toString();
					obj[j]=obj[j+1];
					obj[j+1]=filename;
				}
				
			}
		}
		
		return obj;
	}
	
	public static void main(String[] args){
		FileUtil.ifFileNotExistCreate(new File("e:/www/"));
	}
}
