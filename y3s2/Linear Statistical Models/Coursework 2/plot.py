import matplotlib.pyplot as plt

# Randomly scattered residuals (ideal case)
data1 = [(1, 0.1), (2, -0.2), (3, 0.3), (4, -0.1), (5, 0.2)]

# Non-linear pattern
data2 = [(1, 0.5), (2, 0.2), (3, -0.1), (4, 0.2), (5, 0.5)]

# Heteroscedasticity
data3 = [(1, 0.1), (2, -0.3), (3, 0.5), (4, -0.9), (5, 1.1)]

# Outliers
data4 = [(1, 0.1), (2, -0.2), (3, 0.3), (4, -0.1), (5, 2)]

# Leverage points
data5 = [(1, 0.1), (2, -0.2), (3, 0.3), (4, -0.1), (10, 0.2)]

datasets = [data1, data2, data3, data4, data5]
titles = ['Randomly Scattered Residuals', 'Non-linear Pattern', 'Heteroscedasticity', 'Outliers', 'Leverage Points']

for i, data in enumerate(datasets):
    x, y = zip(*data)
    plt.scatter(x, y)
    plt.xlabel('Predicted Values')
    plt.ylabel('Residuals')
    plt.title(titles[i])
    plt.axhline(0, color='black', linewidth=0.5)
    plt.show()
