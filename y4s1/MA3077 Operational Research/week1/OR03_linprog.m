% We want to use linprog from the Optimization Toolbox to solve
% max 80x + 120y s.t.
%  x + 2y <=  6
% 8x + 7y <= 28
%  x      <=  3
% x, y >= 0
% to learn about linprog, type <help linprog> or <doc linprog> in the command window.
% Note that linprog solves minimization problems only!

f = -[80;120];          % objective function
A = [1 2; 8 7; 1 0];    % constraint matrix
b = [6 28 3];           % constraing rhs
Aeq = [];               % no equality constraints here
beq = [];               % no equality constraints here
lb = zeros(size(f));    % lower bound x,y >= 0
ub = [];

% call linprog
[x, fval, exitflag, output] = linprog(f,A,b,Aeq,beq,lb,ub);
fprintf(['The optimal amounts of compounds A and B to produce',...,
    ' (in kg)\nare, respectively, (x, y) = (%1.2f, %1.2f).',...,
     '\nThe optimal revenue is £%6.2f\n'], x,-fval)