%%
clear, close all;

%% define and plot objective function
f = @(x,y) 80*x + 120*y;
[X, Y] = meshgrid(linspace(0,4, 401));
contourf(X, Y, f(X,Y), 30); colorbar;
xlim([0,4]), ylim([0,4]), axis equal, hold on;

%% plot (normalized) gradient
grad_f = [80; 120];
grad_f = grad_f / norm(grad_f);
quiver(2, 3, grad_f(1), grad_f(2), 'linewidth', 2)

%% determine and plot feasible region
idx1 = X + 2*Y <= 6;                        % constraint 1
idx2 = 8*X + 7*Y <= 28;                     % constraint 2
idx3 = X <= 3;                              % constraint 3
idx4 = X >= 0;                              % constraint 4
idx5 = Y >= 0;                              % constraint 5
idx = idx1 & idx2 & idx3 & idx4 & idx5;
Xf = X(idx); Yf = Y(idx);
plot(Xf, Yf, '.r')


%% plot boundary of feasible region
t = linspace(0,4);
lw = 'linewidth';
plot(t, (6-t)/2, '-k',lw,2)                      % constraint bdry 1
plot(t, (28-8*t)/7, '-k',lw,2)                   % constraint bdry 2
plot(3*ones(size(t)), t, '-k',lw,2)              % constraint bdry 3
plot(zeros(size(t)), t, '-k',lw,2)               % constraint bdry 4
plot(t, zeros(size(t)), '-k',lw,2)               % constraint bdry 5

%% find solution as interection of constraints bdry 1 and 2
% lazy solution: x = fzero(@(x) (6-x)/2 - (28-8*x)/7, 1); y = (6-x)/2;
% exact solution:
% (6-x)/2 - (28-8*x)/7 = 0
% 3 - x/2 = 4 - 8x/7
% 8x/7 - x/2 = 1
% (16-7)x/14 = 1
%y = 3 - x/2 = 3 - 14/18 = 40/18 = 20/9
plot(14/9,20/9,'r.','MarkerSize',20)
%exportgraphics(gcf,'feasible_region.png')

%% 3D plot

figure
surf(X,Y,f(X,Y).*idx), shading interp