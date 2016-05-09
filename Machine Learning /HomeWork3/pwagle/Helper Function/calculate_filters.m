function calaculate_filters( W,P,F,S,PO)
%Calculates Size of output after data pasees through Conv Filter Layer
%   Detailed explanation goes here

result = ((W + (2*P) - F)/S) + 1 ; 
if (PO>1)
    result=result/PO;
end

disp('Output size:')
disp(result);

end

