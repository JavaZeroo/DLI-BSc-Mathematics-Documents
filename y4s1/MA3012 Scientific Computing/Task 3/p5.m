% t = 0.2094, Approximation = 0.029818, Actual = 0.029834, Error = 0.000015
% t = 0.3833, Approximation = 0.134326, Actual = 0.134349, Error = 0.000023
% t = 0.5610, Approximation = 0.401644, Actual = 0.401686, Error = 0.000042
% t = 0.7107, Approximation = 0.870837, Actual = 0.870888, Error = 0.000051
% t = 0.8388, Approximation = 1.589406, Actual = 1.589460, Error = 0.000054
% t = 0.9513, Approximation = 2.614023, Actual = 2.614077, Error = 0.000054
% t = 1.0000, Approximation = 3.219050, Actual = 3.219099, Error = 0.000050

% Given parameters
tol = 1e-4;
hmax = 0.25;
hmin = 0.05;
a = 0;
b = 1;
alpha = 0;

% Define the function
f = @(t, y) t .* exp(3 * t) - 2 * y;

% Runge-Kutta-Fehlberg method
[t, y] = rkf45(f, a, b, alpha, tol, hmax, hmin);

% Calculate and print errors at each step
for i = 1:length(t)
    y_actual = actual_solution(t(i));
    error = abs(y_actual - y(i));
    fprintf('t = %.4f, Approximation = %.6f, Actual = %.6f, Error = %.6f\n', t(i), y(i), y_actual, error);
end

% Plotting the results
t_actual = linspace(0, 1, 300);
y_actual_vals = arrayfun(@(t) actual_solution(t), t_actual);
plot(t, y, 'bo-', t_actual, y_actual_vals, 'r');
xlabel('t');
ylabel('y(t)');
title('Runge-Kutta-Fehlberg Method Approximation vs Actual Solution');
legend('RKF45 Approximation', 'Actual Solution');
grid on;

% Actual solution function
function y = actual_solution(t)
    y = (1/5) * t .* exp(3 * t) - (1/25) .* exp(3 * t) + (1/25) .* exp(-2 * t);
end


function [t, y] = rkf45(f, a, b, alpha, tol, hmax, hmin)
    t = a;
    w = alpha;
    h = hmax;
    FLAG = 1;
    output = [];

    while FLAG == 1
        k1 = h * f(t, w);
        k2 = h * f(t + h/4, w + k1/4);
        k3 = h * f(t + 3*h/8, w + 3*k1/32 + 9*k2/32);
        k4 = h * f(t + 12*h/13, w + 1932*k1/2197 - 7200*k2/2197 + 7296*k3/2197);
        k5 = h * f(t + h, w + 439*k1/216 - 8*k2 + 3680*k3/513 - 845*k4/4104);
        k6 = h * f(t + h/2, w - 8*k1/27 + 2*k2 - 3544*k3/2565 + 1859*k4/4104 - 11*k5/40);
        
        R = 1/h * abs(k1/360 - 128*k3/4275 - 2197*k4/75240 + k5/50 + 2*k6/55);
        
        if R <= tol
            t = t + h;
            w = w + 25*k1/216 + 1408*k3/2565 + 2197*k4/4104 - k5/5;
            output = [output; t, w];
        end
        
        delta = 0.84 * (tol/R)^(1/4);
        
        if delta <= 0.1
            h = 0.1 * h;
        elseif delta >= 4
            h = 4 * h;
        else
            h = delta * h;
        end

        if h > hmax
            h = hmax;
        end
        
        if t >= b
            FLAG = 0;
        elseif t + h > b
            h = b - t;
        elseif h < hmin
            FLAG = 0;
            disp('Minimum step size exceeded');
        end
    end
    
    t = output(:, 1);
    y = output(:, 2);
end
