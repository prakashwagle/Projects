load('/Users/prakashwagle/Documents/MATLAB/project1-2.mat');
trainData_mean = zeros(2400,69);
testData_mean = zeros(1200,69);
trainAction = trainAction';

dumy =zeros(1200,1);
for k = 1:length(trainData)
    if (k~=342)
    m =zeros(1,3);v=zeros(1,3);s=zeros(1,3);crXY=zeros(1,3);
    crYZ=zeros(1,3);crXZ=zeros(1,3);maxx=zeros(1,3);minn=zeros(1,3);energy=zeros(1,3);
    ra=zeros(1,57);
    temp = trainData{k};
    temp=resizem(temp,[400 3]);
    temp = temp/9.8;
    tempf1=temp(1:300,:);
    tempf2=temp(100:400,:);
    m=mean(temp);
    s=std(temp);
    maxx= max(temp);
    minn=min(temp);
    v=var(temp);
    crXY=reshape(corrcoef(temp(:,1),temp(:,2),'rows','complete'),1,4);
    crYZ=reshape(corrcoef(temp(:,2),temp(:,3),'rows','complete'),1,4);
    crXZ=reshape(corrcoef(temp(:,1),temp(:,3),'rows','complete'),1,4);
    energy = sum(abs(fft(temp)))/size(temp,1);
    
    mf1=mean(tempf1);
    sf1=std(tempf1);
    maxxf1= max(tempf1);
    minnf1=min(tempf1);
    vf1=var(tempf1);
    crXYf1=reshape(corrcoef(tempf1(:,1),tempf1(:,2),'rows','complete'),1,4);
    crYZf1=reshape(corrcoef(tempf1(:,2),tempf1(:,3),'rows','complete'),1,4);
    crXZf1=reshape(corrcoef(tempf1(:,1),tempf1(:,3),'rows','complete'),1,4);
    
    mf2=mean(tempf2);
    sf2=std(tempf2);
    maxxf2= max(tempf2);
    minnf2=min(tempf2);
    vf2=var(tempf2);
    crXYf2=reshape(corrcoef(tempf2(:,1),tempf2(:,2),'rows','complete'),1,4);
    crYZf2=reshape(corrcoef(tempf2(:,2),tempf2(:,3),'rows','complete'),1,4);
    crXZf2=reshape(corrcoef(tempf2(:,1),tempf2(:,3),'rows','complete'),1,4);
    
    %ra= cat(2,maxx(:,1),minn(:,1),m(:,1),s(:,1),v(:,1),maxx(:,2),minn(:,2),v(:,2),m(:,2),s(:,2),maxx(:,3),minn(:,3),m(:,3),s(:,3),v(:,3),maxx,minn,m,s,crXY,crYZ,crXZ,energy);
    ra= cat(2,maxxf1(:,1),minnf1(:,1),mf1(:,1),sf1(:,1),vf1(:,1),maxxf2(:,1),minnf2(:,1),mf2(:,1),sf2(:,1),vf2(:,1),maxxf1(:,2),minnf1(:,2),vf1(:,2),mf1(:,2),sf1(:,2),maxxf2(:,2),minnf2(:,2),vf2(:,2),mf2(:,2),sf2(:,2),maxxf1(:,3),minnf1(:,3),mf1(:,3),sf1(:,3),vf2(:,3), maxxf2(:,3),minnf2(:,3),mf2(:,3),sf2(:,3),vf2(:,3),maxx,minn,m,s,crXYf1,crYZf1,crXZf1,crXYf2,crYZf2,crXZf2,energy);
   
    % ra= cat(2,maxxf1(:,1),maxxf2(:,1),minnf1(:,1),minnf2(:,1),mf1(:,1),mf2(:,1),sf1(:,1),sf2(:,1),vf1(:,1),vf2(:,1),maxxf1(:,2),maxxf2(:,2),minnf1(:,2),minnf2(:,2),mf1(:,2),mf2(:,2),sf1(:,2),sf2(:,2),vf1(:,2),vf2(:,2),maxxf1(:,3),maxxf2(:,3),minnf1(:,3),minnf2(:,3),mf1(:,3),mf2(:,3),sf1(:,3),sf2(:,3),vf1(:,3),vf2(:,3),maxx,minn,m,s,crXY,crYZ,crXZ,energy);
    
    trainData_mean(k,:)=ra;
    end
end


 libsvmwrite('train_data',sparse(trainAction),sparse(trainData_mean));  % Seprate trainning data
 [trainY,trainX] = libsvmread('train_data');

for k = 1:length(testData)
    
    m =zeros(1,3);v=zeros(1,3);s=zeros(1,3);crXY=zeros(1,3);
    crYZ=zeros(1,3);crXZ=zeros(1,3);maxx=zeros(1,3);minn=zeros(1,3);energy=zeros(1,3);
    ra=zeros(1,42);
    temp = testData{k};
    temp=resizem(temp,[400 3]);
    temp = temp/9.8;
    tempf1=temp(1:300,:);
    tempf2=temp(100:400,:);
    m=mean(temp);
    s=std(temp);
    maxx= max(temp);
    minn=min(temp);
    v=var(temp);
    crXY=reshape(corrcoef(temp(:,1),temp(:,2),'rows','complete'),1,4);
    crYZ=reshape(corrcoef(temp(:,2),temp(:,3),'rows','complete'),1,4);
    crXZ=reshape(corrcoef(temp(:,1),temp(:,3),'rows','complete'),1,4);
    energy = sum(abs(fft(temp)))/size(temp,1);
    
    mf1=mean(tempf1);
    sf1=std(tempf1);
    maxxf1= max(tempf1);
    minnf1=min(tempf1);
    vf1=var(tempf1);
    crXYf1=reshape(corrcoef(tempf1(:,1),tempf1(:,2),'rows','complete'),1,4);
    crYZf1=reshape(corrcoef(tempf1(:,2),tempf1(:,3),'rows','complete'),1,4);
    crXZf1=reshape(corrcoef(tempf1(:,1),tempf1(:,3),'rows','complete'),1,4);
    
    mf2=mean(tempf2);
    sf2=std(tempf2);
    maxxf2= max(tempf2);
    minnf2=min(tempf2);
    vf2=var(tempf2);
    crXYf2=reshape(corrcoef(tempf2(:,1),tempf2(:,2),'rows','complete'),1,4);
    crYZf2=reshape(corrcoef(tempf2(:,2),tempf2(:,3),'rows','complete'),1,4);
    crXZf2=reshape(corrcoef(tempf2(:,1),tempf2(:,3),'rows','complete'),1,4);
    
    %ra= cat(2,maxx(:,1),minn(:,1),m(:,1),s(:,1),v(:,1),maxx(:,2),minn(:,2),v(:,2),m(:,2),s(:,2),maxx(:,3),minn(:,3),m(:,3),s(:,3),v(:,3),maxx,minn,m,s,crXY,crYZ,crXZ,energy);
    ra= cat(2,maxxf1(:,1),minnf1(:,1),mf1(:,1),sf1(:,1),vf1(:,1),maxxf2(:,1),minnf2(:,1),mf2(:,1),sf2(:,1),vf2(:,1),maxxf1(:,2),minnf1(:,2),vf1(:,2),mf1(:,2),sf1(:,2),maxxf2(:,2),minnf2(:,2),vf2(:,2),mf2(:,2),sf2(:,2),maxxf1(:,3),minnf1(:,3),mf1(:,3),sf1(:,3),vf2(:,3), maxxf2(:,3),minnf2(:,3),mf2(:,3),sf2(:,3),vf2(:,3),maxx,minn,m,s,crXYf1,crYZf1,crXZf1,crXYf2,crYZf2,crXZf2,energy);
    
    %ra= cat(2,maxxf1(:,1),maxxf2(:,1),minnf1(:,1),minnf2(:,1),mf1(:,1),mf2(:,1),sf1(:,1),sf2(:,1),vf1(:,1),vf2(:,1),maxxf1(:,2),maxxf2(:,2),minnf1(:,2),minnf2(:,2),mf1(:,2),mf2(:,2),sf1(:,2),sf2(:,2),vf1(:,2),vf2(:,2),maxxf1(:,3),maxxf2(:,3),minnf1(:,3),minnf2(:,3),mf1(:,3),mf2(:,3),sf1(:,3),sf2(:,3),vf1(:,3),vf2(:,3),maxx,minn,m,s,crXY,crYZ,crXZ,energy);
   
     testData_mean(k,:)=ra;
end


model = svmtrain(trainY,trainX,'-t 2 -q -m 1024 -c 256 -g 0.125 ');
%model = svmtrain(trainY,trainX,'-t 2 -q -m 1024 -c 8 -g 2 ');
[plable,accuracy_linear,~] = svmpredict(dumy,testData_mean,model,[])
[alable,accuracy_linear,~] = svmpredict(trainAction,trainData_mean,model,[]);
test_function(plable,Prediction)