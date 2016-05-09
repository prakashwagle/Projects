load('/Users/prakashwagle/Documents/MATLAB/project1-2.mat');
trainData_mean = zeros(2400,3);
testData_mean = zeros(1200,3);
trainAction = trainAction';
sizeTr=zeros(2400,1092);
sizeTe=zeros(1200,1092);
for k = 1:length(trainData)
   temp = trainData{k};
   temp1 = padarray(temp,[364-size(temp,1)],'post');
   sizeTr(k,:)=reshape(temp1',[1,1092]);
   tempx=0.0 ; tempy=0.0;tempz=0.0;
%    for t= 1:size(temp,1)
%          tempx=temp(1,1) + tempx;
%          tempy=temp(1,2) + tempy;
%          tempz=temp(1,3) + tempz;        
%    end
%    tempx=tempx/size(temp,1) ;tempy=tempy/size(temp,1) ;tempz=tempz/size(temp,1) ;
%    
%     trainData_mean(k,:)=[tempx;tempy;tempz];
end


% libsvmwrite('train_data',sparse(trainAction),sparse(trainData_mean));  % Seprate trainning data
% [trainY,trainX] = libsvmread('train_data');

for k = 1:length(testData)
   temp = testData{k};
   temp1 = padarray(temp,[364-size(temp,1)],'post');
   sizeTe(k,:)=reshape(temp1',[1,1092]);
% tempx=0.0 ; tempy=0.0;tempz=0.0;
%    for t= 1:size(temp,1)
%          tempx=temp(1,1) + tempx;
%          tempy=temp(1,2) + tempy;
%          tempz=temp(1,3) + tempz;        
%    end
%    tempx=tempx/size(temp,1) ;tempy=tempy/size(temp,1) ;tempz=tempz/size(temp,1) ;
%     testData_mean(k,:)=[tempx;tempy;tempz]; 
end


 mdl=fitcknn(sizeTr,trainAction,'Distance','jaccard');
 label = predict(mdl,sizeTe);

%model = svmtrain(trainY,trainX,'-t 0 -q -m 1024 -c 10 ');
%[plabel,accuracy_linear,~] = svmpredict(trainY,trainX,model,[])