PageRank
Name: Prakash Wagle
Id: 800889950


Assumption : 
I have used #@# as a delimiter. If a link is present in  a given input with string “#@#” then it might not be able to calculate it’s pagerank correctly
Instruction to Run PageRank.java
1. Compile : javac -cp /usr/lib/hadoop/*:/usr/lib/hadoop-mapreduce/* PageRank.java -d build -Xlint
      
      2) Build Jar : jar -cvf PageRank.jar -C build/ .




      3) Execute : 


      For Execution you need to pass Three arguments
   hadoop jar PageRank.jar org.myorg.PageRank <inputpath> <outputpath> <Number of Iteration for Job2>
   hadoop jar PageRank.jar org.myorg.PageRank /user/pagerank/input /user/pagerank/output/output 10




       4) Output : head -100  pagerank.txt >> pagerank100.txt