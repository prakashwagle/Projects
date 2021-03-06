function  cross_validation_poly4()
%Cross validation for Polynomial Degree 4 Kernel
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

libsvmwrite('train_data2',Y(1:10000,:),X(1:10000,:));
[heart_scale_label,heart_scale_inst] = libsvmread('train_data2');


bestcv = 0;

for log2c = -1:3
  for log2g = -4:1
    cmd = ['-t 1 -d 4 -v 5 -q -m 1024 -c ', num2str(2^log2c), ' -g ', num2str(2^log2g)];
    cv = svmtrain(heart_scale_label2, heart_scale_inst2, cmd);
    if (cv >= bestcv),
      bestcv = cv; bestc = 2^log2c; bestg = 2^log2g;
    end
    fprintf('%g %g %g (best c=%g, g=%g, rate=%g)\n', log2c, log2g, cv, bestc, bestg, bestcv);
  end
end

end

