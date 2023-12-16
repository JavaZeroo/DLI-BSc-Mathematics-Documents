% Problem 5.b
% Excepted output: 
% The Probability of p_0 is: 
%     0.0079
s = 2;
K = 100;
lambda = 99;
mu = 50;

c = zeros(1, K+1);
c(1) = 1;  % c_0 is always 1
for n = 1:s
    c(n+1) = c(n) * lambda / (n * mu);
end
for n = s+1:K
    c(n+1) = c(n) * lambda / (s * mu);
end

p_0 = 1 / sum(c);

disp('The Probability of p_0 is: ');
disp(p_0);
