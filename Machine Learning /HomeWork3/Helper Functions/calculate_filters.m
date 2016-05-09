function calaculate_filters( W,P,F,S,PO)
%UNTITLED2 Summary of this function goes here
%   Detailed explanation goes here

result = ((W + (2*P) - F)/S) + 1 ; 
if (PO==1)
    result=result/2;
end

disp('Output size:')
disp(result);

end

