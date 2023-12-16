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

n_stocks = size(returns, 2);
target_cov = 0.004;
% weights = max_ret(10,expected_returns',covariances,0.004);


covariances_inv = inv(covariances);
e = ones(n_stocks, 1);

a = expected_returns/covariances* expected_returns';
b = expected_returns/covariances * e;
c = e'/covariances * e;
A = (target_cov * c) ^ 2 - c;
B = 2 * b * (1 - c * target_cov ^ 2);
C = (target_cov * b) ^ 2 - a;
delta = B ^ 2 - 4 * A * C
lambda21 = 0.5 / A * (- B + sqrt(delta));
lambda22 = 0.5 / A * (- B - sqrt(delta));
lambda11 =  (b - c * lambda21) / 2;
lambda12 =  (b - c * lambda22) / 2;
weights1 = covariances \ (expected_returns' - lambda21 * e) / (2 * lambda11)
weights2 = covariances \ (expected_returns' - lambda22 * e) / (2 * lambda12)

% port_info()

function port_return, port_std = port_info(weights, ret_ave_vector, ret_cov_matrix)
    %计算投资组合的年化收益率，以及年化波动率
    port_return = weights' * ret_ave_vector;
    port_var = weights' * ret_cov_matrix *weights;
    port_std = sqrt(port_var);
end

function [weights1, weights2] = max_ret(number, ret_ave_vector, ret_cov_matrix, target_vol)
    % number是股票个数；ret_ave_vector是股票的收益率向量，注意是列向量；
    % ret_cov_matrix是股票收益率的协方差矩阵；
    % target_vol是目标波动率，注意是σ。

    ret_cov_matrix_I = inv(ret_cov_matrix);
    e = ones(number, 1);
    
    a = ret_ave_vector' * ret_cov_matrix_I * ret_ave_vector;
    b = ret_ave_vector' * ret_cov_matrix_I * e;
    c = e' * ret_cov_matrix_I * e;
    A = (target_vol * c) ^ 2 - c;
    B = 2 * b * (1 - c * target_vol ^ 2);
    C = (target_vol * b) ^ 2 - a;
    delta = B ^ 2 - 4 * A * C;
    lambda21 = 0.5 / A * (- B + sqrt(delta));
    lambda22 = 0.5 / A * (- B - sqrt(delta));
    lambda11 =  (b - c * lambda21) / 2;
    lambda12 =  (b - c * lambda22) / 2;
    weights1 = ret_cov_matrix_I * (ret_ave_vector - lambda21 * e) / (2 * lambda11);
    weights2 = ret_cov_matrix_I * (ret_ave_vector - lambda22 * e) / (2 * lambda12);
end
