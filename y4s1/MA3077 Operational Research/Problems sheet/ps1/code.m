clear; close all;

% Coefficients of the objective function
c = [-2.80; -3.00];  % We multiply by -1 because MATLAB also tries to minimize

% Coefficients of the inequality constraints (left-hand side)
A = [0.11, 0.08; 0.06, 0.03; 0.02, 0.08];

% Constants of the inequality constraints (right-hand side)
b = [600; 300; 330];

% Bounds for variables
lb = [0; 0];
ub = [Inf; Inf];

% Solve the linear programming problem
options = optimoptions('linprog', 'Algorithm', 'dual-simplex');
[x, fval] = linprog(c, A, b, [], [], lb, ub, options);

% Convert back to maximization and display results
max_income = -fval;
x_G = x(1);
x_T = x(2);

fprintf('%.2f L for Growrite and %.2f L for Tomfood.\n', x_G, x_T);
fprintf('The maximum daily income is £%.2f.\n', max_income);
