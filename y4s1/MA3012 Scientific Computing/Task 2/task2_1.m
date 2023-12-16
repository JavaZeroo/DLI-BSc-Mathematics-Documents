% Define matrices and vectors
A1 = [6, 2, -1; 1, 4, -2; -3, 2, 4];
b1 = [-3; 2; 4];

A2 = [1, 0.8, 0.8; 0.8, 1, 0.8; 0.8, 0.8, 1];
b2 = [3; 2; 1];

A3 = [1, 3; -7, 1];
b3 = [4; 6];

% Solve equations using Jacobi and Gauss-Seidel methods
[x1_jacobi, iter1_jacobi] = jacobi(A1, b1, 1e-5, 1000);
[x1_gauss_seidel, iter1_gauss_seidel] = gauss_seidel(A1, b1, 1e-5, 1000);

[x2_jacobi, iter2_jacobi] = jacobi(A2, b2, 1e-5, 1000);
[x2_gauss_seidel, iter2_gauss_seidel] = gauss_seidel(A2, b2, 1e-5, 1000);

[x3_jacobi, iter3_jacobi] = jacobi(A3, b3, 1e-5, 1000);
[x3_gauss_seidel, iter3_gauss_seidel] = gauss_seidel(A3, b3, 1e-5, 1000);

% Display results
fprintf('For the first system of equations:\n');
fprintf('Solution using Jacobi method: %s, Number of iterations: %d\n', mat2str(x1_jacobi, 5), iter1_jacobi);
fprintf('Solution using Gauss-Seidel method: %s, Number of iterations: %d\n', mat2str(x1_gauss_seidel, 5), iter1_gauss_seidel);

fprintf('For the second system of equations:\n');
fprintf('Solution using Jacobi method: %s, Number of iterations: %d\n', mat2str(x2_jacobi, 5), iter2_jacobi);
fprintf('Solution using Gauss-Seidel method: %s, Number of iterations: %d\n', mat2str(x2_gauss_seidel, 5), iter2_gauss_seidel);

fprintf('For the third system of equations:\n');
fprintf('Solution using Jacobi method: %s, Number of iterations: %d\n', mat2str(x3_jacobi, 5), iter3_jacobi);
fprintf('Solution using Gauss-Seidel method: %s, Number of iterations: %d\n', mat2str(x3_gauss_seidel, 5), iter3_gauss_seidel);


function [x, iter_count] = jacobi(A, b, tol, max_iter)
    % Initialize variables
    n = length(b);
    x = zeros(n, 1);
    iter_count = 0;
    
    % Iteration loop
    for k = 1:max_iter
        x_new = zeros(n, 1);
        iter_count = iter_count + 1;
        
        % Update each variable
        for i = 1:n
            s = A(i, 1:i-1) * x(1:i-1) + A(i, i+1:n) * x(i+1:n);
            x_new(i) = (b(i) - s) / A(i, i);
        end
        
        % Check for convergence
        if norm(x - x_new, inf) < tol
            break;
        end
        
        x = x_new;
    end
end

function [x, iter_count] = gauss_seidel(A, b, tol, max_iter)
    % Initialize variables
    n = length(b);
    x = zeros(n, 1);
    iter_count = 0;
    
    % Iteration loop
    for k = 1:max_iter
        x_new = x;
        iter_count = iter_count + 1;
        
        % Update each variable
        for i = 1:n
            s = A(i, 1:i-1) * x_new(1:i-1) + A(i, i+1:n) * x(i+1:n);
            x_new(i) = (b(i) - s) / A(i, i);
        end
        
        % Check for convergence
        if norm(x - x_new, inf) < tol
            break;
        end
        
        x = x_new;
    end
end
