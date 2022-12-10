def my_print(x):
    space = " "
    for i in range(x):
        print(f"{space*(x-i+1)}{str(i+1)*(2*i+1)}")

if __name__ == "__main__":
    my_print(9)