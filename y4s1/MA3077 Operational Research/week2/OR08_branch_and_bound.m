%%
opts = optimoptions('linprog','Display','none');
f = -[8, 11]; A = [2 2; 1 2]; b = [25, 19];
lb = [0, 0]; ub = [];
[x,fval]= linprog(f,A,b,[],[],lb, ub, opts);
fprintf("optimal soln is x = (%1.2f, %1.2f), p* = %1.2f\n", [x; -fval]) 

%% impose x(2) >= 7
[x,fval] = linprog(f,A,b,[],[],[0, 7],ub, opts);
fprintf("optimal soln with y(2)>=7 is x = (%1.2f, %1.2f), p* = %1.2f\n", [x; -fval]) 

%% impose x(2) <= 6
[x,fval] = linprog(f,A,b,[],[],lb, [inf, 6], opts);
fprintf("optimal soln with y(2)<=6 is x = (%1.2f, %1.2f), p* = %1.2f\n", [x; -fval]) 

%% impose x(2) <= 6 and x(1)<=6
[x,fval] = linprog(f,A,b,[],[],lb, [6, 6], opts);
fprintf("optimal soln with x<=[6, 6] is x = (%1.2f, %1.2f), p* = %1.2f\n", [x; -fval]) 

%% impose x(2) <= 6 and x(1)>=7
[x,fval] = linprog(f,A,b,[],[],[7, 0], [inf, 6], opts);
fprintf("optimal soln with x(2)<=6 & x(1)>=7 is x = (%1.2f, %1.2f), p* = %1.2f\n", [x; -fval]) 

%% using intlinprog
[x,fval] = intlinprog(f,[1, 2],A,b,[],[],lb,ub);
fprintf("optimal soln is x = (%1.2f, %1.2f), p* = %1.2f\n", [x; -fval]) 
