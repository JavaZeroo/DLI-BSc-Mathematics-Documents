% Time Points, Approximate Values, and Errors:
% t = 0.000000, y = 1.000000, Error = 0.000000
% t = 0.100000, y = 1.104873, Error = 0.000298
% t = 0.200000, y = 1.220710, Error = 0.000693
% t = 0.300000, y = 1.348650, Error = 0.001208
% t = 0.400000, y = 1.489949, Error = 0.001876
% t = 0.500000, y = 1.645989, Error = 0.002732
% t = 0.600000, y = 1.818296, Error = 0.003823
% t = 0.700000, y = 2.008548, Error = 0.005205
% t = 0.800000, y = 2.218594, Error = 0.006947
% t = 0.900000, y = 2.450470, Error = 0.009134
% t = 1.000000, y = 2.706413, Error = 0.011869

% Parameters
a = 0; % Start of the interval
b = 1; % End of the interval
alpha = 1; % Initial condition y(0) = 1
N = 10; % Number of steps

% Solve the differential equation using the Midpoint Method
[T, Y, errors] = midpoint_method(@diff_eq, a, b, alpha, N);

% Display the results and errors
disp('Time Points, Approximate Values, and Errors:');
for i = 1:length(T)
    fprintf('t = %f, y = %f, Error = %f\n', T(i), Y(i), errors(i));
end

% Define the function for the differential equation y' = y^2 * exp(-t)
function dy = diff_eq(t, y)
    dy = y^2 * exp(-t);
end

% Midpoint Method implementation
function [T, Y, errors] = midpoint_method(f, a, b, alpha, N)
    h = (b - a) / N; % Calculate the step size
    T = a:h:b; % Create a vector of time points
    Y = zeros(1, length(T)); % Initialize the solution vector
    Y(1) = alpha; % Set the initial condition
    errors = zeros(1, length(T)); % Initialize the error vector

    % Iterate over each time step
    for i = 1:length(T) - 1
        t = T(i);
        w = Y(i);
        % Midpoint formula
        Y(i + 1) = w + h * f(t + h/2, w + (h/2) * f(t, w));
        exact_val = exp(T(i + 1)); % Exact value
        errors(i + 1) = abs(Y(i + 1) - exact_val); % Calculate error
    end
end