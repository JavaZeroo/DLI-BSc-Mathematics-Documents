% use shadow prices to predict how perturbations in the inequality
% constraints affect the optimal objective value p*

% solve the original problem
f = -[80;120]; A = [1 2; 8 7; 1 0]; b = [6 28 3];
[x,fval, ~, ~, lambda] = linprog(f,A,b,[],[],[0;0],[]);
y = lambda.ineqlin; % dual variables for inequality constraints
fprintf('The optimal solution is x = (%1.2f, %1.2f).\n', x)
fprintf('The optimal objective value is p* = %1.2f.\n', -fval)
fprintf('The dual variables are y = (%1.2f, %1.2f, %1.2f).\n', y)

% perturb the problem and check
db = [1 0 0]; [x,f_] = linprog(f,A,b+db,[],[],[0;0],[]);
fprintf('If db = (%1.1f, %1.1f, %1.1f), then p* = %1.2f.\n', [db(:); -f_])
fprintf('Using shadow prices, we predicted p* = %1.2f.\n', -fval+db*y)
