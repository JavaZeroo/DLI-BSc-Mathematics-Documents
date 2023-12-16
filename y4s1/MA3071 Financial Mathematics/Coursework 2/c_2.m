S0 = 5.35;  
K = 5.65;   
r = 0.054;  
T = 1;      
sigma = 0.3; 

Ms = [1000, 10000, 100000, 1000000, 10000000];
n = 100;

for i = 1:length(Ms)
    M = Ms(i);
    asianOptionPrice = AsianCallOptionMonteCarlo(S0, K, r, T, sigma, M, n);
    disp(['For M = ', num2str(M), ', the asian option price is: ', num2str(asianOptionPrice)]);
end

function asianOptionPrice = AsianCallOptionMonteCarlo(S0, K, r, T, sigma, M, n)
    dt = T / n;  

    % generate n time points
    t = linspace(dt, T, n);  

    % generate standard Brownian motion.
    Z = randn(n, M) * sqrt(dt);

    % calculate all stock prices
    S = S0 * exp(cumsum((r - 0.5 * sigma^2) * dt + sigma * Z, 1));

    % calculate each path's geometric mean
    A_T = exp(mean(log(S), 1));

    % calculate call value
    callValues = max(A_T - K, 0);

    % calculate call price 
    asianOptionPrice = exp(-r * T) * mean(callValues);
end
