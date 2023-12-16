function OR28_state_constraint
clear, close all;

options = optimoptions('fminunc','Display','iter-detailed','Algorithm','quasi-newton','SpecifyObjectiveGradient',true);

fprintf('\nSolve the 1D problem\n')
x = fminunc(@constrainedfct1d, 0,options);
fprintf('The solution is x = %1.2fpi\n',x/pi)

fprintf('\nSolve the 3D problem\n')
x = fminunc(@constrainedfct3d, [0,0,0], options);
fprintf('The solution is x = [%1.2fpi, %1.2fpi,%1.2fpi]\n',x/pi)

end


function [f, df] = constrainedfct1d(x)

% target function
u_t = [2;2];


% state variable and adjoint variables
A = [cos(x), -sin(x); sin(x), cos(x)];
u = A\[1;0];
p = transpose(A)\(2*(u-u_t));

% objective function and derivative
f = norm(u-u_t)^2;
dA= [-sin(x), -cos(x); cos(x), -sin(x)];
df = -transpose(p)*dA*u;
end

function [f, df] = constrainedfct3d(x)

% target function
u_t = [2;2];

% state and adjoint variables
A = [3+cos(x(1)), -sin(x(2)); sin(x(3)), 3];
u = A\[1;0];
p = transpose(A)\(2*(u-u_t));

% objective function and derivative
f = norm(u-u_t)^2;
df = [p(1)*u(2)*sin(x(1)); p(2)*u(2)*cos(x(2)); -p(2)*u(1)*cos(x(3))];

end



