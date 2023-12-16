clear
filename = 'Historical Prices.xlsx';
opts = detectImportOptions(filename);
data = readtable(filename, opts);

% calculate weekly returns
prices = table2array(data(:, 2:end));
returns = (prices(2:end, :) - prices(1:end-1, :)) ./ prices(1:end-1, :);

% calculate expected returns and variances
expected_returns = mean(returns);
variances = var(returns);

% calculate covariances
covariances = cov(returns);

% set up the optimization problem
n_stocks = size(returns, 2);
H = 2 * covariances;
f = zeros(n_stocks, 1);

Aeq = ones(1, n_stocks);
beq = 1;

options = optimoptions('quadprog', 'Display', 'off');
[x, fval] = quadprog(H, f, [], [], Aeq, beq, [], [], [], options);

optimal_weights = x;
variance_portfolio = fval;
expected_return_portfolio = expected_returns * optimal_weights;

fprintf('Weights of the portfolio:\n');
for i = 1:n_stocks
    fprintf('%s: %f\n', data.Properties.VariableNames{i+1}, optimal_weights(i));
end
fprintf('Variance of the portfolio: %f\n', variance_portfolio);
fprintf('Expected return of the portfolio: %f\n', expected_return_portfolio);

% inv(covariances)*ones(10,1)/(ones(10, 1)' * inv(covariances) * ones(10, 1))