/*
 Name : Prakash Wagle  UNCC ID : 800889950 Email: pwagle@uncc.edu
*/
package org.myorg;

import java.io.IOException;
import java.util.regex.Pattern;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


public class TermFrequency extends Configured implements Tool {

   private static final Logger LOG = Logger.getLogger( TermFrequency.class);

   public static void main( String[] args) throws  Exception {
      int res  = ToolRunner.run( new TermFrequency(), args);
      System.exit(res);
   }

   public int run( String[] args) throws  Exception {
      Job job  = Job.getInstance(getConf(), " wordcount ");
      job.setJarByClass(this.getClass());

      FileInputFormat.addInputPaths(job,args[0]);
      FileOutputFormat.setOutputPath(job,new Path(args[ 1]));
      job.setMapperClass(Map.class);
      job.setReducerClass(Reduce.class);
      job.setOutputKeyClass(Text.class);
      job.setOutputValueClass(DoubleWritable.class);

      return job.waitForCompletion(true) ? 0 : 1;
   }

   public static class Map extends Mapper<LongWritable ,  Text ,  Text ,  DoubleWritable > {
      private final static DoubleWritable one  = new DoubleWritable(1);
      private Text word  = new Text();

      private static final Pattern WORD_BOUNDARY = Pattern.compile("\\s*\\b\\s*");

      public void map( LongWritable offset,  Text lineText,  Context context)
        throws  IOException,  InterruptedException {

          String line  = lineText.toString(); // covert Text to String
          Text currentWord  = new Text();
          FileSplit file = (FileSplit) context.getInputSplit(); // Get input file information from context
         String filename= "#####"+file.getPath().getName();

          for ( String word  : WORD_BOUNDARY.split(line)) {
             if (word.isEmpty()) {
                continue;
             }
             word=word+filename; // Adding "#####" + filename  to the key
             currentWord  = new Text(word);
             context.write(currentWord,one);
         }
      }
   }

   public static class Reduce extends Reducer<Text ,  DoubleWritable ,  Text ,  DoubleWritable > {
      @Override
      public void reduce( Text word,  Iterable<DoubleWritable > counts,  Context context)
         throws IOException,  InterruptedException {
         double sum  = 0;
         for ( DoubleWritable count  : counts) {
            sum  += count.get();
         }
         sum = (sum ==0) ? 0.0 : (1+Math.log10(sum)); // calculating logarathmically scaled Frequency
         context.write(word,  new DoubleWritable(sum));
      }
   }
}
