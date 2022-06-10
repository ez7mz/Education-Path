l = int(input("Enter numbers of row : "))
l1 = l
for i in range(1, l + 1):
    for j in range(1, l - i + 1):
        print(" ", end="")
    for j in range(1, 2 * i):
        print("*", end="")
    print()
