%%
clear, close all;

%% define and plot objective function
f = @(x,y) 35*x + 20*y;
[X, Y] = meshgrid(linspace(0,20, 50), linspace(0,20, 50));
contourf(X, Y, f(X,Y), 40); colorbar; hold on; 

%% plot (normalized) gradient
grad_f = [35; 20];
grad_f = grad_f / norm(grad_f);
quiver(4, 10, grad_f(1), grad_f(2) ,'m', 'linewidth', 1.5, 'maxheadsize', 2)

%% determine and plot feasible region
idx1 = 23*X + 11*Y <= 176;                  % constraint 1
idx2 = 4*X + 4*Y <= 51;                     % constraint 2
idx3 = X >= 0;                              % constraint 3
idx4 = Y >= 0;                              % constraint 4
idx = idx1 & idx2 & idx3 & idx4;
Xf = X(idx); Yf = Y(idx);
plot(Xf(:), Yf(:), '*b')


%% plot boundary of feasible region
t = linspace(0,20);
plot(t, (176-23*t)/11, '-k')                      % constraint bdry 1
plot(t, (51-4*t)/4, '-k')                   % constraint bdry 2
plot(zeros(size(t)), t, '-k')               % constraint bdry 3
plot(t, zeros(size(t)), '-k')               % constraint bdry 4
xlim([0,20]); ylim([0,20])

%% find solution as interection of constraints bdry 1 and 2
% lazy solution: 
x = fzero(@(t) (176-23*t)/11 - (51-4*t)/4, 1);
y = (176-23*x)/11;
plot(x,y,'*r')

print -depsc PS1_2a_feasible_region
