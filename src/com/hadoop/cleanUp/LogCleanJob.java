package com.hadoop.cleanUp;

import java.io.File;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.TestMiniMRClientCluster.MyReducer;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.reduce.IntSumReducer;

import com.hadoop.Mapper.MyMapper;

public class LogCleanJob extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		final Job job = new Job(new Configuration(),
				LogCleanJob.class.getSimpleName());
		// 设置为可以打包运行
		File file=new File(args[1]);
		if(file.isFile()){
			file.delete();
		}else if(file.isDirectory()){
			file.delete();
		}
		job.setJarByClass(LogCleanJob.class);
		FileInputFormat.setInputPaths(job, args[0]);
		job.setMapperClass(MyMapper.class);
		job.setCombinerClass(IntSumReducer.class); 
		
		job.setReducerClass(MyReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		// 清理已存在的输出文件
//		FileSystem fs = FileSystem.get(new URI(args[0]), getConf());
//		Path outPath = new Path(args[1]);
		
		boolean success = job.waitForCompletion(true);
		if (success) {
			System.out.println("Clean process success!");
		} else {
			System.out.println("Clean process failed!");
		}
		return 0;
	}

	public static void main(String[] args) {
		//E:\access-log
		String[] agrs = new String[] { "hdfs://localhost:9000/user/test", "file:///E:/hadooplog" };
		Configuration conf = new Configuration();
		try {
			int res = ToolRunner.run(conf, new LogCleanJob(), agrs);
			
			System.exit(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
