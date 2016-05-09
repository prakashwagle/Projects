function main
N=[10,50,100,200,500,1000];
   
  for m = 1:6
    tic;
    S = N(:,m);
    Ia=zeros(100,1);
    Ib=zeros(100,1);
    Ia_mean=zeros(6,1);
    Ib_mean=zeros(6,1);
     for j=1:100
    [X,Y]= generateData(S);   % Generates Data
    [WI]=pseudoinverse(X,Y);  % Calculate Initial weight for PLA
    [W, Ia(j,:)]=pla(X,Y);    % PLA with initial W=[0;0;0]
    [W2,Ib(j,:)]=pla(X,Y,WI); % PLA with weight initialized by pseudo inverse
     end
    test(X,Y,W) % Testing function
    D=['    N:',num2str(size(X,1)),'  Average Iteration : ',num2str(mean(Ia))]; %Output String
    disp(D); % Diplays Output for PLA with zero as initial weights
    Ia_mean(m,:)=mean(Ia);
    test(X,Y,W2)
    D=['PIVN: ',num2str(size(X,1)),'  Average Iteration : ',num2str(mean(Ib))]; %Output String
    disp(D); % Display Output for PLA with pseduoinverse weights
     Ib_mean(m,:)=mean(Ib);
    toc;
  end
  % Plots graph to comapre Iteration Red: with intial weight to zero Blue:
  % with intitalized weights
  plot(N,Ia_mean,'-r',N,Ib_mean,'-b'); 
end
