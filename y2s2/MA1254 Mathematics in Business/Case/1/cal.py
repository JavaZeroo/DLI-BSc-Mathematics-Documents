import random
import time
from adjustText import adjust_text
import matplotlib.pyplot as plt
import numpy as np
plt.figure(figsize=(12.80, 5.80))


def calc_abc_from_line_2d(x0, y0, x1, y1):
    a = y0 - y1
    b = x1 - x0
    c = x0*y1 - x1*y0
    return a, b, c


def get_line_cross_point(line1, line2):
    # x1y1x2y2
    a0, b0, c0 = calc_abc_from_line_2d(*line1)
    a1, b1, c1 = calc_abc_from_line_2d(*line2)
    D = a0 * b1 - a1 * b0
    if D == 0:
        return None
    x = (b0 * c1 - b1 * c0) / D
    y = (a1 * c0 - a0 * c1) / D
    # print(x, y)
    return x, y


texts = []

class OPT:
    i = 1

    def __init__(self, cv, cost, tran):
        self.cv = cv
        self.cost = cost
        self.tran = tran
        self.need = 1 / (cv - 24)
        self.wa = 1 + self.need
        self.cost_mix = self.wa*0.5
        self.cost_buy = (cv*cost)*self.need
        self.cost_tran = self.need*tran + self.wa*2
        self.cst = self.cost_mix + self.cost_buy + self.cost_tran
        self.pf = 24*1.2*self.wa - self.cst
        self.t = OPT.i
        OPT.i += 1
        xm = self.wa*20000
        ym = self.sell(xm)

        self.xm = xm
        self.ym = ym
        self.pr = self.ym / self.cst

    def sell(self, weight):
        org = 1 / self.wa * weight
        buy = self.need / self.wa * weight
        costn = (self.cv * self.cost + self.tran)*buy
        prof = (24 * 1.2 - 2.5) * weight - costn
        return prof

    def plotp(self, c):
        x = np.linspace(0, self.wa*20000)
        y = self.sell(x)

        name = 'opt' + str(self.t)
        plt.plot(x, y, color=c, label=name)


        plt.scatter(self.xm, self.ym, color=c, s=10)
        texts.append(plt.text(self.xm, self.ym,
                     f"({str(int(self.xm))},{str(int(self.ym))})"))
        self.plotLoss(self.xm, self.ym, c)

    def loss(self, w, xo):
        return self.mixCV(self.cv, 24, w, xo) * (1.2 - self.cost - self.tran - 2 - 0.5) * w

    def mixCV(self, cv1, cv2, w1, w2):
        print(self.t)
        print((cv1 * w1 + cv2 * w2) / (w1 + w2))
        return (cv1 * w1 + cv2 * w2) / (w1 + w2)

    def plotLoss(self, xo, yo, c):
        x = np.linspace(0, 8000)
        # print(yo)
        y = yo + self.loss(x, xo)
        name = 'opt' + str(self.t)
        plt.plot((x + xo), y, color=c)

    def plotzero(self, c):
        l1 = [0, 0, 1, 0]
        l2 = [self.xm, self.ym, self.xm+5000,
              self.ym + self.loss(5000, self.xm)]
        cp = get_line_cross_point(l1, l2)
        plt.scatter(cp[0], cp[1], color=c)
        texts.append(
            plt.text(cp[0], cp[1], f"({str(int(cp[0]))},{str(int(cp[1]))})"))

    def __str__(self):
        return (str(self.need)+", " + str(self.cst) + ", " + str(self.pf))

    def crossp(self, func, c):
        l1 = [0, 0, self.xm, self.ym]
        l2 = [func.xm, func.ym, (func.xm + 20000),
              (func.ym + func.loss(20000, self.xm))]
        cp = get_line_cross_point(l1, l2)
        plt.scatter(cp[0], cp[1], color=c)
        texts.append(
            plt.text(cp[0], cp[1], f"({str(int(cp[0]))},{str(int(cp[1]))})"))


opt1 = OPT(25, 1.2, 1.2)
opt2 = OPT(27.6, 1.4, 1.2)
opt3 = OPT(25.5, 1.2, 2.5)
opt4 = OPT(28.3, 1.4, 2.5)
opt5 = OPT(29.2, 1.4, 4.5)
opt6 = OPT(31, 1.55, 4.5)

opts = [opt1, opt2, opt3, opt4, opt5, opt6]

c1 = '#FF7F00'
c2 = '#FFFF00'
c3 = '#00FF00'
c4 = '#00FFFF'
c5 = '#0000FF'
c6 = '#8B00FF'
c7 = '#FF0000'
cs = [c1, c2, c3, c4, c5, c6, c7]


def fig1():
    opt1.plotp(c1)
    opt2.plotp(c2)
    opt3.plotp(c3)
    opt4.plotp(c4)
    opt5.plotp(c5)
    opt6.plotp(c6)

    opt5.crossp(opt6, c6)
    opt4.crossp(opt5, c5)
    opt2.crossp(opt4, c4)
    opt3.crossp(opt2, c2)
    opt1.crossp(opt3, c3)

    opt1.plotzero(c1)

    def st(w):
        return 21.99895025 * w
    x = np.linspace(0, 19044)
    y = st(x)
    xm = 19044
    ym = st(19044)
    plt.rcParams['image.interpolation'] = 'nearest'  # 设置 interpolation style

    plt.plot(x, y, color='#FF0000', label="post")

    plt.scatter(xm, ym, color='#FF0000', s=10)
    texts.append(plt.text(xm, ym, f"({str(int(xm))},{str(int(ym))})"))

    plt.xlabel('weight')
    plt.ylabel('profit')
    plt.legend(loc='best')

    plt.ylim(ymin=0)
    # plt.ylim(ymax=460000)
    plt.xlim(xmin=0)
    plt.xlim(xmax=50000)

    adjust_text(texts,
                arrowprops=dict(arrowstyle='-',
                                color='#4c4f50',
                                lw=1)
                )

    plt.savefig(
        f'./exp/test-{time.strftime("%m-%d-%H%M%S",time.localtime())}.png',
        dpi=500
    )

    plt.show()


fig1()
# fig2()
