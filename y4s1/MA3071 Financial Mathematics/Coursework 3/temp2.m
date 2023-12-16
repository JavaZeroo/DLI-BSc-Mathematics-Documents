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

syms x1 x2 x3 x4 x5 x6 x7 x8 x9 x10 mu lamb
weights = [x1, x2, x3, x4, x5, x6, x7, x8, x9, x10];

% Define the objective function
expected = expected_returns*weights';

% Define the constraints
g1 = sum(weights) - 1 == 0; 
var = weights * covariances * weights'; 
g2 = var - 0.004 == 0; 

W = expected - mu * lhs(g1) - lamb * lhs(g2);

% Calculate the derivatives of the Lagrangian
dL_dx = arrayfun(@(i) diff(W, weights(i)) == 0, 1:length(weights));
dL_dmu = diff(W, mu) == 0;
dL_dlamb = diff(W, lamb) == 0;

system = [dL_dx, dL_dmu, dL_dlamb];
solutions = vpasolve(system, [weights, mu, lamb]);

ret_weights = [solutions.x1, solutions.x2, solutions.x3, solutions.x4, solutions.x5, solutions.x6, solutions.x7, solutions.x8, solutions.x9, solutions.x10];
expected_return = sum(ret_weights .* expected_returns);

ret_cov = ret_weights * covariances * ret_weights';

expected_return,ret_cov