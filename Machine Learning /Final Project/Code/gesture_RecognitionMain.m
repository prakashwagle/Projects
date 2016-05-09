function [ plabel ] = gesture_RecognitionMain( Input_Data_path )
% This program takes 3D Accelerometer data as input, extracts feature from it and uses LibSvm library to recognize those gesture.

load(Input_Data_path); %Load Data from input path

trainData_mean = zeros(2400,71); 
testData_mean = zeros(1200,71); 
trainAction = trainAction';    
dumy_label =zeros(1200,1); % create dummy labels for svnpredict

    for k = 1:length(trainData)
        if (k~=342)  				% Skip cell no.342 as it is empty
        
        temp = trainData{k};   		% Take one cell(single cell represent single gesture) at a time 
		
        temp=resizem(temp,[400 3]); % Each cell as different length hence resize them to a common length 
        temp = temp/9.8;             % Normalize the data with gravitational accelration 
		
        tempf1=temp(1:300,:);  		% divide cell into two separate frames with 50% overlapping 
        tempf2=temp(100:400,:);
		
		tempft=abs(fft(temp));   	% convert data from time domain to frequency domain using Fast Fourier transform 
        tempft1=abs(fft(tempf1));
		tempft2=abs(fft(tempf2));
		
		
		mn=mean(temp);    % calculate mean from time domain
        stdf=std(temp);   % calculate standard deviation from time domain
        maxx= max(temp);  % calculate maximum value from time domain
        minn=min(temp);   % calculate minimum from time domain
        varf=var(temp);   % calculate variance from time domain
        crXY=reshape(corrcoef(temp(:,1),temp(:,2),'rows','complete'),1,4); 	% calculate correlation between two axis 
        crYZ=reshape(corrcoef(temp(:,2),temp(:,3),'rows','complete'),1,4); 
        crXZ=reshape(corrcoef(temp(:,1),temp(:,3),'rows','complete'),1,4); 
        energy = sum(tempft)/size(tempft,1);								 % calculate energy from frequency domain
    
        meanf1=mean(tempf1);
        stdf1=std(tempf1);
        maxxf1= max(tempf1);
        minnf1=min(tempf1);
        entropy1 = wentropy(abs(tempf1)/size(tempf1,1),'shannon');   % calculate entropy and normalize it with the length of vector
        vf1=var(tempf1);
        crXYf1=reshape(corrcoef(tempf1(:,1),tempf1(:,2),'rows','complete'),1,4);
        crYZf1=reshape(corrcoef(tempf1(:,2),tempf1(:,3),'rows','complete'),1,4);
        crXZf1=reshape(corrcoef(tempf1(:,1),tempf1(:,3),'rows','complete'),1,4);
    
        meanf2=mean(tempf2);
        stdf2=std(tempf2);
        maxxf2= max(tempf2);
        minnf2=min(tempf2);
        entropy2 = wentropy(abs(tempf2)/size(tempf2,1),'shannon');
        vf2=var(tempf2);
        crXYf2=reshape(corrcoef(tempf2(:,1),tempf2(:,2),'rows','complete'),1,4);
        crYZf2=reshape(corrcoef(tempf2(:,2),tempf2(:,3),'rows','complete'),1,4);
        crXZf2=reshape(corrcoef(tempf2(:,1),tempf2(:,3),'rows','complete'),1,4);
    
         % Construct a feature vector by concatenating all features extracted in the order of X,Y,Z axis 
         ra= cat(2,maxxf1(:,1),minnf1(:,1),meanf1(:,1),stdf1(:,1),vf1(:,1),maxxf2(:,1),minnf2(:,1),meanf2(:,1),stdf2(:,1),vf2(:,1),maxxf1(:,2),minnf1(:,2),vf1(:,2),meanf1(:,2),stdf1(:,2),maxxf2(:,2),minnf2(:,2),vf2(:,2),meanf2(:,2),stdf2(:,2),maxxf1(:,3),minnf1(:,3),meanf1(:,3),stdf1(:,3),vf2(:,3), maxxf2(:,3),minnf2(:,3),meanf2(:,3),stdf2(:,3),vf2(:,3),maxx,minn,mn,stdf,crXYf1,crYZf1,crXZf1,crXYf2,crYZf2,crXZf2,entropy1,entropy2,energy);
        %size(ra)
        trainData_mean(k,:)=ra;
        end
    end


    for k = 1:length(testData)
    
        temp = testData{k};
       
		temp=resizem(temp,[400 3]); % Each cell as different length hence resize them to a common length 
        temp = temp/10;             % Normalize the data
        tempf1=temp(1:300,:);  		% divide cell into two separate frames with 50% overlapping 
        tempf2=temp(100:400,:);
		tempft=abs(fft(temp));   	% convert data from time domain to frequency domain using Fast Fourier transform 
        tempft1=abs(fft(tempf1));
		tempft2=abs(fft(tempf2));
		
		
		mn=mean(temp);    % calculate mean from time domain
        stdf=std(temp);   % calculate standard deviation from time domain
        maxx= max(temp);  % calculate maximum value from time domain
        minn=min(temp);   % calculate minimum from time domain
        varf=var(temp);   % calculate variance from time domain
        crXY=reshape(corrcoef(temp(:,1),temp(:,2),'rows','complete'),1,4); 	% calculate correlation between two axis 
        crYZ=reshape(corrcoef(temp(:,2),temp(:,3),'rows','complete'),1,4); 
        crXZ=reshape(corrcoef(temp(:,1),temp(:,3),'rows','complete'),1,4); 
        energy = sum(tempft)/size(tempft,1);								 % calculate energy from frequency domain
    
        meanf1=mean(tempf1);
        stdf1=std(tempf1);
        maxxf1= max(tempf1);
        minnf1=min(tempf1);
        entropy1 = wentropy(abs(tempf1)/size(tempf1,1),'shannon');   % calculate entropy and normalize it with the length of vector
        vf1=var(tempf1);
        crXYf1=reshape(corrcoef(tempf1(:,1),tempf1(:,2),'rows','complete'),1,4);
        crYZf1=reshape(corrcoef(tempf1(:,2),tempf1(:,3),'rows','complete'),1,4);
        crXZf1=reshape(corrcoef(tempf1(:,1),tempf1(:,3),'rows','complete'),1,4);
    
        meanf2=mean(tempf2);
        stdf2=std(tempf2);
        maxxf2= max(tempf2);
        minnf2=min(tempf2);
        entropy2 = wentropy(abs(tempf2)/size(tempf2,1),'shannon');
        vf2=var(tempf2);
        crXYf2=reshape(corrcoef(tempf2(:,1),tempf2(:,2),'rows','complete'),1,4);
        crYZf2=reshape(corrcoef(tempf2(:,2),tempf2(:,3),'rows','complete'),1,4);
        crXZf2=reshape(corrcoef(tempf2(:,1),tempf2(:,3),'rows','complete'),1,4);
    
         % Construct a feature vector by concatenating all features extracted in the order of X,Y,Z axis 
         ra= cat(2,maxxf1(:,1),minnf1(:,1),meanf1(:,1),stdf1(:,1),vf1(:,1),maxxf2(:,1),minnf2(:,1),meanf2(:,1),stdf2(:,1),vf2(:,1),maxxf1(:,2),minnf1(:,2),vf1(:,2),meanf1(:,2),stdf1(:,2),maxxf2(:,2),minnf2(:,2),vf2(:,2),meanf2(:,2),stdf2(:,2),maxxf1(:,3),minnf1(:,3),meanf1(:,3),stdf1(:,3),vf2(:,3), maxxf2(:,3),minnf2(:,3),meanf2(:,3),stdf2(:,3),vf2(:,3),maxx,minn,mn,stdf,crXYf1,crYZf1,crXZf1,crXYf2,crYZf2,crXZf2,entropy1,entropy2,energy);
       
		testData_mean(k,:)=ra;
    end


    libsvmwrite('train_data',sparse(trainAction),sparse(trainData_mean));  % Seprate trainning data
    [trainY,trainX] = libsvmread('train_data');
 
     model = svmtrain(trainY,trainX,'-t 2 -q -m 1024 -c 256 -g 0.125 ');        % create a model using svmtrain with radial basis function as a Kernel function
    [plabel,accuracy_linear,~] = svmpredict(dumy_label,testData_mean,model,[]); % predict the gestures based on train model 
    [alabel,accuracy_linear,~] = svmpredict(trainAction,trainData_mean,model,[]);

end

