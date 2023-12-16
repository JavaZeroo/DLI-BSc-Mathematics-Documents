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
    disp(['For M = ', num2str(M), ', the call option price is: ', num2str(callPrice)]);
end

% plot error
errors = zeros(length(Ms), 1);
for i = 1:length(Ms)
    M = Ms(i);
    callPrice = MonteCarloCallPrice(S0, K, r, T, sigma, M);
    errors(i) = abs(callPrice - gt);
end

plot(1:length(Ms), log(errors), 'o-');
xlabel('log(M)');
ylabel('log(error)');
title('log(error) vs log(M)');
grid on;
saveas(gcf, 'a_2.png');


function callPrice = MonteCarloCallPrice(S0, K, r, T, sigma, M)
    % calculate a
    a = r - 0.5 * sigma^2;
    
    % init callValues
    callValues = zeros(M, 1);
    
    % simulate M times
    for i = 1:M
        % generate a random number from standard Brownian motion
        z = randn;
        
        % calculate ST
        ST = S0 * exp(a * T + sigma * z * sqrt(T));
        
        % calculate call value
        callValues(i) = max(ST - K, 0);
    end
    
    % calculate call price 
    callPrice = exp(-r * T) * mean(callValues);
end
