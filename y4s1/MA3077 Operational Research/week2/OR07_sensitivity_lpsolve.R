require(lpSolve)		# upload library
objective <- c(80, 120)	# set objective coefficients
A <- matrix(c(1, 2, 8, 7, 1, 0, 1, 0, 0, 1),
            nrow=5, byrow=TRUE) 	# constraint coefficients
b <- c(6, 28, 3, 0, 0)		# constraint rhs		
dir  <- c("<=", "<=", "<=", ">=", ">=")	# inequality direction
optimum <-  lp(direction="max",  objective, A, dir,  b, compute.sens = TRUE)
optimum$objval
optimum$solution
optimum$sens.coef.from # sensitivity of the sell price
optimum$sens.coef.to