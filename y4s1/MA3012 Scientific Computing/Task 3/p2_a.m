

% Initialize variables
n = 6;
Ln = 1; % Starting value for L_6
pi_true = pi; % MATLAB's built-in value of pi

% Initialize a matrix to store the results
results = [];

% Iterate to calculate
for i = 0:21
    n_current = n * 2^i; % Current number of sides
    pi_approx = n_current * Ln / 2; % Approximation of pi
    abs_error = abs(pi_true - pi_approx); % Absolute error
    
    % Store the results in the matrix
    results = [results; n_current, Ln, abs_error];
    
    % Update Ln for the next iteration
    Ln = next_side_length(Ln);
end

% Write results to a CSV file
writematrix(results, 'pi_approximations.csv');

% Define a function to calculate L_{2n} from L_n
function Ln = next_side_length(Ln)
    Ln = sqrt(2 - sqrt(4 - Ln^2));
end