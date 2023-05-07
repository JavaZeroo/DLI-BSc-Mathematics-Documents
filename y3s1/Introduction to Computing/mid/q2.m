
Y=[];
y=@(x) 2*x./(3+x)+rand(size(x));
for x=linspace(1,10,100);
 Y=[Y,y(x)];
end
x=linspace(1,10,100);

hold on

xx=x;
yy=Y;
%%%%%
A=[xx', 1./xx', ones(size(xx'))];

beta=pinv(A)*yy';

plot(x,beta(1)*sin(x) + beta(2)*cos(x) + beta(3))


