% We want to use linprog from the Optimization Toolbox to solve
% to learn about linprog, type <help linprog> or <doc linprog> in the command window.
% Note that linprog solves minimization problems only!

f = -[1.5*115;3*35];           % objective function
A = [1 1; 115 35; 100 120];    % constraint matrix
b = [80 5000 16000];           % constraing rhs
Aeq = [];               % no equality constraints here
beq = [];               % no equality constraints here
lb = zeros(size(f));    % lower bound x,y >= 0
ub = [];

% call linprog
[x, fval, ~,~,lambda] = linprog(f,A,b,Aeq,beq,lb,ub);
fprintf(['The farmer should plant %4.2f acres of wheat and %4.2f',...
    'acres of barley\n'], x)