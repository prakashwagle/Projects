% Name: Prakash Wagle UNCC ID:800889950
load('data/mnist-baseline/imdb.mat');  % Load data from imdb.mat file
X =images.data ;
X = reshape(X,[784,20000]);   % Reshape data from 4d to 2d
X=X';
Y =images.labels ;
Y = Y';
X= double(X);
X=X/255;          % Scale data from 0 to 255 to 0 to 1
X=sparse(X);

libsvmwrite('train_data',Y(1:10000,:),X(1:10000,:));  % Seprate trainning data
[trainY,trainX] = libsvmread('train_data');

libsvmwrite('test_data',Y(10001:20000,:),X(10001:20000,:));  % Seprate testing data 
[testY,testX] = libsvmread('test_data');

model_linear=svmtrain(trainY,trainX,'-t 0 -q -c 0.055');   % Train and Test for Linear Kernel
[~,accuracy_linear,~] = svmpredict(testY,testX,model_linear,[])

model_poly2=svmtrain(trainY,trainX,'-t 1 -q -c 8192 -r 1 -g 0.00048 -d 2'); % Train and Test for Polynomial Degree 2 Kernel
[~,accuracy_poly2,~] = svmpredict(testY,testX,model_poly2,[])

model_poly4=svmtrain(trainY,trainX,'-t 1 -q -c 0.5 -r 1 -g 0.03125 -d 4'); % Train and Test for Polynomial Degree 4 Kernel
[~,accuracy_poly4,~] = svmpredict(testY,testX,model_poly4,[])


model_radial=svmtrain(trainY,trainX,'-t 2 -q -c 8 -g 0.05'); % Train and Test for Radial Basis  Kernel
[~,accuracy_radial,~] = svmpredict(testY,testX,model_radial,[])


