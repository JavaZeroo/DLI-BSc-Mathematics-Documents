clear, close all;

% set the model parameters
mu = 1/3; lambda = 1/5; n = 6;
%mu = 1/3; lambda = mu; n = 6;

% set up the matrix A such that p' = A*p
e = ones(n,1);
A = spdiags([lambda*e -(lambda+mu)*e mu*e], -1:1, n, n);
A(1,1) = -lambda;
A(end,end) = -mu;

% create an initial condition (e.g. no emails at t=0)
p0 = [1; zeros(n-1, 1)];

% solve for a long time with matlab's solver (e.g. ode45)
[t,p] = ode45(@(t, p) A*p, [0,2e2], p0);

% plot evolution of probabilities and of expected # of emails
figure;
subplot(2,1,1)
plot(t,p)
legend('p0','p1','p2','p3','p4', 'p5')
title('evolution of probabilities')
subplot(2,1,2)
plot(t,p*(0:n-1)')
title('evolution of expected number of emails')

fprintf('The final probabilities are:\n')
fprintf('\t p0 = %1.4f\n', p(end,1)) 
fprintf('\t p1 = %1.4f\n', p(end,2)) 
fprintf('\t p2 = %1.4f\n', p(end,3)) 
fprintf('\t p3 = %1.4f\n', p(end,4)) 
fprintf('\t p4 = %1.4f\n', p(end,5)) 
fprintf('\t p5 = %1.4f\n', p(end,6)) 
fprintf('The final expected number of emails is L = %1.4f\n',p(end,:)*(0:n-1)')


