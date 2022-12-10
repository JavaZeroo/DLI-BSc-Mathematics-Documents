import numpy as np
from matplotlib.pyplot import *

figure()
r = 1
linestyle = ['b-','k-','m-','r-','y-']
p_values = (0.25, 0.5, 1, 2, 4)
for i,p in enumerate(p_values):
    x = np.arange(-r,r+1e-5,1/128.0)
    y = (r**p - (abs(x)**p))**(1.0/p)
    y = np.hstack((y, -y))
    x = np.hstack((x, x))
    plot(x, y, linestyle[i], label=str(i))
axis('equal')
show()