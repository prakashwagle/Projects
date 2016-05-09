function[w] = pseudoinverse(X, Y)
   X=[ones(size(X,1),1),X]; %Adds X0 =1 to X vector
    w=(pinv(X) * Y);  % Calcualte intial weights using pseduo inverse of X with Y
end