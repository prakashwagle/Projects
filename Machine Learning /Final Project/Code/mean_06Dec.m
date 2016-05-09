load('\\filer01\pwagle\My Documents\MATLAB\Final Project\project1.mat');
trainData_mean = zeros(2400,30);
testData_mean = zeros(1200,30);
trainAction = trainAction';
sizeTr=zeros(2400,1092);
sizeTe=zeros(1200,1092);
dumy =zeros(1200,1);
for k = 1:length(trainData)
    if (k~=342)
    m =zeros(1,3);v=zeros(1,3);s=zeros(1,3);crXY=zeros(1,3);
    crYZ=zeros(1,3);crXZ=zeros(1,3);maxx=zeros(1,3);minn=zeros(1,3);energy=zeros(1,3);
    ra=zeros(1,30);
    temp = trainData{k};
    temp = temp/9.8;
    m=mean(temp);
    s=std(temp);
    maxx= max(temp);
    minn=min(temp);
    v=var(temp);
    crXY=reshape(corrcoef(temp(:,1),temp(:,2),'rows','complete'),1,4);
    crYZ=reshape(corrcoef(temp(:,2),temp(:,3),'rows','complete'),1,4);
    crXZ=reshape(corrcoef(temp(:,1),temp(:,3),'rows','complete'),1,4);
    energy = sum(abs(fft(temp())))/size(temp,1);
    ra= cat(2,m(:,1),s(:,1),maxx(:,1),minn(:,1),v(:,1),m(:,2),s(:,2),maxx(:,2),minn(:,2),v(:,2),m(:,3),s(:,3),maxx(:,3),minn(:,3),v(:,3),crXY,crYZ,crXZ,energy);
    
    trainData_mean(k,:)=ra;
    end
end


 libsvmwrite('train_data',sparse(trainAction),sparse(trainData_mean));  % Seprate trainning data
 [trainY,trainX] = libsvmread('train_data');

for k = 1:length(testData)
    
    m =zeros(1,3);v=zeros(1,3);s=zeros(1,3);crXY=zeros(1,3);
    crYZ=zeros(1,3);crXZ=zeros(1,3);maxx=zeros(1,3);minn=zeros(1,3);energy=zeros(1,3);
    ra=zeros(1,30);
    temp = testData{k};
    temp = temp/9.8;
    m=mean(temp);
    s=std(temp);
    maxx= max(temp);
    minn=min(temp);
    v=var(temp);
    crXY=reshape(corrcoef(temp(:,1),temp(:,2),'rows','complete'),1,4);
    crYZ=reshape(corrcoef(temp(:,2),temp(:,3),'rows','complete'),1,4);
    crXZ=reshape(corrcoef(temp(:,1),temp(:,3),'rows','complete'),1,4);
    energy = sum(abs(fft(temp)))/size(temp,1);
    ra= cat(2,m(:,1),s(:,1),maxx(:,1),minn(:,1),v(:,1),m(:,2),s(:,2),maxx(:,2),minn(:,2),v(:,2),m(:,3),s(:,3),maxx(:,3),minn(:,3),v(:,3),crXY,crYZ,crXZ,energy);
    
     testData_mean(k,:)=ra;
end


   lable = testknn( trainData_mean,trainAction,testData_mean,1);

%model = svmtrain(trainY,trainX,'-t 1 -d 4 -s 0 -q -m 1024 -c 1 -g 0.01 ');
model = svmtrain(trainY,trainX,'-t 2 -q -m 1024 -c 50 -g 0.09 ');
[plable,accuracy_linear,~] = svmpredict(dumy,testData_mean,model,[])
[alable,accuracy_linear,~] = svmpredict(trainAction,trainData_mean,model,[]);