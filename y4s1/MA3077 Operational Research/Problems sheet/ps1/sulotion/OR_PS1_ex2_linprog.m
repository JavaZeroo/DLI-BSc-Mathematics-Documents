f = -[35;20];
A = [23 11; 4 4];
b = [176 51];
Aeq = [];
beq = [];
lb = zeros(size(f));
ub = [];

[x,fval,exitflag,output] = linprog(f,A,b,Aeq,beq,lb,ub);