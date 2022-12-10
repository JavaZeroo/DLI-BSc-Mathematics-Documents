import numpy as np

def find_householder(a):
    norm = np.linalg.norm(a)
    w = np.zeros_like(a)
    w[0] = norm
    w = w - a
    H = np.identity(len(a)) - 2*(w.dot(w.T))/np.square(np.linalg.norm(w))
    return H

def main():
    a = np.array([[-1], [2], [2]])
    print(f'a:\n{a}')
    H = find_householder(a)
    print(f'HouseHolder Marix is: \n{H}\nH*a=\n{H.dot(a)}')

if __name__ == '__main__':
    main()