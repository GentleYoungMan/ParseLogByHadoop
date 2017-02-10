package com.hadoop.Mapper;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

/**
 * @author WZQ 得出每个学习者每一次会话的跳转路径
 */
public class CountTZurlMaper extends
		Mapper<LongWritable, Text, Text, IntWritable> {

	Text outputValue = new Text();
	private final static IntWritable one = new IntWritable(1);
	String values = "";
	String url = "";
	String userid = "";
	String time = "";

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		values=value.toString();
		
		if(!url.equals("")){
			String userids="";
			//每一个用户每一次的访问页面统计
			try {
				userids=values.substring(0,11);
			} catch (Exception e) {
				// TODO: handle exception
				userids=values.substring(0,8);
			}
			if(values.contains("GET /")){
				if(values.indexOf("?")!=-1){
					
					if(!url.equals(values.substring(values.indexOf("GET /"), values.indexOf("?")))){
						outputValue.set(url);
						
						context.write(outputValue, one);
					}
				}else {
					try{
						if(!url.equals(values.substring(values.indexOf("GET /"), values.indexOf(" HTTP")))){
							outputValue.set(url);
							context.write(outputValue, one);
						}
					}catch (Exception e){
						System.out.println(values);
					}
					
				}
				
			}else if(values.contains("POST /")){
				if(values.indexOf("?")!=-1){
					if(!url.equals(values.substring(values.indexOf("POST /"), values.indexOf("?")))&&userid.equals(userids)){
						outputValue.set(url);
						context.write(outputValue, one);
					}
				}else {
					if(!url.equals(values.substring(values.indexOf("POST /"), values.indexOf(" HTTP")))&&userid.equals(userids)){
						outputValue.set(url);
						context.write(outputValue, one);
					}
				}
			}
			
		}
			try {
				userid=values.substring(0,11);
			} catch (Exception e) {
				userid=values.substring(0,8);
			}
				// 获得url,获得每个学习者
				if(values.contains("GET /")){
					if(values.indexOf("?")!=-1){
						url=values.substring(values.indexOf("GET /"), values.indexOf("?"));
						time=values.substring(values.indexOf(" +0800")-14, values.indexOf(" +0800")-8);//每天
						
						//outputValue.set(userid+"\t"+url);
						
					}else {
						url=values.substring(values.indexOf	("GET /"), values.indexOf(" HTTP"));
						time=values.substring(values.indexOf(" +0800")-14, values.indexOf(" +0800")-8);//每天
						
						//outputValue.set(userid+"\t"+url);
						
					}
				}else if(values.contains("POST /")){
					if(values.indexOf("?")!=-1){
						url=values.substring(values.indexOf("POST /"), values.indexOf("?"));
						time=values.substring(values.indexOf(" +0800")-14, values.indexOf(" +0800")-8);//每天
						
						//outputValue.set(userid+"\t"+url);
						
					}else {
						url=values.substring(values.indexOf("POST /"), values.indexOf(" HTTP"));
						
						time=values.substring(values.indexOf(" +0800")-14, values.indexOf(" +0800")-8);//每天
						//outputValue.set(userid+"\t"+url);
						
					}
				}
			
		
			
		
		}

}
