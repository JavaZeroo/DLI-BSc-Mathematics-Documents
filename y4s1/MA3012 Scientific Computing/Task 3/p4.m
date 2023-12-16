x_i = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
y_i = [34.6588, 40.3719, 14.6448, -14.2721, -13.3570, 24.8234, 75.2795, 103.5743, 97.4847, 78.2392];

figure;

plot(x_i, y_i, 'ko', 'MarkerFaceColor', 'k'); 
hold on;

colors = ['b', 'r', 'g', 'm']; 

for degree = 3:6
    p = polyfit(x_i, y_i, degree);

    x_fit = linspace(1, 10, 400); 
    y_fit = polyval(p, x_fit);

    plot(x_fit, y_fit, colors(degree-2), 'DisplayName', ['Degree ' num2str(degree)]);
end

legend('show');
title('Comparison of Polynomial Fits');
xlabel('x');
ylabel('y');
hold off;
