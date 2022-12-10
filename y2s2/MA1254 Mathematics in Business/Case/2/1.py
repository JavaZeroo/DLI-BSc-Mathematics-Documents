########## 用多进程计算1-100000000的和 ###########
from multiprocessing import Pool

def sum_nums(start,end):
    # 计算分段中的数据之和
    result = 0
    for i in range(start,end+1):
        result += i
    return result

def main():
    pool = Pool(8)
    n = int(1e4)
    r = range(0,10**8+1,n)
    # 以0开头，100000000结尾，步长n为10000。即结果为0,10000，20000,30000……
    results = []
    for j in zip([x+1 for x in r],r[1:]):
        # x+1 for x in r结果为1,10001,20001,30001……
        # r[1:]结果为10000,20000,30000,40000……个数比上面的少1个
        # 用zip函数，结果为（1,10000），（10001,20000），（20001,30000）……列表长度与最短的对象相同，即与r[1:]个数相同
        # 这样就把1-100000000分段了。
        s = pool.apply_async(sum_nums,j)
        # 此处j是一个元组，所以直接在apply_async括号里填j就行
        # 此处得到的结果是multiprocessing.pool.ApplyResult 这种形式，需要用get函数将返回结果的值取出
        results.append(s)
    sum_results = 0
    for res in results:
        sum_results += res.get()
        # 此处res.get()取出的值是int类型

    pool.close()
    pool.join()
    print(sum_results)

if __name__ == '__main__':
    main()

