print("WELCOME !!")
print("You Have 1000 £. Good Luck !")
balans = 1000
while int(balans) > 0:
    from random import randint

    WinNbr = randint(0, 49)
    # print("\n", WinNbr, "\n")
    nbr = input("On which number do you want to bet ? Between 0 and 49 : ")
    amount = input("How mush do you wante to bet ?")
    if int(amount) > int(balans) and int(balans) <= 0:
        break
    elif int(amount) > int(balans) and int(balans) > 0:
        print("You dont have this mush of money")
        print("Your balans is : ", balans)
        print("\n\n")
        continue
    if int(WinNbr) != int(nbr):
        print("Yhe Winning Number is : ", WinNbr)
        print("Sorry but you lost !! Try Again\n")
        balans = int(balans) - int(amount)
        print("Your Balans Now is : ", int(balans))
        print("\n\n")
    else:
        print("BINGOO !! You Win")
        balans = int(balans) + (50 * int(amount))
        print("Your balans Now is : ", balans)
        print("Try Again!\n\n")

print("You don't have enough money !!")
