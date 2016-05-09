function  cross_validation_ru()
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
libsvmwrite('train_data4',Y(1:10000,:),X(1:10000,:));
[heart_scale_label4,heart_scale_inst4] = libsvmread('train_data4');

bestcv = 0;

for log2c = -1:3,
  for log2g = -4:1,
    cmd = ['-v 5 -q -m 1024 -c ', num2str(2^log2c), ' -g ', num2str(2^log2g)];
    cv = svmtrain(heart_scale_label4, heart_scale_inst4, cmd);
    if (cv >= bestcv),
      bestcv = cv; bestc = 2^log2c; bestg = 2^log2g;
    end
    fprintf('%g %g %g (best c=%g, g=%g, rate=%g)\n', log2c, log2g, cv, bestc, bestg, bestcv);
  end
end

end

