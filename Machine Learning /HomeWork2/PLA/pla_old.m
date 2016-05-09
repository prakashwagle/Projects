function[W,iters] = pla_old(X,Y,W)
X=[ones(size(X,1),1),X];
XX=X;
YY=Y;
iters=0;
index=(1:size(X)).';
 while (size(index,1)>0)
   [Z,i] = datasample(index,1,1);
      X1=X(i,:);
      if( Y(i) ~= sign(X1*W))
       W= W + (Y(i)*X1).';
       X=XX;
       Y=YY;
       index=(1:size(X)).';
       iters=iters+1;
     else
        X(i,:)=[];
        Y(i,:)=[];
        index(i,:)=[];
     end
  end
end
