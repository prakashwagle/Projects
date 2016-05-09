# Load the necessary libraries
library(plyr)
library(stringr)
library(e1071)
library(data.table)
library(rmongodb)
library(tm)
library(wordcloud)
library(MASS)
library(plotrix)

#Remove values in variables to remove any garbage value if any
remove(a,a1,a2,a3,a4,a5,dataf)
remove(sentences)
remove(afin_list)
remove(vNegTerms, negTerms, posTerms, vPosTerms)
remove(x,final_score,scores,initial_sentence,sentence,words,wordList,vNegMatches, negMatches, posMatches, vPosMatches,score)
remove(x1,x2,x3,x4)
remove(y1,y2,y3,y4)
remove(mail,d,w)
remove(wordcloud,wordcloud2)

#Read the Email CSV File
a=read.csv("/Users/prakashwagle/Downloads/email",header = TRUE,check.names=T,stringsAsFactors = FALSE)
#Selecting 1011 emails
a10=a[1:1011,1:15]
View(a10);
a111=data.table(a10)
a11=as.data.frame(a111[ , list( To = unlist( strsplit(To,"\n") ) ) , by = list(Mailbox, Date, From.Name, From.Address, ReplyTo, Cc, Bcc, Subject, Priority, Flags, Message.Id, Message, Attachments, Folder) ])
##View(a11)
#selecting message column from the emails
messagecol=a11[,12]
#Read dictionary
SentimentDict<-read.delim(file='/Users/prakashwagle/Documents/imm6010/AFINN/AFINN-111.txt', header=FALSE, stringsAsFactors=FALSE)
names(SentimentDict) <- c('word', 'Sentimentscore')
#Convert all words in the dictionary to lower case
SentimentDict$word<- tolower(SentimentDict$word)
#Categorize words in the emails into worst, bad, good, best  
worst<- c(SentimentDict$word[SentimentDict$Sentimentscore==-5 | SentimentDict$Sentimentscore==-4])
bad<-c(SentimentDict$word[SentimentDict$Sentimentscore==-3 | SentimentDict$Sentimentscore==-2 | SentimentDict$Sentimentscore==-1])
good<-c(SentimentDict$word[SentimentDict$Sentimentscore==3 | SentimentDict$Sentimentscore==2 | SentimentDict$Sentimentscore==1])
best<-c(SentimentDict$word[SentimentDict$Sentimentscore==5 | SentimentDict$Sentimentscore==4])  

#Perform sentiment analysis on the emails
sentimentAnalysis <- function(messagecol, worst, bad, good, best)
{
  final_scores<- matrix('', 0, 4)
  scores<-laply(messagecol, function(message, worst, bad, good, best)
  {
    initial_message<- message
    message <- gsub('[[:punct:]]', '', message)
    message <- gsub('[[:cntrl:]]', '', message)
    message <- gsub('\\d+', '', message)
    message <- tolower(message)
    wordsCollection <- str_split(message, '\\s+')
    singleword <- unlist(wordsCollection)
    
    bestmatch <- match(singleword, best)
    goodmatch <- match(singleword, good)
    worstmatch <- match(singleword, worst)
    badmatch<- match(singleword, bad)
    
    bestmatch <- sum(!is.na(bestmatch))
    goodmatch <- sum(!is.na(goodmatch))
    worstmatch <- sum(!is.na(worstmatch))
    badmatch <- sum(!is.na(badmatch))
    sentiment <- c(worstmatch, badmatch, goodmatch, bestmatch)
    return(sentiment)
  }, worst, bad, good, best)
  return(scores)
}
x <- sentimentAnalysis(messagecol, worst, bad, good, best)
x1 = x[,1]
x2 = x[,2]
x3 = x[,3]
x4 = x[,4]
#Multiply worst terms by -10, bad terms by -5, good terms by 5 and best terms by 10
y1 = x1 * -10
y2 = x2* -5
y3 = x3 * 5
y4 = x4 * 10
#Add columns to existing email file]
y = y1 + y2 + y3 + y4
a2 <- data.frame("worst" = x1, "bad" = x2, "good" = x3, "best" = x4, "SentimentScore" = y)
a3 <- cbind(a11, a2)
View(a3)
a4=sum(y)
#Barplot
xc<-c(sum(y1),sum(y2),sum(y3),sum(y4))
names(xc) <- c("VeryNegative","Negative","Positive","VeryPositive")
barplot(xc)
#PieChart
m <- sum(y2) * -1
slices <- c(sum(y1),m,sum(y3),sum(y4)) 
lbls <- cc("VeryNegative","Negative","Positive","VeryPositive")
pie3D(slices,labels=lbls,explode=0.1,
      main="Pie Chart of Sentiment of Emails ")

#Take message column in sentences 1
sentences1=a11[,12]
mail<- Corpus(VectorSource(sentences1))

#Wordcloud
wordcloud1 <- function(mail)
{
  #Remove unnecessary characters and words 
  mail<- tm_map(mail, stripWhitespace)
  
  mail<- tm_map(mail, removeNumbers)
  
  mail<- tm_map(mail, removePunctuation)
  
  mail<-tm_map(mail,removeWords,stopwords("english"))
  
  mail<-tm_map(mail,removeWords,c("the","I","you","thanks","from","From","Deshpande","Kishor","Vishnu"))
  
  mail<-tm_map(mail,removeWords,c("Seema","Millerwilliam","Bethmiller","Henrymiller","Randy","Carroll","William","Adam","Punjanisunilpathak","Chris","Morris","Barton"))
  
  mail<-tm_map(mail,removeWords,c("Avinash","Bbramesh","Adhikari","Fernando","Udaysinh","Doug","Millerwilliam","Laxman","Kumar","Roy","Pathak","Bhaskar","Deshpandehenry","Parag","Eknath"))
  
  mail<-tm_map(mail,removeWords,c("Kate","Kale","Kaleparag","Mishra","Sunilrandy","Anthony","Menon","Yogesh"))
  
  mail<-tm_map(mail,removeWords,c("Singh","Ramesh","Kamat","Mohite","Deshpandechris","Martin","Howard","Kim"))
  
  mail<-tm_map(mail,removeWords,c("Bulusukishor","Nikamkishor","Kumarbulusu","Dougmiller","Kenneth","Shauna","deshpandekishor","Sam","Thakur"))
  
  mail<-tm_map(mail,removeWords,c("Trivedi","Praveen","Santosh","Maharashtra","Nigam","Anand","Deshpandemiller","Virendra","Mumbai"))
  
  
  
  mail<-tm_map(mail,removeWords,c("Moss","Seemasunilpathak","Vivek","Upendra","Jaideep","Stevens","Mahesh"))
  
  mail<-tm_map(mail,removeWords,c("Sunil","Mugdha","Sandesh","Punjani","Bulusu","Viveksingh","Yashita","Beth"))
  
  mail<-tm_map(mail,removeWords,c("Chidambaram","can","william","henry","miller","this","Kamatdatatech","Deena","Pingle","Usa","Pinglelaxman","Stuartmiller","Tikku"))  
  
  mail<-tm_map(mail,removeWords,c("Chidambaramkishor","deshpande","vishnu","kishor","will","please","Brad","Susan","Ccmiller","Madison","From","Deshpandemailtokishordeshpandedatatechcommiller","Kishor","Vishnu"))
  
  wordcloud(mail,scale=c(4,0.5),min.freq=11,max.words=100,random.order=FALSE,rot.per=0.35,use.r.layout=FALSE,colors=brewer.pal(8,"Dark2"))
  
  
  return(as.character(mail))
  
}

w <- wordcloud1(mail)

