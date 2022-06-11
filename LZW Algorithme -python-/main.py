from Function import *

# if any importation error just make the two file in the same directory
#### Function.py and main.py

# First Exemple : (TD3 Exo3 )
S = "bbbabbaabbbb"
dic = {"a":0,"b":1}
print(10*"="," Ex 1 Coding Result : ",10*"=")
print("String : "+S)
res, d = coddingLZW(S, dic)
print("Result : ",res)
print("Final dictionary : ",d)
print()
print(10*"="," Ex 1 Decoding Result : ",10*"=")
String = decoddingLZW([1,2,0,4,1], dic)
print("Result : ",String)

# Second Exemple : 
print("\n")
S1 = "a-bar-array-by-barrayar-bay"
dic1 = {"a":1, "b":2, "r":3, "y":4, "-":5 }
print(10*"="," Ex 2 Coding Result : ",10*"=")
res1, d1 = coddingLZW(S1, dic1)
print("Result : ",res1)
print("Final dictionary : ",d1)
print()
print(10*"="," Ex 2 Decoding Result : ",10*"=")
String1 = decoddingLZW(res1, dic1)
print("Result : ",String1)

print("\n")