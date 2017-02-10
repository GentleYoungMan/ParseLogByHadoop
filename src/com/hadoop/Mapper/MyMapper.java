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
	String day = "";// ����ʱ��
	String month = "";
	String year = "";
	int index = 0;
	int monindex=0;
	int yearindex=0;
	protected void map(Object key, Text value, Context context)
			throws java.io.IOException, InterruptedException {
		Map<String, String> SaveIdMmap = new HashMap<String, String>();
		final String[] parsed = logParser.parse(value.toString());
		
		
		// ip, time, url, status, traffic//����
		// step1.���˵����Ϸ�������
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

		// 7.��Ӧ��userid��ip ������userid
		if (parsed[0].equals(ip)) {

			outputValue.set(userid + "\t" + parsed[0] + "\t" + parsed[1] + "\t"
					+ parsed[2]);
			context.write(outputValue, one);

		}

		// �����һ����¼��ʱ����Ⱦͱ�ʾ���ڵ��죬���һ�Ҫ��ÿһ��ѧϰ��
		// ��֤��ͬһ��
		// ���Ұ���userid����Ϊ��¼������ͳ������

		
		// �����ǰ�����ݴ���userid����ô�ͱ���ip
		if (parsed[2].contains("userId")) {
			try {
				ip = parsed[0];
				// if(parsed[2].indexOf("&")==-1){
				// userid=parsed[2].substring(parsed[2].indexOf("userId")+7,parsed[2].length()-" HTTP/1.1".length());
				// }else{
				// userid=parsed[2].substring(parsed[2].indexOf("userId")+7,parsed[2].indexOf("&"));
				// }
				
				
				day = parsed[1].substring(0, 8);// �����һ�ε�¼��ʱ�䣬ʱ���ʽΪ������
				month = parsed[1].substring(0, 6);// ��
				year = parsed[1].substring(0, 4);// ��
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
