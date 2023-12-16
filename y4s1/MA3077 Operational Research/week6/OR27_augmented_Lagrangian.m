function OR27_augmented_Lagrangian
clear, close all;

% example
f = @(x) sum(x);
df = @(x) ones(2,1);
c = @(x) sum(x.^2)-2;
dc = @(x) 2*x';

options = optimoptions('fminunc','Display','off','Algorithm','quasi-newton','SpecifyObjectiveGradient',true);
x0 = [0,0];
y = 0;
for p = 1:5:31
    x = fminunc(@(x) augmentedLagrangian(x,f,df,c,dc,y,p),x0,options);
    y = y - p*c(x);
    x0 = x;
    fprintf('\n p = %2u, x = [%1.3f, %1.3f], y = %1.2f\n', p, x(1), x(2), y)
end
end

function [L, dL] = augmentedLagrangian(x, f, df, c, dc, y, p)
L = f(x) - y*c(x) + p/2*c(x).^2;
dL = df(x) - y*dc(x) + p*c(x).*dc(x);
end



