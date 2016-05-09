function[X,Y] = generateData(N)
X=  -1+(1+1)*rand(N,2);
A= -1+(1+1)*rand(1,2);
B= -1+(1+1)*rand(1,2);
Y= sign((B(1,1) - A(1,1)) * (X(1:N,2) - A(1,2)) - (B(1,2) - A(1,2)) * (X(1:N,1) - A(1,1)));
end