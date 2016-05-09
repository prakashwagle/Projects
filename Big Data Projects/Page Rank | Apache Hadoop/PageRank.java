// Name : Prakash Wagle  UNCC ID : 800889950 Email: pwagle@uncc.edu

package org.myorg;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.ByteBuffer;
import org.apache.hadoop.fs.FileSystem;
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
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.io.DoubleWritable.Comparator;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapred.SequenceFileAsBinaryOutputFormat;
import org.apache.hadoop.mapred.SequenceFileInputFormat;
public class PageRank extends Configured implements Tool {

   private static final Logger LOG = Logger.getLogger( PageRank.class);

   public static void main( String[] args) throws  Exception {
      int res  = ToolRunner.run( new PageRank(), args);
      System.exit(res);
   }

   public int run( String[] args) throws  Exception {
	   //Delete if output file exists
	   FileSystem fs = FileSystem.get(getConf());
	      if(fs.exists(new Path(args[1])))
	      {
	        fs.delete(new Path(args[1]),true);
	      }
	   String input= args[0];
	   String output=args[1]+1;
	   runJob(input,output,1); // Call for first job
	   int status=1;
	   int runs=0;
	   if(args.length!=3)
	   {
		   runs = 10;  
	   }
	   else
	   {
	      runs = Integer.parseInt(args[2]);
	   }
	   input="";
      output="";
      for(int i=1;i<=runs;i++)
      {
    	  int j = i+1;
    	  input=args[1]+i;
    	  output=args[1]+j;
    	  runJob(input,output,2);  // Call for second job based on Number of Iterarion provided by user
    	  
      }
      runs+=1;
      input=args[1]+runs;
      runs+=1;
      output=args[1];
      runJob(input,output,3);  //Call for third job
      
      return status;
   }
   // Function to create and config Jobs
   public void runJob (String input_path,String output_path,int job)  throws IOException,InterruptedException,ClassNotFoundException
   {
	   if(job==1)
	   {
	   	  Job job_first  = Job.getInstance(getConf(), "pagerank");
	      job_first.setJarByClass(this.getClass());
	      FileInputFormat.addInputPaths(job_first,input_path);
	      FileOutputFormat.setOutputPath(job_first,new Path(output_path));
	      job_first.setMapperClass(ParseMap.class);
	      job_first.setReducerClass(ParseReduce.class);
	      job_first.setNumReduceTasks(1);
	      job_first.setOutputKeyClass(Text.class);
	      job_first.setOutputValueClass(Text.class);
	      job_first.waitForCompletion(true);
	   }
	   else if(job==2)
	   {
		   	  Job job_second  = Job.getInstance(getConf(),"pagerank2");
		      job_second.setJarByClass(this.getClass());
		      FileInputFormat.addInputPaths(job_second,input_path);
		      FileOutputFormat.setOutputPath(job_second,new Path(output_path));
		      job_second.setMapperClass(PageRankMap.class);
		      job_second.setReducerClass(PageRankReduce.class);
		      job_second.setOutputKeyClass(Text.class);
		      job_second.setOutputValueClass(Text.class);
		      job_second.setNumReduceTasks(1);
		      job_second.waitForCompletion(true); 
		      FileSystem fs = FileSystem.get(getConf());
		      if(fs.exists(new Path(input_path)))
		      {
		      fs.delete(new Path(input_path),true);
		      }
	   }
	   else
	   {
		   Job job_third  = Job.getInstance(getConf(),"pagerank3");
		   job_third.setJarByClass(this.getClass());
		      FileInputFormat.addInputPaths(job_third,input_path);
		      FileOutputFormat.setOutputPath(job_third,new Path(output_path));
		      job_third.setMapperClass(SortMap.class);
		      job_third.setReducerClass(SortReduce.class);
		      job_third.setOutputKeyClass(DoubleWritable.class);
		      job_third.setOutputValueClass(Text.class);
		      job_third.setSortComparatorClass(DoubleComparator.class);
		      job_third.setNumReduceTasks(1);
		      job_third.waitForCompletion(true); 
		      FileSystem fs = FileSystem.get(getConf());
		      if(fs.exists(new Path(input_path)))
		      {
		      fs.delete(new Path(input_path),true);
		      }
	   }
   }
   
  

   public static class ParseMap extends Mapper<LongWritable,Text,Text,Text> {
    
     private static final Pattern LINK_PATTERN = Pattern.compile("\\[.+?\\]"); // Pattern to take out links from <Text>

      public void map( LongWritable offset,  Text line,  Context context) throws IOException,InterruptedException {

          String title_str ="";
          String text ="";
          int begin = 0;
          begin=line.find("<title>"); 
          int end = 0;
          end = line.find("</title>", begin);
          begin += 7; 
          
          title_str= Text.decode(line.getBytes(),begin,end-begin); // Parse title out of line
          title_str=title_str.replaceAll(",","");
          title_str=title_str.replaceAll("\\s","_"); // Replace all white space by "_"
          Text title = new Text(title_str);
          begin=0;
          begin = line.find("<text");
          begin = line.find(">",begin);
          end = line.find("</text>",begin);
          begin += 1;
          
          if(begin == -1 || end == -1) {
        	 return;
          }
          else
          {
        	  text = Text.decode(line.getBytes(), begin, end-begin); // Parse text out of line
          }
          Matcher match = LINK_PATTERN.matcher(text);
          while(match.find())
          {
        	  String link= match.group().replace("[",""); // Replace all "[]" present in link  by ""
        	  String link2=link.replace("]","");
        	  link2 = link2.replaceAll(",","");
        	  link2 = link2.replaceAll("\\s","_"); // Replace all white space by "_"
        	  
        	  context.write(title,new Text(link2));
          }
               
        }
     }
   

   public static class ParseReduce extends Reducer<Text,Text,Text,Text> {
	   public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
	        String defualtpagerank = "0.15\t";

	        boolean first = true;

	        for (Text value : values) {
	        	 if(!first) defualtpagerank += "#@#";  //Adds delimiter along with default page rank
	            defualtpagerank += value.toString();
	             first = false;
	        }

	        context.write(key, new Text(defualtpagerank));
	    }
   	}
   
   ///////////////////////////////////////////////////////////
   public static class PageRankMap extends Mapper<LongWritable,Text,Text,Text> {
	    
	     public void map( LongWritable offset,Text lineText,  Context context) throws IOException,InterruptedException 
	        {

	          int page_tab = lineText.find("\t");
	          int rank_tab = lineText.find("\t",page_tab+1);
	          if(page_tab==-1 || rank_tab==-1)
	          {
	        	  return;
	          }
	          String page = Text.decode(lineText.getBytes(),0,page_tab);
	         
	          
	          
	          String page_rank = Text.decode(lineText.getBytes(),0,rank_tab+1);
	          String list_link = Text.decode(lineText.getBytes(),rank_tab+1,lineText.getLength()-(rank_tab+1));
	          
	          String[] link_list = list_link.split("#@#"); // Split based on delimiter
	          for (String link : link_list )
	          {
	        	 if(link.isEmpty())
	        	 continue;
	        	 
	        	  context.write(new Text(link), new Text(page_rank +"\t"+ link_list.length)); // Write intermideiate output along with no. of links
	          }
	          if(link_list.length==0)
	          {
	        	  return;
	          }
	          context.write(new Text(page), new Text("@@@" + list_link));  // Write linklist parsed from first job   
	        }
	     }
	   

	   public static class PageRankReduce extends Reducer<Text,Text,Text,Text> {
		   public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		        
                String link_list="" ;
                String[] page;
                float totalPageRank=0; 
                String test="";
                float dampling=0.85f;
		        boolean first = true;

		        for (Text value : values) {
		        String text = value.toString();	
		        	if(text.startsWith("@@@"))  // parse the link list sperately
		        	{
		        	link_list = text.substring(3);
		        	}
		         
		        	else
		        	{
		        	
		        	    page = text.split("\\t");
		        	    if(page[1].isEmpty() || page[3].isEmpty())
			        	{
			        		return;
			        	}
				        float pageRank = Float.valueOf(page[1]);
				        float totalLink = Integer.valueOf(page[3]);
				        totalPageRank = totalPageRank + (pageRank/totalLink); // Calculate page rank for given key
		        	}
		      }
		        totalPageRank = (dampling * totalPageRank) + (1-dampling); // Add dampling factor to page rank
		        context.write(key, new Text(totalPageRank +"\t"+link_list));
		      
		    }
	   	}
	   // Sorts the result comming from Job2 
	   public static class SortMap extends Mapper<LongWritable,Text,DoubleWritable,Text> {
		    
	   public void map( LongWritable offset,Text lineText,  Context context) throws IOException,InterruptedException 
		        {

		          int page_tab = lineText.find("\t");
		          int rank_tab = lineText.find("\t",page_tab+1);
		          try
		          {
		          if(rank_tab==-1)
		          {
		        	  rank_tab= lineText.getLength()-page_tab;
		          }
		          String page = Text.decode(lineText.getBytes(),0,page_tab);
		          String rank = Text.decode(lineText.getBytes(),page_tab,(rank_tab-page_tab));
		          if(page=="" || rank=="")
		          {
		        	  return;
		          }
		        
		        	  context.write(new DoubleWritable(Double.parseDouble(rank)), new Text(page));  
		          }
		          catch(Exception e)
		          {
		        	 return; 
		          }
		              
		        }
		     }
	   
	   public static class SortReduce extends Reducer<DoubleWritable,Text,DoubleWritable,Text> {
		   public void reduce(DoubleWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException 
		   {
		        
		        for (Text value : values) 
		        {
		        	context.write(key,value);
		        }

		        
		    }
	   	}
	   // Overwrites default comprator for changing it to Descending order
	   public static class DoubleComparator extends WritableComparator {

		    public DoubleComparator() {
		        super(DoubleWritable.class);
		    }

		    @Override
		    public int compare(byte[] b1, int s1, int l1,byte[] b2, int s2, int l2) {

		        Double v1 = ByteBuffer.wrap(b1, s1, l1).getDouble();
		        Double v2 = ByteBuffer.wrap(b2, s2, l2).getDouble();

		        return v1.compareTo(v2) * (-1);
		    }
		}
}
