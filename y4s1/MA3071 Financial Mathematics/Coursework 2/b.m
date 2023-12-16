S0 = 5.35; 
K = 5.65; 
r = 0.054; 
T = 0.75; 
sigma = 0.3; 

Ms = [1000, 10000, 100000, 1000000, 10000000, 100000000];

gt = 0.5198;

for i = 1:length(Ms)
    M = Ms(i);
    callPrice = MonteCarloCallPrice(S0, K, r, T, sigma, M);
    disp(['For M = ', num2str(M), ', the exotic option price is: ', num2str(callPrice)]);
end

function callPrice = MonteCarloCallPrice(S0, K, r, T, sigma, M)
    % calculate a
    a = r - 0.5 * sigma^2;
    
    % init callValues
    callValues = zeros(M, 1);
    
    % simulate M times
    for i = 1:M
        % generate a random number from standard Brownian motion.
        z = randn;
        
        % calculate ST
        ST = S0 * exp(a * T + sigma * z * sqrt(T));
        
        % calculate call value
        callValues(i) = max(8 * cos(ST) - 5.65, 0);
    end
    
    % calculate call price 
    callPrice = exp(-r * T) * mean(callValues);
end
