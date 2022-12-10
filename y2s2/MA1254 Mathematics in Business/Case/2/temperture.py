import numpy as np
import matplotlib.pyplot as plt
from tqdm import tqdm

gap = 0.001
temperture_rise = [1.2, 1.8]
start_temperature = -23
lowest_temperature = -23
day_cost = 120
night_cost = 40
unit_num = 3
is_summer = 0
allthreshold = [-20.4, -18.1, -18.1]
# hours = 25
# days = hours / 24


class Unit:
    unit_num = 0

    def __init__(self):
        self.unit_num = Unit.unit_num
        Unit.unit_num += 1
        self.cost = 0
        self.isstart = False

    def set_threshold(self, threshold):
        self.threshold = threshold

    def start(self, t):
        time = t % 24
        if time <= 17:
            self.cost += 120 * gap
        else:
            self.cost += 40 * gap

    def check(self, temperture, ti):
        if temperture <= -23:
            self.isstart = False
            return 0
        if temperture > self.threshold or self.isstart is True:
            self.start(ti)
            self.isstart = True
            return -0.8 * gap
        else:
            return 0

    def __str__(self):
        return f"{self.unit_num}:{self.cost}"


def getcycle(threshold):
    time = np.linspace(0, 48, int(48 / gap))

    def reduce(current, ti, rt):
        for unit in units:
            rt += unit.check(current, ti)
        return rt

    units = [Unit() for i in range(unit_num)]
    for i in range(unit_num):
        units[i].set_threshold(threshold[i])

    def temperture(time, is_summer):
        t = []
        rt = 0
        current = start_temperature
        prt = 0
        for ti in time:
            current = start_temperature + temperture_rise[is_summer] * ti + prt
            rt = reduce(current, ti, rt)
            prt = rt
            current = start_temperature + temperture_rise[is_summer] * ti + rt
            # print(f"{current}")
            if current < -23:
                break
            t.append(current)
        return t
    t = temperture(time, is_summer)
    plt.plot(time[:len(t)], t)

    plt.show()
    return len(t) * gap


def getcost(threshold):
    cycle = getcycle(threshold)
    # print(cycle)
    time = np.linspace(0, 24 * cycle, int(24 * cycle / gap))

    def reduce(current, ti, rt):
        for unit in units:
            rt += unit.check(current, ti)
        return rt

    def cost(units):
        cost_all = 0
        for unit in units:
            cost_all += unit.cost
        return cost_all

    units = [Unit() for i in range(unit_num)]
    for i in range(unit_num):
        units[i].set_threshold(threshold[i])

    def temperture(time, is_summer):
        t = []
        rt = 0
        current = start_temperature
        prt = 0
        for ti in time:
            current = start_temperature + temperture_rise[is_summer] * ti + prt

            rt = reduce(current, ti, rt)
            prt = rt
            # print(f"{units[0].isstart}, {units[1].isstart}, {units[2].isstart}")

            current = start_temperature + temperture_rise[is_summer] * ti + rt
            # print(f"{current}")
            # print(current, rt)
            t.append(current)
        return t

    t = temperture(time, is_summer)
    # plt.plot(time, t)
    c = cost(units)
    # print(c)
    return c / cycle


print(getcost(allthreshold))

# mincost = 10000
# minstrategy = []
# for i in tqdm(range(-23, -17)):
#     for j in range(-23, -17):
#         for k in range(-23, -17):
#             c = getcost([i, j, k])
#             if c < mincost:
#                 mincost = c
#                 minstrategy = [i, j, k]
# for i in np.linspace(-23, -17, 25):
#     print(i)
#     for j in np.linspace(-23, -17, 25):
#         for k in np.linspace(-23, -17, 25):
#             c = getcost([i, j, k])
#             if c < mincost:
#                 mincost = c
#                 minstrategy = [i, j, k]
# print(f"Minium cost is {mincost}. Which strategy is{minstrategy}")
