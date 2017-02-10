package com.hadoop.Mapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.Counters.Counter;
import org.apache.hadoop.mapreduce.Mapper;

import com.hadoop.parseLog.LogParser;

public class MyMapper extends Mapper<Object, Text, Text, IntWritable> {
	LogParser logParser = new LogParser();
	Text outputValue = new Text();
	private final static IntWritable one = new IntWritable(1);

	String userid = "";
	String ip = "";
	String day = "";// 保存时间
	String month = "";
	String year = "";
	int index = 0;
	int monindex=0;
	int yearindex=0;
	protected void map(Object key, Text value, Context context)
			throws java.io.IOException, InterruptedException {
		Map<String, String> SaveIdMmap = new HashMap<String, String>();
		final String[] parsed = logParser.parse(value.toString());
		
		
		// ip, time, url, status, traffic//流量
		// step1.过滤掉不合法的请求
		if (parsed[2].startsWith("GET /static/")) {
			return;
		} else if (parsed[2].contains("/robots.txt HTTP/1.1")) {
			return;
		} else if (parsed[2].contains("/error/priv_error.htm")) {
			return;
		} else if (parsed[2].contains(".gif") || parsed[2].contains(".jsp")
				|| parsed[2].contains(".js") || parsed[2].contains(".swf")
				|| parsed[2].contains(".jpg") || parsed[2].contains(".bmp")
				|| parsed[2].contains(".css") || parsed[2].contains(".ico")
				|| parsed[2].contains(".png")) {
			return;
		} else if (!parsed[3].equals("200")) {
			return;
		}

		// 7.对应的userid的ip 都加上userid
		if (parsed[0].equals(ip)) {

			outputValue.set(userid + "\t" + parsed[0] + "\t" + parsed[1] + "\t"
					+ parsed[2]);
			context.write(outputValue, one);

		}

		// 如果下一条记录的时间相等就表示是在当天，并且还要是每一个学习者
		// 保证是同一个
		// 并且包含userid，作为登录次数的统计依据

		
		// 如果当前的数据存在userid，那么就保存ip
		if (parsed[2].contains("userId")) {
			try {
				ip = parsed[0];
				// if(parsed[2].indexOf("&")==-1){
				// userid=parsed[2].substring(parsed[2].indexOf("userId")+7,parsed[2].length()-" HTTP/1.1".length());
				// }else{
				// userid=parsed[2].substring(parsed[2].indexOf("userId")+7,parsed[2].indexOf("&"));
				// }
				
				
				day = parsed[1].substring(0, 8);// 保存第一次登录的时间，时间格式为年月日
				month = parsed[1].substring(0, 6);// 月
				year = parsed[1].substring(0, 4);// 年
				userid = parsed[2].substring(parsed[2].indexOf("userId") + 7,
						parsed[2].indexOf("&"));
				outputValue.set(userid + "\t" + parsed[0] + "\t" + parsed[1]
						+ "\t" + parsed[2]);
			
				context.write(outputValue, one);
			} catch (Exception e) {
				System.out.println(parsed[0] + "\t" + parsed[1] + "\t"
						+ parsed[2]);
			}

		}

	}

	
	
}
