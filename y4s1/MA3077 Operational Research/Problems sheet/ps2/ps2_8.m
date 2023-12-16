% Problem 8
% Excepted Output
%    -1
%     1
%     0

A = [1 2 3; 4 5 6; 7 8 10];
b = [1;1;1];  
x0 = [0;0;0];  

% Perform one step of Newton's method
new_x = newtonsMethodStep(A, b, x0);

disp(new_x)

function x1 = newtonsMethodStep(A, b, x0)
    gradient = 2 * A' * (A * x0 - b);

    % Calculate the Hessian
    hessian = 2 * A' * A;

    % Perform one step of Newton's method
    x1 = x0 - inv(hessian) * gradient;
end