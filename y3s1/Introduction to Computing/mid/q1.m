
x=[-1 1 2 3];
y=[-3 -1 3 25];
scatter(x,y, 'filled')
hold on
plot(-1:0.1:3, Lagrange_interpol(x, y, -1:0.1:3))
legend({'Data Points', 'Cubic Spline Curve', 'Query Data Point'}, 'Location','northwest')
hold off

function yq=Lagrange_interpol(x,y, xq)          % Q1
n=size(x,2);
L=ones(n,size(xq,2));
for i=1:n
  for j=1:n
     if (i~=j)
        L(i,:)=L(i,:).*(xq-x(j))/(x(i)-x(j));
     end
  end
end
yq=0;
for i=1:n
  yq=yq+y(i)*L(i,:);
end
end
