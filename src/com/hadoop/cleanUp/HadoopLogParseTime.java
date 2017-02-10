package com.hadoop.cleanUp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hadoop.util.FileUtil;

public class HadoopLogParseTime {

	public static void main(String[] args) throws ParseException, IOException {
		SimpleDateFormat sim = new SimpleDateFormat("HH:mm:ss.mmmm");
		//File file = new File("E:\\parsedata\\yearout.txt");
//		File file = new File("C:\\Users\\80584_000\\Desktop\\Data\\ÿ��ĵ�¼����ͳ��.txt");
//		File file2= new File("C:\\Users\\80584_000\\Desktop\\Data\\ÿ������ʱ��ͳ��.txt");
		//File file = new File("C:\\Users\\80584_000\\Desktop\\Data\\ÿ�����ߴ���ͳ��.txt");
		//File file2= new File("C:\\Users\\80584_000\\Desktop\\Data\\ÿ������ʱ��ͳ��.txt");
		//File file = new File("C:\\Users\\80584_000\\Desktop\\Data\\monthdata.txt");
		File file = new File("C:\\Users\\80584_000\\Desktop\\Data\\AllUserHighAndLowTime.txt");
		List<String> list = FileUtil.readFileToList(file);
	
		
		//String filepaht="E:\\resultmonth.txt";//
		
		String filepath="E:\\Allusergfdifu.txt";//ÿ��
		parse(list,filepath);
		//WriteWeekTimes(list,filepath);
		//WriteResult(parselist(list),filepaht);

	}

	public static void parse(List<String> list,String filepath) throws IOException{
		if (new File(filepath).exists()) {
			new File(filepath).delete();
		}
		FileOutputStream out = new FileOutputStream(new File(filepath));
		for(int i=0;i<list.size();i++){
			out.write((list.get(i).substring(0, 8)+"\n").getBytes());;
		}
	}
	/**
	 * @param list
	 * @param filepaht
	 * @throws IOException
	 * �ó�����ѧϰ��ÿ���¼�ĸ߷�ʱ��͵ͷ�ʱ��
	 */
	public static void WriteEveryWeekUserLoginHighAndLow(List<String> list,String filepath) throws IOException{
		if (new File(filepath).exists()) {
			new File(filepath).delete();
		}
		String times="";
		String cs="";
		String cs2="";
		
		int n=0;
		int m=0;
		Map<String,String> map=new HashMap<String,String>();
		List<String> resultlist=new ArrayList<String>();
		List<String> timelist=new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			times=list.get(i).substring(0, 8);
			cs=list.get(i).substring(11, list.get(i).length());//��õ�¼����
			resultlist.add(cs);
			map.put(times, cs);
			//timelist.add(list.get(i).substring(0, 10));
			for(int j=i+1;j<list.size();j++){
				//�ж����еĵ�¼�Ĵ�ʱ�ǲ�����ͬһ����
				if(times.equals(list.get(j).substring(0, 8))){
					cs2=list.get(j).substring(11, list.get(j).length());
					//�ȴ�С
					if(Integer.valueOf(cs2)>=Integer.valueOf(cs)){
						String cs3=cs;
						cs=cs2;
						cs2=cs3;
						
					//resultlist.add(cs);
					//timelist.add(list.get(i).substring(0, 10));
					//map.put(times, cs);
				}
				
			}
			
		}
			resultlist.add(cs2);
			timelist.add(list.get(i).substring(0, 10));
		}
	}
	
	public static void sort(List<String> resultlist,List<String> list){
		for(int i=0;i<resultlist.size();i++){
				resultlist.get(i);//
		}
	}
	/**
	 * @param list
	 * @param filepath
	 * @throws IOException 
	 * ÿ�������û�����ҳ��Ĵ���
	 */
	public static void WriteEveryWeekUserViewTimes(List<String> list,String filepath) throws IOException{
		if (new File(filepath).exists()) {
			new File(filepath).delete();
		}
		FileOutputStream out = new FileOutputStream(new File(filepath));
		for(String lists:list){
			
			Double weektimes=Double.valueOf(lists.substring(0, lists.indexOf(",")))/4.0;
			if(weektimes<1.0){
				weektimes=1.0;
			}
			out.write(String.valueOf(weektimes+"\n").getBytes());
		}
	}
	/**
	 *����ÿ��ѧϰ��ÿ�죬ÿ�ܣ�ÿ�µ�ƽ���Ựʱ��
	 *ÿ��ĻỰʱ��/ÿ��ĻỰ����
	 */
	public static void WriteDayAverageTimes(List<String> logintimeslist,List<String> loginlonglist,String filepath) throws IOException{
		if (new File(filepath).exists()) {
			new File(filepath).delete();
		}
		String userid="";
		String date="";
		String cishu="";
		String shichang="";
		String userid2="";
		String date2="";
		FileOutputStream out = new FileOutputStream(new File(filepath));
		for(int i=0;i<logintimeslist.size();i++){
			userid=logintimeslist.get(i).substring(0, logintimeslist.get(i).indexOf("|"));
			date=logintimeslist.get(i).substring(logintimeslist.get(i).indexOf("|")+1, logintimeslist.get(i).indexOf(","));
			cishu=logintimeslist.get(i).substring(logintimeslist.get(i).indexOf(",")+1, logintimeslist.get(i).length());
			for(int j=0;j<loginlonglist.size();j++){
				userid2=loginlonglist.get(j).substring(loginlonglist.get(j).indexOf("|")+1,loginlonglist.get(j).indexOf(","));
				date2=loginlonglist.get(j).substring(loginlonglist.get(j).indexOf(",")+1,loginlonglist.get(j).length());
				shichang=loginlonglist.get(j).substring(0,loginlonglist.get(j).indexOf("|"));
				if(userid.equals(userid2)&&date.equals(date2)){
					Double average=Double.valueOf(shichang)/Double.valueOf(cishu);
					out.write((userid+"|"+date+"|"+average+"\n").getBytes());
				}
			}
		}
		
	}
	/**
	 * @param resultlist
	 * @param filepath
	 * @throws IOException
	 * ��������ʱ����ͳ��
	 */
	public static void WriteWeekTimes(List<String> resultlist,String filepath) throws IOException{
		if (new File(filepath).exists()) {
			new File(filepath).delete();
		}
		String times="";
		String userid="";
		String date="";
		FileOutputStream out = new FileOutputStream(new File(filepath));
		for(int i=0;i<resultlist.size();i++){
			times=resultlist.get(i).substring(resultlist.get(i).indexOf(",")+1,resultlist.get(i).length());
			Double weektime=Double.valueOf(times)/4.00;
			if(weektime<1.0){
				weektime=1.0;
			}
			userid = resultlist.get(i).substring(
					0,
					resultlist.get(i).indexOf("|"));
			date = resultlist.get(i).substring(
					resultlist.get(i).indexOf("|") + 1,
					resultlist.get(i).indexOf(","));
			//date = date.substring(0, 8);//ÿ��
			date = date.substring(0, 6);//ÿ��
			out.write((userid+"\t"+date+"\t"+weektime+"\n").getBytes());
		}
	}
	/**
	 * @param resultlist
	 * @param filepath
	 * @throws IOException
	 * ��������ʱ����ͳ��
	 * 
	 */
	public static void WriteMonthAndDayResult(List<String> resultlist,String filepath) throws IOException{
		String times="";
		String date="";
		String userid="";
		if (new File(filepath).exists()) {
			new File(filepath).delete();
		}
		FileOutputStream out = new FileOutputStream(new File(filepath));
		for(int i=0;i<resultlist.size();i++){
			times=resultlist.get(i).substring(0, resultlist.get(i).indexOf(","));
			Double weektimes=Double.valueOf(times)/4.00;
			if(weektimes<1.0){
				weektimes=1.0;
			}
			date = resultlist.get(i).substring(
					resultlist.get(i).indexOf("|") + 1,
					resultlist.get(i).length());
			//date = date.substring(0, 8);//ÿ��
			date = date.substring(0, 6);//ÿ��
			userid = resultlist.get(i).substring(
					resultlist.get(i).indexOf(",") + 1,
					resultlist.get(i).indexOf("|"));
			out.write((weektimes+"|"+date+"|"+userid+"\n").getBytes());
		}
	}
	/**
	 * @param resultlist
	 * @param filepath
	 * @throws IOException
	 * ͳ������ʱ��������ÿ�º�ÿ���
	 */
	public static void WriteResult(List<String> resultlist,String filepath) throws IOException{
		String userid = "";
		String userid2 = "";
		String date2 = "";
		String date = "";
		Long onlinetime = 0l;
		Long onlinetime2 = 0l;
		Long l1 = 0l;
		Long l2 = 0l;
		Long l3 = 0l;
		Long l = 0l;
		int m = 0;
		//FileOutputStream out = new FileOutputStream(new File("E:\\result.txt"));
		if (new File(filepath).exists()) {
			new File(filepath).delete();
		}
		FileOutputStream out = new FileOutputStream(new File(filepath));
		
		for (int i = 0; i < resultlist.size(); i++) {
			date = resultlist.get(i).substring(
					resultlist.get(i).indexOf(",") + 1,
					resultlist.get(i).length());
			//date = date.substring(0, 8);//ÿ��
			date = date.substring(0, 6);//ÿ��
			userid = resultlist.get(i).substring(
					resultlist.get(i).indexOf("|") + 1,
					resultlist.get(i).indexOf(","));
			onlinetime = Long.valueOf(resultlist.get(i).substring(0,
					resultlist.get(i).indexOf("|")));

			for (int j = i + 1; j < resultlist.size(); j++) {
				userid2 = resultlist.get(j).substring(
						resultlist.get(j).indexOf("|") + 1,
						resultlist.get(j).indexOf(","));
//				date2 = resultlist
//						.get(j)
//						.substring(resultlist.get(j).indexOf(",") + 1,
//								resultlist.get(j).length()).substring(0, 8);//ÿ��
				date2 = resultlist
						.get(j)
						.substring(resultlist.get(j).indexOf(",") + 1,
								resultlist.get(j).length()).substring(0, 6);//ÿ��
				
				onlinetime2 = Long.valueOf(resultlist.get(j).substring(0,
						resultlist.get(j).indexOf("|")));
				// ʱ����ͬһ�죬��userid ��ͬһ��,��һ����ȵ�
				// �ҳ������û��ĵ�ʱ�䣬���Զ�Ӧ��ʱ��ĺ�ʱ�������
				if (userid.equals(userid2) && date.equals(date2)) {
					
							i++;
							if (m == 0) {
								l = onlinetime + onlinetime2;
								l2 = l;

								m++;
							} else {
								l2 = l2 + onlinetime2;
								
							}
						
				
					
				}

		
			}

			//���ǵ�һ��
			if (l2 == 0) {
				out.write((onlinetime + "," + userid + "|" + date + "\n")
						.getBytes());
			
			} else {
				
				if(l3==0){
					
					l3=l2;
					
					m = 0;
					out.write((l2 + "," + userid + "|" + date + "\n")
							.getBytes());
					l2=0l;
				}else
				if(l3!=l2){
					out.write((l2 + "," + userid + "|" + date + "\n")
							.getBytes());
					out.flush();
					l3=l2;
					l2=0l;
					m = 0;
				}
				
			}
			
			
			
		}
	}
	public static List<String> parselist(List<String> lists) {
		String userid = "";
		String times = "";
		String lasttimes = "";
		String userid2 = "";
		Long l2 = 0l;
		Long l3 = 0l;
		Long l1 = 0l;
		int j = 0;

		List<String> resultlist = new ArrayList<String>();

		for (int i = 0; i < lists.size(); i++) {

			userid = lists.get(i).substring(0, lists.get(i).indexOf("|"));
			times = lists.get(i).substring(lists.get(i).indexOf("|") + 1,
					lists.get(i).length());
			if (i != lists.size() - 1) {
				userid2 = lists.get(i + 1).substring(0,
						lists.get(i + 1).indexOf("|"));
				lasttimes = lists.get(i + 1).substring(
						lists.get(i + 1).indexOf("|") + 1,
						lists.get(i + 1).length());
			} else {
				userid2 = lists.get(i).substring(0, lists.get(i).indexOf("|"));
				lasttimes = lists.get(i).substring(
						lists.get(i).indexOf("|") + 1, lists.get(i).length());
			}

			String s = "";
			String ss = "";
			try {
				s = times.substring(0, 10);
				ss = lasttimes.substring(0, 10);

			} catch (Exception e) {

			}
			if (s != "" && ss != "") {
				// ����userid ��ͬ����Ҫ��ͬһ��
				if (userid.equals(userid2) && s.equals(ss)) {

					l2 = Long.valueOf(lasttimes) - Long.valueOf(times);// ���ʱ��Ĳ�ֵ

					if (j == 0) {
						l1 = l2;// �Ѳ�ֵ����l1
						j++;
					} else {
						l1 = l1 + l2;
					}

				} else {

					resultlist.add(String.valueOf(l1) + "|" + userid + "," + s
							+ "\n");
					j = 0;
				}
			}

		}

		return resultlist;
	}
}
