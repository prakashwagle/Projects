load('/Users/prakashwagle/Documents/MATLAB/project1-2.mat');
% trainData_mean = zeros(2400,80);
% testData_mean = zeros(1200,80);
trainData_mean = zeros(2400,71);
testData_mean = zeros(1200,71);
trainAction = trainAction';

dumy =zeros(1200,1);
for k = 1:length(trainData)
    if (k~=342)
    m =zeros(1,3);v=zeros(1,3);s=zeros(1,3);crXY=zeros(1,3);
    crYZ=zeros(1,3);crXZ=zeros(1,3);maxx=zeros(1,3);minn=zeros(1,3);energy=zeros(1,3);
    temp = trainData{k};
    temp=resizem(temp,[400 3]);
    temp = temp/9.8;
    tempft=abs(fft(temp));
    tempf1=temp(1:300,:);
    tempf2=temp(100:400,:);
    tempft1=abs(fft(tempf1));
    tempft2=abs(fft(tempf2));
    
    mf= (mean(tempft));
    ent = wentropy(abs(tempft)/size(tempft,1),'shannon');
    enf = sum(abs(tempft))/size(tempft,1);
    stdf=std(temp);
    maxx= max(temp);
    minn=min(temp);
    vf=var(temp);
    
    
    mean1= (mean(tempf1));
    entropy1 = wentropy(abs(tempf1)/size(tempf1,1),'shannon');
    energy1 = sum(abs(tempft1))/size(tempft1,1);
    
    
    stdf1=std(tempf1);
    maxxf1= max(tempf1);
    minnf1=min(tempf1); 
    vf1=var(tempf1);
    crXYf1=reshape(corrcoef(tempf1(:,1),tempf1(:,2),'rows','complete'),1,4);
    crYZf1=reshape(corrcoef(tempf1(:,2),tempf1(:,3),'rows','complete'),1,4);
    crXZf1=reshape(corrcoef(tempf1(:,1),tempf1(:,3),'rows','complete'),1,4);
    
    mean2= (mean(tempf2));
    entropy2 = wentropy(abs(tempf2)/size(tempf2,1),'shannon');
    energy2 = sum(abs(tempft2))/size(tempft2,1);
    
    
    stdf2=std(tempf2);
    maxxf2= max(tempf2);
    minnf2=min(tempf2); 
    vf2=var(tempf2);
    crXYf2=reshape(corrcoef(tempf2(:,1),tempf2(:,2),'rows','complete'),1,4);
    crYZf2=reshape(corrcoef(tempf2(:,2),tempf2(:,3),'rows','complete'),1,4);
    crXZf2=reshape(corrcoef(tempf2(:,1),tempf2(:,3),'rows','complete'),1,4);
    
    %fv = cat(2,maxxf1(:,1),minnf1(:,1),mean1(:,1),stdf1(:,1),vf1(:,1),energy1(:,1),maxxf2(:,1),minnf2(:,1),mean2(:,1),stdf2(:,1),vf2(:,1),energy2(:,1),maxx(:,1),minn(:,1),mf(:,1),stdf(:,1),vf(:,1),enf(:,1),maxxf1(:,2),minnf1(:,2),mean1(:,2),stdf1(:,2),vf1(:,2),energy1(:,2),maxxf2(:,2),minnf2(:,2),mean2(:,2),stdf2(:,2),vf2(:,2),energy2(:,2),maxx(:,2),minn(:,2),mf(:,2),stdf(:,2),vf(:,2),enf(:,2),maxxf1(:,3),minnf1(:,3),mean1(:,3),stdf1(:,3),vf1(:,3),energy1(:,3),maxxf2(:,3),minnf2(:,3),mean2(:,3),stdf2(:,3),vf2(:,3),energy2(:,3),maxx(:,3),minn(:,3),mf(:,3),stdf(:,3),vf(:,3),enf(:,3),crXYf1,crXYf2,crYZf1,crYZf2,crXZf1,crXZf2,entropy1,entropy2);
    fv = cat(2,maxxf1(:,1),minnf1(:,1),mean1(:,1),stdf1(:,1),energy1(:,1),maxxf2(:,1),minnf2(:,1),mean2(:,1),stdf2(:,1),energy2(:,1),maxx(:,1),minn(:,1),mf(:,1),stdf(:,1),enf(:,1),maxxf1(:,2),minnf1(:,2),mean1(:,2),stdf1(:,2),energy1(:,2),maxxf2(:,2),minnf2(:,2),mean2(:,2),stdf2(:,2),energy2(:,2),maxx(:,2),minn(:,2),mf(:,2),stdf(:,2),enf(:,2),maxxf1(:,3),minnf1(:,3),mean1(:,3),stdf1(:,3),energy1(:,3),maxxf2(:,3),minnf2(:,3),mean2(:,3),stdf2(:,3),energy2(:,3),maxx(:,3),minn(:,3),mf(:,3),stdf(:,3),enf(:,3),crXYf1,crXYf2,crYZf1,crYZf2,crXZf1,crXZf2,entropy1,entropy2);
    %size(fv)
    trainData_mean(k,:)=fv;
    end
end

libsvmwrite('train_data',sparse(trainAction),sparse(trainData_mean));  % Seprate trainning data
 [trainY,trainX] = libsvmread('train_data');

for k = 1:length(testData)
    
    m =zeros(1,3);v=zeros(1,3);s=zeros(1,3);crXY=zeros(1,3);
    crYZ=zeros(1,3);crXZ=zeros(1,3);maxx=zeros(1,3);minn=zeros(1,3);energy=zeros(1,3);
    ra=zeros(1,57);
    temp = testData{k};
    temp=resizem(temp,[400 3]);
    temp = temp/9.8;
    tempft=abs(fft(temp));
    tempf1=temp(1:300,:);
    tempf2=temp(100:400,:);
    tempft1=abs(fft(tempf1));
    tempft2=abs(fft(tempf2));
    
     mf= (mean(tempft));
    ent = wentropy(abs(tempft)/size(tempft,1),'shannon');
    enf = sum(abs(tempft))/size(tempft,1);
    stdf=std(temp);
    maxx= max(temp);
    minn=min(temp);
    vf=var(temp);
    
    
    mean1= (mean(tempf1));
    entropy1 = wentropy(abs(tempf1)/size(tempf1,1),'shannon');
    energy1 = sum(abs(tempft1))/size(tempft1,1);
    
    
    stdf1=std(tempf1);
    maxxf1= max(tempf1);
    minnf1=min(tempf1); 
    vf1=var(tempf1);
    crXYf1=reshape(corrcoef(tempf1(:,1),tempf1(:,2),'rows','complete'),1,4);
    crYZf1=reshape(corrcoef(tempf1(:,2),tempf1(:,3),'rows','complete'),1,4);
    crXZf1=reshape(corrcoef(tempf1(:,1),tempf1(:,3),'rows','complete'),1,4);
    
    mean2= (mean(tempf2));
    entropy2 = wentropy(abs(tempf2)/size(tempf2,1),'shannon');
    energy2 = sum(abs(tempft2))/size(tempft2,1);
    
    
    stdf2=std(tempf2);
    maxxf2= max(tempf2);
    minnf2=min(tempf2); 
    vf2=var(tempf2);
    crXYf2=reshape(corrcoef(tempf2(:,1),tempf2(:,2),'rows','complete'),1,4);
    crYZf2=reshape(corrcoef(tempf2(:,2),tempf2(:,3),'rows','complete'),1,4);
    crXZf2=reshape(corrcoef(tempf2(:,1),tempf2(:,3),'rows','complete'),1,4);
    
 
 fv = cat(2,maxxf1(:,1),minnf1(:,1),mean1(:,1),stdf1(:,1),energy1(:,1),maxxf2(:,1),minnf2(:,1),mean2(:,1),stdf2(:,1),energy2(:,1),maxx(:,1),minn(:,1),mf(:,1),stdf(:,1),enf(:,1),maxxf1(:,2),minnf1(:,2),mean1(:,2),stdf1(:,2),energy1(:,2),maxxf2(:,2),minnf2(:,2),mean2(:,2),stdf2(:,2),energy2(:,2),maxx(:,2),minn(:,2),mf(:,2),stdf(:,2),enf(:,2),maxxf1(:,3),minnf1(:,3),mean1(:,3),stdf1(:,3),energy1(:,3),maxxf2(:,3),minnf2(:,3),mean2(:,3),stdf2(:,3),energy2(:,3),maxx(:,3),minn(:,3),mf(:,3),stdf(:,3),enf(:,3),crXYf1,crXYf2,crYZf1,crYZf2,crXZf1,crXZf2,entropy1,entropy2);
 %fv = cat(2,maxxf1(:,1),minnf1(:,1),mean1(:,1),stdf1(:,1),vf1(:,1),energy1(:,1),maxxf2(:,1),minnf2(:,1),mean2(:,1),stdf2(:,1),vf2(:,1),energy2(:,1),maxx(:,1),minn(:,1),mf(:,1),stdf(:,1),vf(:,1),enf(:,1),maxxf1(:,2),minnf1(:,2),mean1(:,2),stdf1(:,2),vf1(:,2),energy1(:,2),maxxf2(:,2),minnf2(:,2),mean2(:,2),stdf2(:,2),vf2(:,2),energy2(:,2),maxx(:,2),minn(:,2),mf(:,2),stdf(:,2),vf(:,2),enf(:,2),maxxf1(:,3),minnf1(:,3),mean1(:,3),stdf1(:,3),vf1(:,3),energy1(:,3),maxxf2(:,3),minnf2(:,3),mean2(:,3),stdf2(:,3),vf2(:,3),energy2(:,3),maxx(:,3),minn(:,3),mf(:,3),stdf(:,3),vf(:,3),enf(:,3),crXYf1,crXYf2,crYZf1,crYZf2,crXZf1,crXZf2,entropy1,entropy2);
  %fv = cat(2,maxxf1(:,1),minnf1(:,1),mean1(:,1),stdf1(:,1),energy1(:,1),maxxf2(:,1),minnf2(:,1),mean2(:,1),stdf2(:,1),energy2(:,1),maxxf1(:,2),minnf1(:,2),mean1(:,2),stdf1(:,2),energy1(:,2),maxxf2(:,2),minnf2(:,2),mean2(:,2),stdf2(:,2),energy2(:,2),maxxf1(:,3),minnf1(:,3),mean1(:,3),stdf1(:,3),energy1(:,3),maxxf2(:,3),minnf2(:,3),mean2(:,3),stdf2(:,3),energy2(:,3),crXYf1,crXYf2,crYZf1,crYZf2,crXZf1,crXZf2,entropy1,entropy2,maxx,minn,mf,stdf,enf);
% size(fv);
    testData_mean(k,:)=fv;
   
end




model = svmtrain(trainY,trainX,'-t 2 -q -m 1024 -c 1024 -g 0.125 ');
%model = svmtrain(trainY,trainX,'-t 2 -q -m 1024 -c 8 -g 2 ');
[plable,accuracy_linear,~] = svmpredict(dumy,testData_mean,model,[])
[alable,accuracy_linear,~] = svmpredict(trainAction,trainData_mean,model,[]);
test_function(plable,Prediction)


