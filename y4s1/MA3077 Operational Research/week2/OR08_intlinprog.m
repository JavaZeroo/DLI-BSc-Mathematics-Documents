f = ones(1,7);
intcon = 1:7;
v = [1 0 0 1 1 1 1];
A = -toeplitz(v([1, 7:-1:2]), v);
b = -[45,50,61,49,60,50,26];
Aeq = [];
beq = [];
lb = zeros(size(f));
ub = 35*ones(1,7);
x = intlinprog(f,intcon,A,b,Aeq,beq,lb,ub)
x = linprog(f,A,b,Aeq,beq,lb,ub)


