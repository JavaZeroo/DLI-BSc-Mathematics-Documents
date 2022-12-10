def fib_recursion(x):
    assert x >= 1
    if x == 1 or x == 2:
        return 1
    else:
        return fib_recursion(x-1) + fib_recursion(x-2)


if __name__ == '__main__':
    res = fib_recursion(15)
    print(res)