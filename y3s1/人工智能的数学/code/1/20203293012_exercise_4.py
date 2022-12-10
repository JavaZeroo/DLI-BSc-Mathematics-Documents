def my_round(x,n):
    if n < 0:
        print("parameter  error")
        return None
    tmp = int(x * 10**n)
    return tmp/10**n

if __name__ == "__main__":
    print(my_round(1234.56789, 3))
    print(my_round(1234.56789, 6))
    print(my_round(1234.56789, -1))