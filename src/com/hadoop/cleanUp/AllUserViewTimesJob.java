package com.hadoop.cleanUp;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.reduce.IntSumReducer;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.hadoop.Mapper.CountAllUseriViewtimesMaper;
import com.hadoop.Mapper.CountEveryUseriViewtimesMaper;
import com.hadoop.Mapper.userIdCount;
import com.hadoop.Reduce.AllUserViewTimeCountReduce;
import com.hadoop.Reduce.IdCountReduce;
import com.hadoop.Reduce.UserViewTimeCountReduce;

public class AllUserViewTimesJob extends Configured implements Tool {

	@Override
	public int run(String[] arg0) throws Exception {
		final Job job = new Job(new Configuration(),
				AllUserViewTimesJob.class.getSimpleName());
		job.setJarByClass(AllUserViewTimesJob.class);
		FileInputFormat.setInputPaths(job, arg0[0]);
		job.setMapperClass(CountAllUseriViewtimesMaper.class);
		job.setCombinerClass(IntSumReducer.class);

		job.setReducerClass(AllUserViewTimeCountReduce.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileOutputFormat.setOutputPath(job, new Path(arg0[1]));
		// MysqlOutputFormat.setOutputPath(job, new Path(arg0[1]));
		// �����Ѵ��ڵ�����ļ�
		FileSystem fs = FileSystem.get(new URI(arg0[0]), getConf());
		Path outPath = new Path(arg0[1]);
		if (fs.exists(outPath)) {
			fs.delete(outPath, true);
		}

		boolean success = job.waitForCompletion(true);
		if (success) {
			System.out.println("Clean process success!");
		} else {
			System.out.println("Clean process failed!");
		}

		return 0;
	}

	public static void main(String[] args) {
		String[] agrs = new String[] { "file:///E:/yearout",
				"file:///E:/userviewtimeseverydayMonth" };
		try {
			int res = ToolRunner.run(new Configuration(), new AllUserViewTimesJob(),
					agrs);
			System.exit(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}