% Parameters
a = 0; % Start of the interval
b = 1; % End of the interval
alpha = 1; % Initial condition y(0) = 1
N = 10; % Number of steps

% Solve the differential equation using the second-order Taylor method
[T, Y, errors] = taylor2_method(@diff_eq, @dfdt, @dfdy, a, b, alpha, N);

% Display the results and errors
disp('Time Points, Approximate Values, and Errors:');
for i = 1:length(T)
    fprintf('t = %f, y = %f, Error = %f\n', T(i), Y(i), errors(i));
end

% Define the function for the differential equation y' = y^2 * exp(-t)
function dy = diff_eq(t, y)
    dy = y^2 * exp(-t);
end

% Define the partial derivatives of the function
function dfdt_val = dfdt(t, y)
    dfdt_val = -y^2 * exp(-t);
end

function dfdy_val = dfdy(t, y)
    dfdy_val = 2 * y * exp(-t);
end

% Second-order Taylor method implementation
function [T, Y, errors] = taylor2_method(f, dfdt, dfdy, a, b, alpha, N)
    h = (b - a) / N; % Calculate the step size
    T = a:h:b; % Create a vector of time points
    Y = zeros(1, length(T)); % Initialize the solution vector
    Y(1) = alpha; % Set the initial condition
    errors = zeros(1, length(T)); % Initialize the error vector

    % Iterate over each time step
    for i = 1:length(T) - 1
        t = T(i);
        w = Y(i);
        Y(i + 1) = w + h * f(t, w) + (h^2 / 2) * (dfdt(t, w) + dfdy(t, w) * f(t, w)); % Taylor's formula
        exact_val = exp(T(i + 1)); % Exact value
        errors(i + 1) = abs(Y(i + 1) - exact_val); % Calculate error
    end
end

% Time Points, Approximate Values, and Errors:
% t = 0.000000, y = 1.000000, Error = 0.000000
% t = 0.100000, y = 1.105000, Error = 0.000171
% t = 0.200000, y = 1.221005, Error = 0.000397
% t = 0.300000, y = 1.349165, Error = 0.000694
% t = 0.400000, y = 1.490748, Error = 0.001077
% t = 0.500000, y = 1.647153, Error = 0.001569
% t = 0.600000, y = 1.819923, Error = 0.002195
% t = 0.700000, y = 2.010763, Error = 0.002989
% t = 0.800000, y = 2.221550, Error = 0.003991
% t = 0.900000, y = 2.454355, Error = 0.005248
% t = 1.000000, y = 2.711460, Error = 0.006822