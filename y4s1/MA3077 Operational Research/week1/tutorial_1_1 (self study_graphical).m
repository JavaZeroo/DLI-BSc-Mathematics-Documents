%%
clear, close all;

%% define and plot objective function
f = @(x,y) 1.5*115*x + 3*35*y;
[X, Y] = meshgrid(linspace(0,160, 400));
contourf(X, Y, f(X,Y), 40); colorbar;
xlim([0,160]), ylim([0,160]), axis equal, hold on;

%% plot (normalized) gradient
grad_f = [1.5*115; 3*35];
grad_f = (grad_f / norm(grad_f))*50;
quiver(50, 100, grad_f(1), grad_f(2), 'linewidth', 2)

%% determine and plot feasible region
idx1 = X + Y <= 80;                        % constraint 1
idx2 = 115*X + 35*Y <= 5000;               % constraint 2
idx3 = 100*X +120*Y<= 16000;                % constraint 3
idx4 = X >= 0;                              % constraint 4
idx5 = Y >= 0;                              % constraint 5
idx = idx1 & idx2 & idx3 & idx4 & idx5;
Xf = X(idx); Yf = Y(idx);
plot(Xf, Yf, '.b')


%% plot boundary of feasible region
t = linspace(0,160);
lw = 'linewidth';
plot(t, 80-t, '-k',lw,2)                      % constraint bdry 1
plot(t, (5000-115*t)/35, '-k',lw,2)                   % constraint bdry 2
plot(t, (16000-100*t)/120, '-k',lw,2)              % constraint bdry 3
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
%plot(14/9,20/9,'r.','MarkerSize',20)
%exportgraphics(gcf,'feasible_region.png')

%% 3D plot

figure
surf(X,Y,f(X,Y).*idx), shading interp