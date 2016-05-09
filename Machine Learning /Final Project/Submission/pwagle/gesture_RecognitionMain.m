function [ plabel ] = gesture_RecognitionMain( Input_Data_path )
% This program takes 3D Accelerometer data as input, extracts feature from it and uses LibSvm library to recognize those gesture.

load(Input_Data_path); %Load Data from input path

trainData_mean = zeros(2400,71); 
testData_mean = zeros(1200,71); 
trainAction = trainAction'; % training label    
dumy_label =zeros(1200,1); % create dummy labels for svnpredict

%-----Extarcting Features from Train data ----------%
   for k = 1:length(trainData)
     if (k~=342)
   
        temp = trainData{k};   		% Take one cell(single cell represent single gesture) at a time 
		
        temp=resizem(temp,[400 3]); % Each cell as different length hence resize them to a common length 
        temp = temp/9.8;             % Normalize the data with gravitational acceleration 
		
        tempf1=temp(1:300,:);  		% divide cell into two separate frames with 50% overlapping 
        tempf2=temp(100:400,:);
		
		tempft=abs(fft(temp));   	% convert data from time domain to frequency domain using Fast Fourier transform 
        tempft1=abs(fft(tempf1));
		tempft2=abs(fft(tempf2));
    
        mf= (mean(tempft)); % calculate mean
        energy = sum(abs(tempft))/size(tempft,1);
        stdf=std(temp);   % calculate standard deviation from time domain
        maxx= max(temp);  % calculate maximum value from time domain
        minn=min(temp);   % calculate minimum from time domain
        
    
    
    
        crXY=reshape(corrcoef(temp(:,1),temp(:,2),'rows','complete'),1,4); 	% calculate correlation between two axis 
        crYZ=reshape(corrcoef(temp(:,2),temp(:,3),'rows','complete'),1,4); 
        crXZ=reshape(corrcoef(temp(:,1),temp(:,3),'rows','complete'),1,4); 
        energy = sum(abs(tempft))/size(tempft,1);								 % calculate energy from frequency domain
    
        mean1=mean(tempf1);
        stdf1=std(tempf1);
        maxxf1= max(tempf1);
        minnf1=min(tempf1);
        entropy1 = wentropy(abs(tempf1)/size(tempf1,1),'shannon');   % calculate entropy and normalize it with the length of vector
        energy1 = sum(abs(tempft1))/size(tempft1,1);
        
        crXYf1=reshape(corrcoef(tempf1(:,1),tempf1(:,2),'rows','complete'),1,4);
        crYZf1=reshape(corrcoef(tempf1(:,2),tempf1(:,3),'rows','complete'),1,4);
        crXZf1=reshape(corrcoef(tempf1(:,1),tempf1(:,3),'rows','complete'),1,4);
    
        mean2=mean(tempf2);
        stdf2=std(tempf2);
        maxxf2= max(tempf2);
        minnf2=min(tempf2);
        entropy2 = wentropy(abs(tempf2)/size(tempf2,1),'shannon');
        energy2 = sum(abs(tempft2))/size(tempft2,1);
        
        crXYf2=reshape(corrcoef(tempf2(:,1),tempf2(:,2),'rows','complete'),1,4);
        crYZf2=reshape(corrcoef(tempf2(:,2),tempf2(:,3),'rows','complete'),1,4);
        crXZf2=reshape(corrcoef(tempf2(:,1),tempf2(:,3),'rows','complete'),1,4);
    
         % Construct a feature vector by concatenating all features extracted in the order of X,Y,Z axis 
        fv = cat(2,maxxf1(:,1),minnf1(:,1),mean1(:,1),stdf1(:,1),energy1(:,1),maxxf2(:,1),minnf2(:,1),mean2(:,1),stdf2(:,1),energy2(:,1),maxx(:,1),minn(:,1),mf(:,1),stdf(:,1),energy(:,1),maxxf1(:,2),minnf1(:,2),mean1(:,2),stdf1(:,2),energy1(:,2),maxxf2(:,2),minnf2(:,2),mean2(:,2),stdf2(:,2),energy2(:,2),maxx(:,2),minn(:,2),mf(:,2),stdf(:,2),energy(:,2),maxxf1(:,3),minnf1(:,3),mean1(:,3),stdf1(:,3),energy1(:,3),maxxf2(:,3),minnf2(:,3),mean2(:,3),stdf2(:,3),energy2(:,3),maxx(:,3),minn(:,3),mf(:,3),stdf(:,3),energy(:,3),crXYf1,crXYf2,crYZf1,crYZf2,crXZf1,crXZf2,entropy1,entropy2);
        %size(fv)
        trainData_mean(k,:)=fv;
    end
   end

%-----Extarcting Features from Test data ----------%
for k = 1:length(testData)
    
        temp = testData{k};   		% Take one cell(single cell represent single gesture) at a time 
		
        temp=resizem(temp,[400 3]); % Each cell as different length hence resize them to a common length 
        temp = temp/9.8;             % Normalize the data with gravitational accelration 
		
        tempf1=temp(1:300,:);  		% divide cell into two separate frames with 50% overlapping 
        tempf2=temp(100:400,:);
		
		tempft=abs(fft(temp));   	% convert data from time domain to frequency domain using Fast Fourier transform 
        tempft1=abs(fft(tempf1));
		tempft2=abs(fft(tempf2));
    
         mf= (mean(tempft));
         energy = sum(abs(tempft))/size(tempft,1);
         stdf=std(temp);   % calculate standard deviation from time domain
         maxx= max(temp);  % calculate maximum value from time domain
         minn=min(temp);   % calculate minimum from time domain
    
         mean1=mean(tempf1);
         stdf1=std(tempf1);
         maxxf1= max(tempf1);
         minnf1=min(tempf1);
         entropy1 = wentropy(abs(tempf1)/size(tempf1,1),'shannon');   % calculate entropy and normalize it with the length of vector
         energy1 = sum(abs(tempft1))/size(tempft1,1);
        
        crXYf1=reshape(corrcoef(tempf1(:,1),tempf1(:,2),'rows','complete'),1,4);
        crYZf1=reshape(corrcoef(tempf1(:,2),tempf1(:,3),'rows','complete'),1,4);
        crXZf1=reshape(corrcoef(tempf1(:,1),tempf1(:,3),'rows','complete'),1,4);
    
        mean2=mean(tempf2);
        stdf2=std(tempf2);
        maxxf2= max(tempf2);
        minnf2=min(tempf2);
        entropy2 = wentropy(abs(tempf2)/size(tempf2,1),'shannon');
        energy2 = sum(abs(tempft2))/size(tempft2,1);
        
        crXYf2=reshape(corrcoef(tempf2(:,1),tempf2(:,2),'rows','complete'),1,4);
        crYZf2=reshape(corrcoef(tempf2(:,2),tempf2(:,3),'rows','complete'),1,4);
        crXZf2=reshape(corrcoef(tempf2(:,1),tempf2(:,3),'rows','complete'),1,4);
    
        % Construct a feature vector by concatenating all features extracted in the order of X,Y,Z axis 
        fv = cat(2,maxxf1(:,1),minnf1(:,1),mean1(:,1),stdf1(:,1),energy1(:,1),maxxf2(:,1),minnf2(:,1),mean2(:,1),stdf2(:,1),energy2(:,1),maxx(:,1),minn(:,1),mf(:,1),stdf(:,1),energy(:,1),maxxf1(:,2),minnf1(:,2),mean1(:,2),stdf1(:,2),energy1(:,2),maxxf2(:,2),minnf2(:,2),mean2(:,2),stdf2(:,2),energy2(:,2),maxx(:,2),minn(:,2),mf(:,2),stdf(:,2),energy(:,2),maxxf1(:,3),minnf1(:,3),mean1(:,3),stdf1(:,3),energy1(:,3),maxxf2(:,3),minnf2(:,3),mean2(:,3),stdf2(:,3),energy2(:,3),maxx(:,3),minn(:,3),mf(:,3),stdf(:,3),energy(:,3),crXYf1,crXYf2,crYZf1,crYZf2,crXZf1,crXZf2,entropy1,entropy2);
        testData_mean(k,:)=fv;
   
end


    libsvmwrite('train_data',sparse(trainAction),sparse(trainData_mean));  % Seprate trainning data
    [trainY,trainX] = libsvmread('train_data');
 
     model = svmtrain(trainY,trainX,'-t 2 -q -m 1024 -c 256 -g 0.125 ');        % create a model using svmtrain with radial basis function as a Kernel function
    [plabel,accuracy_linear,~] = svmpredict(dumy_label,testData_mean,model,[]); % predict the gestures based on train model 
    

end

