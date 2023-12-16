% Problem 3.b
% Excepted Output:
% Optimal solution found.
% 
% Optimal Mixed Strategy for Player 1:
%     0.0625
%     0.2500
%     0.6875
%          0
% 
% Optimal Value:
%     0.0625

clear; close all;

% Payoff Matrix
A = [0, 2, 1, -1;
     3, 4, 0, -5;
     -1, 3, 0, 2;
     -2, -1, 2, 1];

c = [1; zeros(4, 1)];

A_ub = [-ones(4, 1), -A']; % A^T x >= v
b_ub = zeros(4, 1);

A_eq = [0, ones(1, 4)]; 
b_eq = 1;

lb = [-inf; zeros(4, 1)]; 
ub = []; 

options = optimoptions('linprog', 'Algorithm', 'dual-simplex');
[x, fval] = linprog(c, A_ub, b_ub, A_eq, b_eq, lb, ub, options);

optimal_mixed_strategy = x(2:end);
optimal_value = -fval;

disp('Optimal Mixed Strategy for Player 1:');
disp(optimal_mixed_strategy);
disp('Optimal Value:');
disp(optimal_value);
