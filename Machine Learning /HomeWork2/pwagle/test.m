function test(X,Y,W)
X=[ones(size(X,1),1),X];
Yresult = sign (X*W);
MC = (Yresult==Y);
    if(sum(MC)==size(Y,1))
        disp('PASS');
    else
        disp('Fail');
    end    
end