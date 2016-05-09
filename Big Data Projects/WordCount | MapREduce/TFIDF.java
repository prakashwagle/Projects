/*
 Name : Prakash Wagle  UNCC ID : 800889950 Email: pwagle@uncc.edu
*/
package org.myorg;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


public class TFIDF extends Configured implements Tool {

   private static final Logger LOG = Logger.getLogger( TFIDF.class);

   public static void main( String[] args) throws  Exception {
      int res  = ToolRunner.run( new TFIDF(), args);
      System.exit(res);
   }

   public int run( String[] args) throws  Exception {
      Job job_first = Job.getInstance(getConf(), " wordcount ");

      Configuration con = new Configuration();
      job_first.setJarByClass(this.getClass());
      FileInputFormat.addInputPaths(job_first,args[0]); //set input path for first job_first
      FileOutputFormat.setOutputPath(job_first,new Path(args[1])); //set output path for first job_first
      job_first.setMapperClass(Map.class);
      job_first.setReducerClass(Reduce.class);
      job_first.setOutputKeyClass(Text.class);
      job_first.setOutputValueClass(DoubleWritable.class);
      job_first.submit(); //submits first job_first
      job_first.waitForCompletion(true);

      FileSystem filessystem = FileSystem.get(job_first.getConfiguration()); //taking information about filesyatem from first job_first
      FileStatus[] status = filessystem.listStatus(new Path(args[0])); // no. of input files for first job_first
      con.set("noInput",Integer.toString(status.length)); // add information about input file in configuration of second job_first


      Job job_second  = Job.getInstance(con, " wordcount2 ");
      job_second.setJarByClass(this.getClass());
      FileInputFormat.addInputPaths(job_second,args[1]);
      FileOutputFormat.setOutputPath(job_second,new Path(args[2]));
      job_second.setMapperClass(Map2.class);
      job_second.setReducerClass(Reduce2.class);
      job_second.setOutputKeyClass(Text.class);
      job_second.setOutputValueClass(Text.class);
      job_second.submit(); //submits second job_first
      return job_second.waitForCompletion(true) ? 0 : 1; //checks wether job_second was sucessfully executed or not
   }

   public static class Map extends Mapper<LongWritable ,  Text ,  Text ,  DoubleWritable > {
      private final static DoubleWritable one  = new DoubleWritable(1);
      private Text word  = new Text();

      private static final Pattern WORD_BOUNDARY = Pattern.compile("\\s*\\b\\s*");

      public void map( LongWritable offset,  Text lineText,  Context context)
        throws  IOException,  InterruptedException {

         String line  = lineText.toString();
         Text currentWord  = new Text();
        FileSplit file = (FileSplit) context.getInputSplit(); // Gets the information about input file from context
        String filename= "#####"+file.getPath().getName();

         for ( String word  : WORD_BOUNDARY.split(line)) {
            if (word.isEmpty()) {
               continue;
            }
            word=word+filename; // adding filename + '#####' to key
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
         sum = (sum ==0) ? 0.0 : (1+Math.log10(sum)); // computing logarithmically scaled term frequency
         context.write(word,  new DoubleWritable(sum));
      }
   }
//Map2 and Reduce2 are invoked by job_second for calculating Term frequency - Inverse documnet frequrncy
   public static class Map2 extends Mapper<LongWritable ,  Text ,  Text ,  Text > {
	    //  private final static DoubleWritable one  = new DoubleWritable(1);
	      private Text word  = new Text();
	      private static final Logger logger = Logger.getLogger( Map2.class);
	      private static final Pattern WORD_BOUNDARY = Pattern.compile("\\s*\\b\\s*");

	      public void map( LongWritable offset,  Text lineText,  Context context)
	        throws  IOException,  InterruptedException {

	         String line  = lineText.toString();
	         Text currentWord  = new Text();
	         Text currentValue  = new Text();
	         String limiter="#####";
	         String tokens[]= line.split(limiter); // splits value using "#####" as seprator
	         currentWord=new Text(tokens[0]);
	         String value = tokens[1].replace("\t","="); // replaces tab space by "="
	         currentValue=new Text(value);

	         context.write(currentWord,currentValue);
	      }
	   }

	   public static class Reduce2 extends Reducer<Text ,  Text ,  Text ,  DoubleWritable >
	   {
	      @Override
	      public void reduce(Text word,  Iterable<Text > counts,  Context context)
	         throws IOException,  InterruptedException {
	         double sum  = 0;
	         int size =0;
	         String word_string = word.toString(); // converts Text to String
	         String value_string="";
	         ArrayList<Double> value = new ArrayList<Double>();
	         ArrayList<String> filename = new ArrayList<String>();
	         Configuration con = context.getConfiguration();
	         Double inputsize = Double.parseDouble(con.get("noInput"));
	         String answer ="";
	         for (Text count  : counts) {
	        	 String  line = count.toString();
	        	 String tokens[] = line.split("=");
	        	 filename.add(tokens[0]);
	        	 value.add(Double.parseDouble(tokens[1]));
	        	 size++;
		         }
	    Double idf = Math.log10(inputsize/size); // Calculate Inverse Document frequency
	 		Iterator<String> FilenameIterator = filename.iterator();
	 		Iterator<Double> ValueIterator = value.iterator();
			while (FilenameIterator.hasNext()) {
				value_string="";
				value_string = word_string+"#####"+FilenameIterator.next();
				context.write(new Text(value_string),  new DoubleWritable(ValueIterator.next()*idf)); // calculate and write Term frequency - inverse document frequency
			}

	      }
	   }
}
