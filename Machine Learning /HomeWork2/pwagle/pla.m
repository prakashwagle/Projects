 function[W,iters] = pla(X,Y,W0)

 if ~exist('W0','var') % Check weither W0 is presenyt or not
    W = [0;0;0];
else
    W = W0;
 end
 
X=[ones(size(X,1),1),X]; % Adds X0=1 to X vector to calculate W0 weight
flag=1; % Intitalize Flag 
iters=0; % Initialize Iterator
  while(flag~=5) % Checks weither all points are classified correctly 
    Yout=sign(X*W); 
    MC = (Yout==Y); % Checks which samples have been misclassified 
    MC_index=find(~MC); 
   if(size(MC_index,1)==0) % If all samples are classified correctly flag is set
       flag=5;
    else
     [index,i]=datasample(MC_index,1,1);   % Randomly Selects misclassified sample
        W = W + (Y(index,:)*X(index,:)).'; % Update the weights using misclassified sample
        iters=iters+1; % Update iterator count for updating weights
    end
  end
end