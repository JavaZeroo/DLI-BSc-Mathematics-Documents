def gcd(x,y):
    if not isinstance(x,int) or not isinstance(y,int):
        x, y = int(x), int(y)
    while y:
        x,y=y,x%y
    return x

if __name__ == "__main__":
    x = input("x:")
    y = input("y:")
    print(gcd(x, y))