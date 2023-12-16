function OR25_Rosenbrock

clear, close all;

% The Rosenbrock function is a difficult one to minimize
f = @(x1, x2) 100*(x2-x1.^2).^2+(1-x1).^2;
[X, Y] = meshgrid(linspace(0.4,1.6,50));
contourf(X,Y,f(X,Y),50); colorbar; hold on;

% derive formula of gradient and Hessian
gradf = @(x1, x2) [-400*(x2-x1.^2).*x1-2*(1-x1); 200*(x2-x1.^2)];
Hf = @(x1, x2) [800*x1.^2-400*(x2-x1.^2)+2,-400*x1;-400*x1, 200];

x0 = [1.2; 1.2];
%Newton(f, gradf, Hf, x0, 5, 1)
SteepestDescent(f, gradf, x0, 99, 1)

x0 = [-1.2; 1];
SteepestDescent(f, gradf, x0, 99, 0)
%Newton(f, gradf, Hf, x0, 5, 0)
end

function SteepestDescent(f, gradf, x0, N, ifplot)
x = x0;
fprintf('iteration ii = 0, x = [%1.4f, %1.4f], f(x) = %1.8f, ', x(1), x(2), f(x(1),x(2)))
if ifplot; plot(x(1), x(2), '*r'); end
for ii = 1:N
    p = -gradf(x(1),x(2));
    s = 1; c1 = 1e-4; r = 0.5;
    x_= x + s*p;
    fprintf('s = 1, ')
    % backtracking
    while f(x_(1), x_(2)) > f(x(1), x(2))+s*c1*(p'*gradf(x(1),x(2)))
        s = r*s;
        x_= x + s*p;
        fprintf('%1.2e ', s)
    end
    x = x_;
    fprintf(['\n' 'iteration ii = %2u, x = [%1.4f, %1.4f], f(x) = %1.8f, '],ii, x(1), x(2), f(x(1),x(2)))
    if ifplot; plot(x(1), x(2), '*r'); end
end
end

function Newton(f, gradf, Hf, x0, N, ifplot)
x = x0;
fprintf('iteration ii = 0, x = [%1.4f, %1.4f], f(x) = %1.8f\n', x(1), x(2), f(x(1),x(2)))
if ifplot; plot(x(1), x(2), '*r'); end
for ii = 1:N
    x = x - Hf(x(1),x(2))\gradf(x(1),x(2));
    fprintf('iteration ii = %u, x = [%1.4f, %1.4f], f(x) = %1.8f\n',ii, x(1), x(2), f(x(1),x(2)))
    if ifplot; plot(x(1), x(2), '*r'); end
end
end
