function [ Accuracy ] = test_function( correct,test )
%Test Function : this function is used to calculate the accuracy between
%two results
%Here we compare each element of new result with each element of previous result

answer=0;
     for i=1:size(test)
        if (correct(i,1)==test(i,1))
            answer=answer + 1; 
        end
     end
     disp('Accuracy : ');
    Accuracy = ((answer/size(test,1)) * 100);
end

