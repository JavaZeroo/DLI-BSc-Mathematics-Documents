import math

def compute_sin(x):
    L, time, ret = 1, 0, 0
    
    while abs(L) >= 1e-5:
        L = ((-1)**time * x**(2*time+1))/math.factorial(2*time+1)
        ret += L
        time += 1
    return ret, time


if __name__ == '__main__':
    # res = compute_sin(math.pi)
    res = compute_sin(1)
    print("sin(1):%.5f\nSum time:%d" % res)