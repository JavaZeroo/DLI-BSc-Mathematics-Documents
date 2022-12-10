import numpy as np


def my_svd(a):
    assert type(a) is np.ndarray
    eigval_u, eigvec_u = np.linalg.eigh(a @ a.T)

    # Sort eigenvalue and eigenvector
    sort_index_u = np.argsort(eigval_u)[::-1]
    u = eigvec_u[:, sort_index_u]

    # Get s
    s_square = np.sort(eigval_u)[::-1]
    s = np.sqrt(s_square)

    # Make sure that the eigenvector are all positive and only
    s[np.where(s <= 0)] = 0
    s = s[:min(a.shape[0], a.shape[1])]

    vT = (gen_s(1 / s, a.shape).T) @ u.T @ a
    return u, s, vT


def gen_s(s, shape):
    s_matrix = np.zeros(shape)
    s_matrix[:len(s), :len(s)] = np.diag(s)
    return s_matrix


def my_pinv(a):
    u, s, v = my_svd(a)
    s = gen_s(1 / s, a.shape)
    return v.T @ s.T @ u.T


if __name__ == '__main__':
    a = np.array([[0, 1], [1, 1], [1, 0]])

    print("\n===================SVD From Numpy======================\n")
    u, s, vT = np.linalg.svd(a, full_matrices=True)
    print(u, gen_s(s, a.shape), vT, sep='\n\n')
    print(np.allclose(a, u @ gen_s(s, a.shape) @ vT))

    print("\n===================SVD From my_svd=====================\n")
    u, s, vT = my_svd(a)
    print(u, gen_s(s, a.shape), vT, sep='\n\n')
    print(np.allclose(a, u @ gen_s(s, a.shape) @ vT))

    print("\n===================pinv From Numpy=====================\n")
    print(np.linalg.pinv(a))
    print("\n===================pinv From my_pinv=====================\n")
    print(my_pinv(a))
