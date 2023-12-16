close all

x = linspace(-2,2,201);
y = x;
[X,Y] = meshgrid(x,y);
f = @(x,y)  x.^2 + x.*y + y.^2;
c = 1.2;
fx = @(x,y) c*(2*x+y)/(sqrt(5*x.^2+8*x.*y+5*y.^2));
fy = @(x,y) c*(2*y+x)/(sqrt(5*x.^2+8*x.*y+5*y.^2));
surf(X,Y,f(X,Y)), shading interp

figure, contourf(X,Y,f(X,Y),40), colorbar, axis equal, hold on
plot(0,0,'g.','markersize',20)

quiver(1,1,-fx(1,1),-fy(1,1),'r','linewidth',2)
quiver(1,0,-fx(1,0),-fy(1,0),'r','linewidth',2)
quiver(0.34,-0.3,-fx(0.34,-0.3),-fy(0.34,-0.3),'r','linewidth',2)