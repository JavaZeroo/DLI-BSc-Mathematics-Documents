function OR27_quadratic_penalty
clear, close all;

% example
f = @(x) sum(x);
df = @(x) ones(2,1);
c = @(x) sum(x.^2)-2;
dc = @(x) 2*x';

options = optimoptions('fminunc','Display','off','Algorithm','quasi-newton','SpecifyObjectiveGradient',true);
x0 = [0,0];
for p = 1:5:31
    x = fminunc(@(x) quadraticpenalty(x,f,df,c,dc, p),x0,options);
    x0 = x;
    fprintf('\n p = %2u, x = [%1.3f, %1.3f]\n', p, x(1), x(2))
end
end

function [Q, dQ] = quadraticpenalty(x, f, df, c, dc, p)
Q = f(x) + p/2*c(x).^2;
dQ = df(x) + p*c(x).*dc(x);
end



