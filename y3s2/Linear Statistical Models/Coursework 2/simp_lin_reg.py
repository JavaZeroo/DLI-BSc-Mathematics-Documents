import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from scipy.stats import t

def my_simple_linear_regression(x, y):
    # find mean of x and y
    x_mean = np.mean(x)
    y_mean = np.mean(y)
    # find the numerator and denominator
    numerator = 0
    denominator = 0
    for i in range(len(x)):
        numerator += (x[i] - x_mean) * (y[i] - y_mean)
        denominator += (x[i] - x_mean) ** 2
    # find slope and intercept
    slope = numerator / denominator
    intercept = y_mean - (slope * x_mean)
    return slope, intercept

def calculate_RSS(y, y_hat):
    RSS = np.sum((y - y_hat) ** 2)
    return RSS




def main():
    data = pd.read_csv("data.csv")
    X = np.array(data.iloc[:, 1]).reshape(-1, 1)
    Y = np.array(data.iloc[:, 2]).reshape(-1, 1)
    plt.scatter(X, Y)
    b, a = my_simple_linear_regression(X, Y)
    # draw the line
    print(b, a)
    x = np.array([np.min(X), np.max(X)]).reshape(-1, 1)
    y = a + b * x
    plt.plot(x, y, color="red")
    plt.xlabel("Daily")
    plt.ylabel("Sunday")
    plt.savefig("simple_linear_regression.png")
    print(f"Len of data: {len(X)}")
    RSS = calculate_RSS(Y, a + b * X)
    print(f"RSS: {calculate_RSS(Y, a + b * X)}")
    print(f"RSS: {np.sum((Y - np.mean(Y)) ** 2) - np.sum((X - np.mean(X)) * (Y - np.mean(Y))) ** 2 / np.sum((X - np.mean(X)) ** 2)}")
    sigma2 = calculate_RSS(Y, a + b * X) / (len(X) - 2)
    sigma = np.sqrt(sigma2)
    print(f"sigma^2: {sigma2}")
    print(f"sigma: {sigma}")
    print(f"S_xx: {np.sum((X - np.mean(X)) ** 2)}")
    S_yy=np.sum((Y - np.mean(Y)) ** 2)
    print(f"S_yy: {S_yy}")
    print(f"S_xy: {np.sum((X - np.mean(X)) * (Y - np.mean(Y)))}")
    print(f"t for b=0 :{b * np.sqrt(np.sum((X - np.mean(X)) ** 2)) / np.sqrt(calculate_RSS(Y, a + b * X) / (len(X) - 2))}")
    print(f"t for a=0 :{a/(sigma * np.sqrt(1/len(X)+np.mean(X)**2/np.sum((X - np.mean(X)) ** 2)))}")
    print(f"95%Confidence Interval of a:({a-2.07*np.sqrt(calculate_RSS(Y, a + b * X) / (len(X) - 2))*np.sqrt(1/len(X) + np.mean(X) ** 2 / np.sum((X - np.mean(X)) ** 2))},{a+2.07*np.sqrt(calculate_RSS(Y, a + b * X) / (len(X) - 2))*np.sqrt(1/len(X) + np.mean(X) ** 2 / np.sum((X - np.mean(X)) ** 2))})")
    print(f"95%Confidence Interval of b:({b-2.07*np.sqrt(calculate_RSS(Y, a + b * X) / (len(X) - 2))/np.sqrt(np.sum((X - np.mean(X)) ** 2))},{b+2.07*np.sqrt(calculate_RSS(Y, a + b * X) / (len(X) - 2))/np.sqrt(np.sum((X - np.mean(X)) ** 2))})")
    rho2 = (S_yy - RSS)/S_yy
    rho = np.sqrt(rho2)
    print(f"rho: {rho}")
    print(f"T: {rho * np.sqrt((len(X) - 2) / (1 - rho ** 2))}")
    temp_1 = 2*1.96/np.sqrt(len(X)-3)
    temp_2 =(1+rho)/(1-rho)
    print(f"({(np.exp(-temp_1)*temp_2 - 1)/(np.exp(-temp_1)*temp_2 + 1)},{(np.exp(temp_1)*temp_2 - 1)/(np.exp(temp_1)*temp_2 + 1)})")
    
    x_0 = 0.5
    pred = a + b * x_0
    t_value_CI = (2.07 * sigma * np.sqrt(1/len(X) + (x_0 - np.mean(X)) ** 2 / np.sum((X - np.mean(X)) ** 2)))
    t_value_pred = (2.07 * sigma * np.sqrt(1+ 1/len(X) + (x_0 - np.mean(X)) ** 2 / np.sum((X - np.mean(X)) ** 2)))
    print(f"Prediction: {pred}")
    print(f"95Confidence t_value_CI: {t_value_CI}")
    print(f"95%Confidence Interval:({pred-t_value_CI},{pred+t_value_CI})")
    print(f"Range of CI: {2*t_value_CI}")
    print(f"95Confidence t_value_pred: {t_value_pred}")
    print(f"95%Prediction Interval:({pred-t_value_pred},{pred+t_value_pred})")
    print(f"Range of PI: {2*t_value_pred}")
    
if __name__ == "__main__":
    main()