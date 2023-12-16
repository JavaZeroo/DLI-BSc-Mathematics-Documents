% Define the function f(x) = x + exp(x)
f = @(x) x + exp(x);

% Initialize parameters
a = -1;
b = 0;
N_0 = 22;
i = 1;
FA = f(a);

% Create an empty array to store the results
results = zeros(N_0, 5);

% Start the bisection algorithm
while i <= N_0
    % Compute p
    p = (a + b) / 2;
    % Compute f(p)
    FP = f(p);
    
    % Store the results in the array
    results(i, :) = [i, a, b, p, FP];
    
    % Update i
    i = i + 1;
    
    % Update a, b, and FA accordingly
    if FA * FP > 0
        a = p;
        FA = FP;
    else
        b = p;
    end
end

% Convert the results to a table and display them
resultsTable = array2table(results, 'VariableNames', {'n', 'a_n', 'b_n', 'c_n', 'f_c_n'});
disp(resultsTable)

% Fixed-Point Iteration to find the root of x + e^x = 0
clear; clc;

% Function definition for g(x) = -e^x
g = @(x) -exp(x);

% Initialization
x_0 = -1;
tolerance = 1e-6;
max_iterations = 100;
x_n = x_0;

% Display header
fprintf('n\tx_n\n');
fprintf('----------------------\n');
fprintf('0\t%.6f\n', x_0);

% Fixed-Point Iteration Algorithm
for n = 1:max_iterations
    % Compute x_{n+1}
    x_next = g(x_n);
    
    % Display the result
    fprintf('%d\t%.6f\n', n, x_next);
    
    % Check the stopping criteria
    if abs(x_next - x_n) < tolerance
        break;
    end
    
    % Update x_n for the next iteration
    x_n = x_next;
end



% Initialize parameters
clear; clc;
p0 = -1;
epsilon = 1e-6;
N0 = 22;
i = 1;

% Display headers for the table
fprintf('Iteration \t p0 \t\t p \t\t |p - p0|\n');

% Start Newton's method
while i <= N0
    % Compute f(p0) and f'(p0)
    fp0 = p0 + exp(p0);
    f_prime_p0 = 1 + exp(p0);

    % Compute new p
    p = p0 - fp0 / f_prime_p0;

    % Display the results
    fprintf('%d \t\t %.6f \t %.6f \t %.6e\n', i, p0, p, abs(p - p0));

    % Check the stopping criteria
    if abs(p - p0) < epsilon
        fprintf('The method found a root after %d iterations: p = %.6f\n', i, p);
        break;
    end

    % Update i and p0
    i = i + 1;
    p0 = p;
end

% If the method didn't find a root, print a failure message
if i > N0
    fprintf('The method failed after %d iterations.\n', N0);
end
