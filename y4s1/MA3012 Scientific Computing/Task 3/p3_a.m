% Time Points, Approximate Values, and Errors:
% t = 0.000000, y = 1.000000, Error = 0.000000
% t = 0.100000, y = 1.100000, Error = 0.005171
% t = 0.200000, y = 1.209485, Error = 0.011917
% t = 0.300000, y = 1.329254, Error = 0.020605
% t = 0.400000, y = 1.460150, Error = 0.031675
% t = 0.500000, y = 1.603065, Error = 0.045656
% t = 0.600000, y = 1.758932, Error = 0.063187
% t = 0.700000, y = 1.928726, Error = 0.085027
% t = 0.800000, y = 2.113455, Error = 0.112086
% t = 0.900000, y = 2.314156, Error = 0.145447
% t = 1.000000, y = 2.531887, Error = 0.186395

% Parameters
a = 0; % Start of the interval
b = 1; % End of the interval
alpha = 1; % Initial condition y(0) = 1
N = 10; % Number of steps

% Solve the differential equation using Euler's method
[T, Y, errors] = euler_method(@diff_eq, a, b, alpha, N);

% Display the results and errors
disp('Time Points, Approximate Values, and Errors:');
for i = 1:length(T)
    fprintf('t = %f, y = %f, Error = %f\n', T(i), Y(i), errors(i));
end

% Define the function for the differential equation y' = y^2 * exp(-t)
function dy = diff_eq(t, y)
    dy = y^2 * exp(-t);
end

% Euler's method implementation
function [T, Y, errors] = euler_method(f, a, b, alpha, N)
    h = (b - a) / N; % Calculate the step size
    T = a:h:b; % Create a vector of time points
    Y = zeros(1, length(T)); % Initialize the solution vector
    Y(1) = alpha; % Set the initial condition
    errors = zeros(1, length(T)); % Initialize the error vector

    % Iterate over each time step
    for i = 1:length(T) - 1
        t = T(i);
        Y(i + 1) = Y(i) + h * f(t, Y(i)); % Euler's formula
        exact_val = exp(T(i + 1)); % Exact value
        errors(i + 1) = abs(Y(i + 1) - exact_val); % Calculate error
    end
end