
def comput_a(a, n):
    assert n > 0
    ret = 0
    for i in range(n):
        next = 0
        for j in range(i+1):
            next += a * 10**j
        ret += next
    return ret

if __name__ == '__main__':
    res = comput_a(2, 5)
    print(res)    
