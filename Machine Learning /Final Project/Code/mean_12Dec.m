load('/Users/prakashwagle/Documents/MATLAB/project1-2.mat');
trainData_mean = zeros(2400,56);
testData_mean = zeros(1200,56);
% trainData_mean = zeros(2400,72);
% testData_mean = zeros(1200,72);
trainAction = trainAction';

dumy =zeros(1200,1);
for k = 1:length(trainData)
    if (k~=342)
    m =zeros(1,3);v=zeros(1,3);s=zeros(1,3);crXY=zeros(1,3);
    crYZ=zeros(1,3);crXZ=zeros(1,3);maxx=zeros(1,3);minn=zeros(1,3);energy=zeros(1,3);
    temp = trainData{k};
    temp=resizem(temp,[400 3]);
    temp = temp/9.8;
    tempft=fft(cat(2,temp(:,1)-mean(temp(:,1)),temp(:,2)-mean(temp(:,2)),temp(:,3)-mean(temp(:,3))));
    tempf1=temp(1:300,:);
    tempf2=temp(100:400,:);
    tempft1=fft(cat(2,tempf1(:,1)-mean(tempf1(:,1)),tempf1(:,2)-mean(tempf1(:,2)),tempf1(:,3)-mean(tempf1(:,3))));
    tempft2=fft(cat(2,tempf2(:,1)-mean(tempf2(:,1)),tempf2(:,2)-mean(tempf2(:,2)),tempf2(:,3)-mean(tempf2(:,3))));
    
    mf= (mean(tempft));
    ent = wentropy(abs(tempft)/size(tempft,1),'shannon');
    enf = sum(abs(tempft))/size(tempft,1);
    stdf=std(temp);
    maxx= max(temp);
    minn=min(temp);
    
    
    mean1= (mean(tempf1));
    entropy1 = wentropy(abs(tempft1)/size(tempft1,1),'shannon');
    energy1 = sum(abs(tempft1))/size(tempft1,1);
    
    
    stdf1=std(tempf1);
    maxxf1= max(tempf1);
    minnf1=min(tempf1);  
    crXYf1=reshape(corrcoef(tempf1(:,1),tempf1(:,2),'rows','complete'),1,4);
    crYZf1=reshape(corrcoef(tempf1(:,2),tempf1(:,3),'rows','complete'),1,4);
    crXZf1=reshape(corrcoef(tempf1(:,1),tempf1(:,3),'rows','complete'),1,4);
    
    mean2= (mean(tempf2));
    entropy2 = wentropy(abs(tempft2)/size(tempf2,1),'shannon');
    energy2 = sum(abs(tempft2))/size(tempft2,1);
    
    
    stdf2=std(tempf2);
    maxxf2= max(tempf2);
    minnf2=min(tempf2); 
    crXYf2=reshape(corrcoef(tempf2(:,1),tempf2(:,2),'rows','complete'),1,4);
    crYZf2=reshape(corrcoef(tempf2(:,2),tempf2(:,3),'rows','complete'),1,4);
    crXZf2=reshape(corrcoef(tempf2(:,1),tempf2(:,3),'rows','complete'),1,4);
    
    %fv = cat(2,mean1,entropy1,energy1,vf1,maxxf1,minnf1,stdf1,crXYf1,crYZf1,crXZf1,mean2,entropy2,energy2,vf2,maxxf1,minnf2,stdf2,crXYf2,crYZf2,crXZf2,enf);
    %fv = cat(2,mean1,entropy1,energy1,vf1,maxxf1,minnf1,stdf1,mean2,entropy2,energy2,vf2,maxxf1,minnf2,stdf2,crXYf1,crYZf1,crXZf1,crXYf2,crYZf2,crXZf2,mf,ent,enf,stdf);
    fv = cat(2,maxxf1(:,1),minnf1(:,1),mean1(:,1),stdf1(:,1),energy1(:,1),maxxf2(:,1),minnf2(:,1),mean2(:,1),stdf2(:,1),energy2(:,1),maxxf1(:,2),minnf1(:,2),mean1(:,2),stdf1(:,2),energy1(:,2),maxxf2(:,2),minnf2(:,2),mean2(:,2),stdf2(:,2),energy2(:,2),maxxf1(:,3),minnf1(:,3),mean1(:,3),stdf1(:,3),energy1(:,3),maxxf2(:,3),minnf2(:,3),mean2(:,3),stdf2(:,3),energy2(:,3),crXYf1,crXYf2,crYZf1,crYZf2,crXZf1,crXZf2,entropy1,entropy2);
    %fv = cat(2,mean1(:,1),stdf1(:,1),energy1(:,1),maxxf1(:,1),minnf1(:,1),mean2(:,1),stdf2(:,1),energy2(:,1),maxxf2(:,1),minnf2(:,1),mean1(:,2),stdf1(:,2),energy1(:,2),maxxf1(:,2),minnf1(:,2),mean2(:,2),stdf2(:,2),energy2(:,2),maxxf2(:,2),minnf2(:,2),mean1(:,3),stdf1(:,3),energy1(:,3),maxxf1(:,3),minnf1(:,3),mean2(:,3),stdf2(:,3),energy2(:,3),maxxf2(:,3),minnf2(:,3),crXYf1,crXYf2,crYZf1,crYZf2,crXZf1,crXZf2,entropy1,entropy2);
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
    tempft=fft(cat(2,temp(:,1)-mean(temp(:,1)),temp(:,2)-mean(temp(:,2)),temp(:,3)-mean(temp(:,3))));
    tempf1=temp(1:300,:);
    tempf2=temp(100:400,:);
    tempft1=fft(cat(2,tempf1(:,1)-mean(tempf1(:,1)),tempf1(:,2)-mean(tempf1(:,2)),tempf1(:,3)-mean(tempf1(:,3))));
    tempft2=fft(cat(2,tempf2(:,1)-mean(tempf2(:,1)),tempf2(:,2)-mean(tempf2(:,2)),tempf2(:,3)-mean(tempf2(:,3))));
    
    mf= (mean(tempft));
    ent = wentropy(abs(tempft)/size(tempft,1),'shannon');
    enf = sum(abs(tempft))/size(tempft,1);
    stdf=std(temp);
    maxx= max(temp);
    minn=min(temp);
    
    
    mean1= (mean(tempf1));
    entropy1 = wentropy(abs(tempft1)/size(tempft1,1),'shannon');
    energy1 = sum(abs(tempft1))/size(tempft1,1);
    
    
    stdf1=std(tempf1);
    maxxf1= max(tempf1);
    minnf1=min(tempf1);  
    crXYf1=reshape(corrcoef(tempf1(:,1),tempf1(:,2),'rows','complete'),1,4);
    crYZf1=reshape(corrcoef(tempf1(:,2),tempf1(:,3),'rows','complete'),1,4);
    crXZf1=reshape(corrcoef(tempf1(:,1),tempf1(:,3),'rows','complete'),1,4);
    
    mean2= (mean(tempf2));
    entropy2 = wentropy(abs(tempft2)/size(tempf2,1),'shannon');
    energy2 = sum(abs(tempft2))/size(tempft2,1);
    
    
    stdf2=std(tempf2);
    maxxf2= max(tempf2);
    minnf2=min(tempf2); 
    crXYf2=reshape(corrcoef(tempf2(:,1),tempf2(:,2),'rows','complete'),1,4);
    crYZf2=reshape(corrcoef(tempf2(:,2),tempf2(:,3),'rows','complete'),1,4);
    crXZf2=reshape(corrcoef(tempf2(:,1),tempf2(:,3),'rows','complete'),1,4);
    
     %fv = cat(2,mean1,entropy1,energy1,vf1,maxxf1,minnf1,stdf1,crXYf1,crYZf1,crXZf1,mean2,entropy2,energy2,vf2,maxxf1,minnf2,stdf2,crXYf2,crYZf2,crXZf2,enf);
%      fv = cat(2,mean1(:,1),entropy1(:,1),stdf1(:,1),maxxf1(:,1),minnf1(:,1),mean2(:,1),entropy2(:,1),stdf2(:,1),maxxf2(:,1),minnf2(:,1),
%                 mean1(:,2),entropy1(:,2),stdf1(:,2),maxxf1(:,2),minnf1(:,2),mean2(:,2),entropy2(:,2),stdf2(:,2),maxxf2(:,2),minnf2(:,2),
%                 mean1(:,3),entropy1(:,3),stdf1(:,3),maxxf1(:,3),minnf1(:,3),mean2(:,3),entropy2(:,3),stdf2(:,3),maxxf2(:,3),minnf2(:,3),mf,ent,stdf,maxx,minn,,crXYf1,crYZf1,crXZf1,crXYf2,crYZf2,crXZf2,enf);

   fv = cat(2,maxxf1(:,1),minnf1(:,1),mean1(:,1),stdf1(:,1),energy1(:,1),maxxf2(:,1),minnf2(:,1),mean2(:,1),stdf2(:,1),energy2(:,1),maxxf1(:,2),minnf1(:,2),mean1(:,2),stdf1(:,2),energy1(:,2),maxxf2(:,2),minnf2(:,2),mean2(:,2),stdf2(:,2),energy2(:,2),maxxf1(:,3),minnf1(:,3),mean1(:,3),stdf1(:,3),energy1(:,3),maxxf2(:,3),minnf2(:,3),mean2(:,3),stdf2(:,3),energy2(:,3),crXYf1,crXYf2,crYZf1,crYZf2,crXZf1,crXZf2,entropy1,entropy2);
  %fv = cat(2,mean1(:,1),stdf1(:,1),energy1(:,1),maxxf1(:,1),minnf1(:,1),mean2(:,1),stdf2(:,1),energy2(:,1),maxxf2(:,1),minnf2(:,1),mean1(:,2),stdf1(:,2),energy1(:,2),maxxf1(:,2),minnf1(:,2),mean2(:,2),stdf2(:,2),energy2(:,2),maxxf2(:,2),minnf2(:,2),mean1(:,3),stdf1(:,3),energy1(:,3),maxxf1(:,3),minnf1(:,3),mean2(:,3),stdf2(:,3),energy2(:,3),maxxf2(:,3),minnf2(:,3),crXYf1,crXYf2,crYZf1,crYZf2,crXZf1,crXZf2,entropy1,entropy2);

% size(fv);
    testData_mean(k,:)=fv;
   
end



model = svmtrain(trainY,trainX,'-t 2 -q -m 1024 -c 512 -g 0.0625 ');
%model = svmtrain(trainY,trainX,'-t 2 -q -m 1024 -c 8 -g 2 ');
[plable,accuracy_linear,~] = svmpredict(dumy,testData_mean,model,[])
[alable,accuracy_linear,~] = svmpredict(trainY,trainX,model,[]);
test_function(plable,Prediction)
test_function(plable,result_12Dec)

