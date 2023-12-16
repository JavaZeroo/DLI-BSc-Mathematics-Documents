n_values = 1:10;

fprintf(' n         n! Stirling Approx  Absolute Error Relative Error\n');
fprintf('-----------------------------------------------------------------------\n');

for i = 1:length(n_values)
    n = n_values(i);
    n_fact = factorial(n);
    stirling_val = stirling_approx(n);
    absolute_error = abs(n_fact - stirling_val);
    relative_error = absolute_error / n_fact;
    
    fprintf('%2d   %7d   %14f   %12f   %7f\n', n, n_fact, stirling_val, absolute_error, relative_error);
end

function sa = stirling_approx(n)
    sa = n^n * exp(-n) * sqrt(2 * pi * n);
end

%  n         n! Stirling Approx  Absolute Error Relative Error
% -----------------------------------------------------------------------
%  1         1         0.922137       0.077863   0.077863
%  2         2         1.919004       0.080996   0.040498
%  3         6         5.836210       0.163790   0.027298
%  4        24        23.506175       0.493825   0.020576
%  5       120       118.019168       1.980832   0.016507
%  6       720       710.078185       9.921815   0.013780
%  7      5040      4980.395832      59.604168   0.011826
%  8     40320     39902.395453     417.604547   0.010357
%  9    362880    359536.872842    3343.127158   0.009213
% 10   3628800   3598695.618741   30104.381259   0.008296