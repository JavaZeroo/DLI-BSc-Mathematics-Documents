%%
function OR_Lect_07_feasible_region
clear, close all;
theta = [0 pi/30 pi/25 -pi/25];

figure;
for ii =1:length(theta)
subplot(2,2,ii); plotme(theta(ii)); ylim([0 4])
end
end

function plotme(theta)
R = [cos(theta) -sin(theta); sin(theta) cos(theta)];
c = R*[80; 120];

% define and plot objective function
f = @(x,y) c(1)*x + c(2)*y;
[X, Y] = meshgrid(linspace(0,4, 50));
contourf(X, Y, f(X,Y), 30); colorbar; hold on;

% plot (normalized) gradient
grad_f = c;
grad_f = grad_f / norm(grad_f);
quiver(2, 3, grad_f(1), grad_f(2), 'linewidth', 2)

% determine and plot feasible region
idx1 = X + 2*Y <= 6;                        % constraint 1
idx2 = 8*X + 7*Y <= 28;                     % constraint 2
idx3 = X <= 3;                              % constraint 3
idx4 = X >= 0;                              % constraint 4
idx5 = Y >= 0;                              % constraint 5
idx = idx1 & idx2 & idx3 & idx4 & idx5;
Xf = X(idx); Yf = Y(idx);
plot(Xf(:), Yf(:), '*b')

% plot boundary of feasible region
t = linspace(0,4);
plot(t, (6-t)/2, '-k')                      % constraint bdry 1
plot(t, (28-8*t)/7, '-k')                   % constraint bdry 2
plot(3*ones(size(t)), t, '-k')              % constraint bdry 3
plot(zeros(size(t)), t, '-k')               % constraint bdry 4
plot(t, zeros(size(t)), '-k')               % constraint bdry 5

% find solution as interection of constraints bdry 1 and 2
f = -[80;120];
A = [1 2; 8 7; 1 0]; b = [6 28 3];
Aeq = []; beq = [];
lb = zeros(size(f)); ub = [];
[x,fval,exitflag,output] = linprog(-c,A,b,Aeq,beq,lb,ub);
plot(x(1), x(2),'*r')
title(['Theta = ', num2str(theta)])
end

