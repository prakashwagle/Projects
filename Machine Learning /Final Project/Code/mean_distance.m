load('\\filer01\pwagle\My Documents\MATLAB\Final Project\project1.mat');
trainData_mean = cell(2400,1);
trainAction_mean = zeros(296305,1);
testData_mean = cell(1200,1);
mean =zeros(2400,1);
i=1;
for k = 1:length(trainData)
   temp = trainData{k};
%    mean =zeros(size(temp,1),1);
   for t= 1:size(temp,1)
         mean(k)=sqrt((temp(1,1).^2)+(temp(1,2).^2)+(temp(1,3).^2));
%          trainAction_mean(i,1)=trainAction(1,k);
           trainAction_mean=trainAction';
         i=i+1;
   end
  trainData_mean{k}=double(mean);
end
testmean =zeros(1200,1);
for k = 1:length(testData)
   temp = testData{k};
%    mean =zeros(size(temp,1),1);
   for t= 1:size(temp,1)
         testmean(k)=sqrt((temp(1,1).^2)+(temp(1,2).^2)+(temp(1,3).^2));
         
   end
  testData_mean{k}=double(testmean);
end
mean=double(mean);
testmean=double(testmean);
model = svmtrain(trainAction_mean,mean,'-t 2 -s 2 -q -m 1024 -c 100 ');
[~,accuracy_linear,~] = svmpredict(trainAction_mean,mean,model,[])