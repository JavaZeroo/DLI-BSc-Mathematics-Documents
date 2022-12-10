cv_0 = 23

class OPT:
    def __init__(self, cv, cost, tran):
        self.cv = cv
        self.cost = cost
        self.tran = tran
        self.need = 1 / (cv - 23)
        self.wa = 20000 / (1 - self.need)
        self.cst = (cv*cost+tran)*self.need
        self.pf = 24*1.2-2.5-self.cst


opt1 = OPT(25, 1.2, 1.2)
opt2 = OPT(27.6, 1.4, 1.2)
opt3 = OPT(25.5, 1.2, 2.5)
opt4 = OPT(28.3, 1.4, 2.5)
opt5 = OPT(29.2, 1.4, 4.5)
opt6 = OPT(31, 1.55, 4.5)

opts = [opt1, opt2, opt3, opt4, opt5, opt6]

for opt in opts:
    print(opt.wa)

