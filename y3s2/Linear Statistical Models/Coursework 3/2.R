T=read.table("data.txt")
# print(T)
XA = T[,1]
XV = T[,2]
XD = T[,3]
R = T[,4]


print("############ a) ############")
data <- data.frame(XA, XV, XD, R)
# Fit the polymodel
polymodel <- lm(R ~ XA + XV + XD + I(XA^2) + I(XV^2) + I(XD^2) + I(XA*XV) + I(XA*XD) + I(XV*XD))
print(summary(polymodel))

   
print("############ b) ############")
# Get residuals and fitted values
residuals <- residuals(polymodel)
fitted_values <- fitted(polymodel)

# Plot residuals vs fitted values
par(pin = c(8, 5))
plot(fitted_values, residuals, xlab = "Fitted Values", ylab = "Residuals", main = "Residuals vs Fitted Values", pch = 20, col = "blue")
abline(h = 0, lty = 2)  # Add a horizontal line at zero
lines(lowess(fitted_values, residuals), col = "red")
print(anova(polymodel))
print(anova(lm(R~1)))

# calculate R^2
R2 = sum(anova(polymodel)[1:9,2])/anova(lm(R~1))[,2]
print(R2)


print("############ c) ############")
logR = log(R)
logR_polymodel <- lm(logR ~ XA + XV + XD + I(XA^2) + I(XV^2) + I(XD^2) + I(XA*XV) + I(XA*XD) + I(XV*XD))
print(summary(logR_polymodel))


print("############ d) ############")
residuals <- residuals(logR_polymodel)
fitted_values <- fitted(logR_polymodel)

# Plot residuals vs fitted values
par(pin = c(8, 5))
plot(fitted_values, residuals, xlab = "Fitted Values", ylab = "Residuals", main = "Residuals vs Fitted Values", pch = 20, col = "blue")
abline(h = 0, lty = 2)  # Add a horizontal line at zero
lines(lowess(fitted_values, residuals), col = "red")
print(anova(logR_polymodel))
print(anova(lm(logR~1)))

# calculate R^2
R2 = sum(anova(logR_polymodel)[1:9,2])/anova(lm(logR~1))[,2]
print(R2)


print("############ e) ############")
logR_simplemodel <- lm(logR ~ XA + XV + XD)
print(anova(logR_simplemodel, logR_polymodel))


print("############ f) ############")
data1 <- data.frame(XA=0,XV=1,XD=0)
pred1 <- predict(logR_simplemodel,data1,interval="confidence")
print(pred1)

data2 <- data.frame(XA=0.1,XV=1,XD=0)
pred2 <- predict(logR_simplemodel,data2,interval="confidence")
print(pred2)