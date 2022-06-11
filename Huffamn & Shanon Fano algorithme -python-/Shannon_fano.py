
# Algorithm Shannon fano for lossless data compression.
# Made by : 
#       * MESRAR Hamza - ez7mz -

"""PrettyPrint
    Dics: dictionnaire
    A function to have a more pleasant and readable display
"""
def PrettyPrint(dics):
    for key,value in dics.items():
        print(str(key)+" : "+str(value))
    
""" SortProbs:
    input_String: This is the message to encode, it is of type String
     This function aims to calculate and order the probabilities of the letters of the message passed as parameter in
     returning them as a sorted dictionary.
"""
def SortProbs(input_String):
    frequencies = {}

    for char in input_String.lower(): 
        if char in frequencies: 
            frequencies[char] += 1
        else: 
            frequencies[char] = 1
            
    for keys,values in frequencies.items():
        frequencies[keys] = values / len(input_String)
    
    # We give our dictionary in an increasing way using the parameter reverse=True
    frequencies = dict(sorted(frequencies.items(), key=lambda item: item[1],reverse=True))
    return frequencies


    """MidTable:
        Freq: A dictionary that contains the values of letters ordered in descending order.
    """
def MidTable(Freq):
    k = 0
    # On prend justement les probabilite et on les passe dans un tableau pour faciliter le calcule
    tab = list(Freq.values())
    # On prend la premier valeur des probabilite 
    sum1 = list(Freq.values())[0]
    sum2 = 0
    # On somme terme par terme
    for i in range(len(tab)-1,k+1,-1):
        sum2 = sum2 + tab[i]
        if sum1<sum2:
            k = k+1
            sum1 = sum1 + tab[k]
    return k


    """Shannon_fano:
        Freq: the ordred dictionary
        res: A dictionary that will take the result returned by the function in a recursive way
    """
def Shannon_fano(Freq,res):
    # Condition d'arret
    if len(list(Freq.values())) == 2:
        i = 0
        for key,_ in Freq.items():
            if i < 1:
                res[key]=str(res.setdefault(key,""))+"0"
            else:
                res[key]=str(res.setdefault(key,""))+"1"
            i = i + 1
        return res
    else:
        i = 0
        milieu = MidTable(Freq)
        d1 = dict(list(Freq.items())[milieu+1:])
        d2 = dict(list(Freq.items())[:milieu+1])
        for key,_ in Freq.items():
            if i <= milieu:
                res[key]=str(res.setdefault(key,""))+"0"
            else:
                res[key]=str(res.setdefault(key,""))+"1"
            i = i + 1 
        if len(d1) > 1: 
            Shannon_fano(d1,res)
        if len(d2) > 1:
            Shannon_fano(d2,res)
        return res

# main
if __name__ == '__main__':
    print("="*20+" We can test the algorithm on the following examples "+"="*20)
    print("="*20+" EXERCICE 1 "+"="*20) 
    S1 = {'x1': 0.5,'x2':0.25,'x3':0.125,'x4':0.0625,'x5':0.03125,'x6':0.03125}
    resultat = {}
    print(S1)
    PrettyPrint(Shannon_fano(S1,resultat))
    print("="*20+" EXERCICE 2 "+"="*20) 
    resultat = {}
    frequence = {'A':0.4,'B':0.2,'C' : 0.12,'D' : 0.1,'E' : 0.1,'F':0.08}
    print(frequence)
    PrettyPrint(Shannon_fano(frequence,resultat))
    print("="*20+" EXERCICE 3 "+"="*20) 
    resultat = {}
    S2 = {'a0':0.2,'a2':0.4,'a3':0.1,'a4':0.1}
    print(S2)
    PrettyPrint(Shannon_fano(S2,resultat))