package com.hadoop.Mapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.hadoop.parseLog.LogParser;

public class userIdCount extends Mapper<LongWritable, Text, Text, IntWritable> {
	private final static IntWritable one = new IntWritable(1);
	LogParser logParser = new LogParser();
	Text outputValue = new Text();
	String userid = "";
	String year = "";
	String month = "";
	String day = "";
	String firsttimes = "";
	String firsttimess = "";
	String lasttimes = "";
	String daytimes="";
	String montimes="";
	String oneweek="";
	int week = 0;
	Calendar c = Calendar.getInstance();
	Date date;
	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		//这是统计在一次会话之中所产生的记录
//		try {
//			if (userid != "") {
//				if (value.toString().contains(userid)) {
//
//				
//
//					outputValue.set(userid + "\t" + value.toString().substring(
//							value.toString().indexOf(" +0800") - 14,
//							value.toString().indexOf(" +0800")));
//					context.write(outputValue, one);
//				}
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}


		
		
		// 统计登录次数
		try {
			if (value.toString().contains("userId")) {
				
				
				
				
				
					userid = value.toString().substring(
							value.toString().indexOf("userId") + 7,
							value.toString().indexOf("&"));
					firsttimes = value.toString().substring(
							value.toString().indexOf(" +0800") - 14,
							value.toString().indexOf(" +0800"));
					 daytimes=firsttimes.substring(0, 10);//每天的会话次数
//					 SimpleDateFormat sim=new SimpleDateFormat("yyyyMMdd");
//						
//						date=sim.parse(daytimes);
//						c.setTime(date);
//						c.add(c.DATE, 7);
//						date=c.getTime();
//						oneweek=new SimpleDateFormat("yyyyMMdd").format(date);	
						//outputValue.set(userid);
						//outputValue.set(userid);
						//context.write(outputValue, one);
			
				 //一个星期的时间
				
				
				// montimes=firsttimes.substring(0, 6);//每月的会话次数
				// System.out.println(userid);s
				//主要问题在于判断下一个周的区间
				 //利用当前时间加上七天就可以获得，且智能加一次，
				
				outputValue.set(daytimes);
				//outputValue.set(userid);
				context.write(outputValue, one);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
			
	}
}
