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
		//����ͳ����һ�λỰ֮���������ļ�¼
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


		
		
		// ͳ�Ƶ�¼����
		try {
			if (value.toString().contains("userId")) {
				
				
				
				
				
					userid = value.toString().substring(
							value.toString().indexOf("userId") + 7,
							value.toString().indexOf("&"));
					firsttimes = value.toString().substring(
							value.toString().indexOf(" +0800") - 14,
							value.toString().indexOf(" +0800"));
					 daytimes=firsttimes.substring(0, 10);//ÿ��ĻỰ����
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
			
				 //һ�����ڵ�ʱ��
				
				
				// montimes=firsttimes.substring(0, 6);//ÿ�µĻỰ����
				// System.out.println(userid);s
				//��Ҫ���������ж���һ���ܵ�����
				 //���õ�ǰʱ���������Ϳ��Ի�ã������ܼ�һ�Σ�
				
				outputValue.set(daytimes);
				//outputValue.set(userid);
				context.write(outputValue, one);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
			
	}
}
