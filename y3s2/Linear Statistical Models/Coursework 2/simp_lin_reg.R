# 定义简单线性回归函数
simple_linear_regression_function <- function(x, y) {
  
  # calculate the mean of x and y
  mean_x <- mean(x)
  mean_y <- mean(y)
  
  # calculate the a and b
  b <- sum((x - mean_x) * (y - mean_y)) / sum((x - mean_x)^2)
  a <- mean_y - b * mean_x
  
  # return intercept, slope and predictions
  list(intercept = a, slope = b)
}

data <- read.csv("data.csv", header = TRUE)
# 创建数据
x <- data$Daily
y <- data$Sunday

# 调用简单线性回归函数
results <- simple_linear_regression_function(x, y)

# 显示结果
cat("Intercept (a):\n")
print(results$intercept)

cat("\nSlope (b):\n")
print(results$slope)

