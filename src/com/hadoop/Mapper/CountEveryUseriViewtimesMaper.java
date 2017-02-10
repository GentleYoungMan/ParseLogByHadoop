package com.hadoop.Mapper;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

/**
 * @author WZQ
 *ÿ��ѧϰ��һ���ڷ���ÿ��ҳ��Ĵ���
 */
public class CountEveryUseriViewtimesMaper extends Mapper<LongWritable, Text, Text, IntWritable>{

	Text outputValue = new Text();
	private final static IntWritable one = new IntWritable(1);
	String values="";
	String url="";
	String userid="";
	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		values=value.toString();
		
		if(!url.equals("")){
			if(values.contains("GET /")){
				if(values.indexOf("?")!=-1){
					//ÿһ���û�ÿһ�εķ���ҳ��ͳ��
					if(url.equals(values.substring(values.indexOf("GET /"), values.indexOf("?")))&&values.contains(userid)){
						outputValue.set(userid+"\t"+url);
						context.write(outputValue, one);
					}
				}else {
					try{
						if(url.equals(values.substring(values.indexOf("GET /"), values.indexOf(" HTTP")))&&values.contains(userid)){
							outputValue.set(userid+"\t"+url);
							context.write(outputValue, one);
						}
					}catch (Exception e){
						System.out.println(values);
					}
					
				}
				
			}else if(values.contains("POST /")){
				if(values.indexOf("?")!=-1){
					if(url.equals(values.substring(values.indexOf("POST /"), values.indexOf("?")))&&values.contains(userid)){
						outputValue.set(userid+"\t"+url);
						context.write(outputValue, one);
					}
				}else {
					if(url.equals(values.substring(values.indexOf("POST /"), values.indexOf(" HTTP")))&&values.contains(userid)){
						outputValue.set(userid+"\t"+url);
						context.write(outputValue, one);
					}
				}
			}
			
		}
			try {
				// ���url,���ÿ��ѧϰ��
				if(values.contains("GET /")){
					if(values.indexOf("?")!=-1){
						url=values.substring(values.indexOf("GET /"), values.indexOf("?"));
						
						userid=values.substring(0,11);
						outputValue.set(userid+"\t"+url);
						context.write(outputValue, one);
					}else {
						url=values.substring(values.indexOf	("GET /"), values.indexOf(" HTTP"));
						userid=values.substring(0,11);
						outputValue.set(userid+"\t"+url);
						context.write(outputValue, one);
					}
				}else if(values.contains("POST /")){
					if(values.indexOf("?")!=-1){
						url=values.substring(values.indexOf("POST /"), values.indexOf("?"));
						userid=values.substring(0,11);
						outputValue.set(userid+"\t"+url);
						context.write(outputValue, one);
					}else {
						url=values.substring(values.indexOf("POST /"), values.indexOf(" HTTP"));
						userid=values.substring(0,11);
						outputValue.set(userid+"\t"+url);
						context.write(outputValue, one);
					}
				}
			} catch (Exception e) {
				userid=values.substring(0,8);
			}
		
			
		
		}
		

}
