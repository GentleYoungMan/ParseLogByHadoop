package com.hadoop.Reduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

//Mapper<LongWritable, Text, LongWritable, Text>
public class MyReduce extends Reducer<Text, IntWritable, Text, IntWritable> {
	protected void reduce(Text key, Iterable<IntWritable> values,
			Context context) throws java.io.IOException, InterruptedException {
		
	 IntWritable result = new IntWritable();
		int sum = 0;
		for (IntWritable val : values) {
			sum += val.get();
			
		}
		
		result.set(sum);
		context.write(key, result);
	}
}
