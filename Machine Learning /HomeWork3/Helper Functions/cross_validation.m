function  cross_validation()
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here
load('data\mnist-baseline\imdb.mat');
X =images.data ;
X = reshape(X,[784,20000]);
X=X';
Y =images.labels ;
Y = Y';
X= double(X);
X=X/255;
X=sparse(X);
libsvmwrite('train_data',Y(1:10000,:),X(1:10000,:));
[heart_scale_label,heart_scale_inst] = libsvmread('train_data');

%X2 = X(:,10001:20000);
%Y2 = Y(:,10001:20000);

bestcv = 0;
%bestc = 0;
%bestg=0;

for log2c = -5:2:15,
  for log2g = 1:1,
    cmd = ['-t 0 -v 5 -q -m 1024 -c ', num2str(2^log2c), ' -g ', num2str(2^log2g)];
    cv = svmtrain(heart_scale_label, heart_scale_inst, cmd);
    if (cv >= bestcv),
      bestcv = cv; bestc = 2^log2c; bestg = 2^log2g;
    end
    fprintf('%g %g %g (best c=%g, g=%g, rate=%g)\n', log2c, log2g, cv, bestc, bestg, bestcv);
  end
end

end

