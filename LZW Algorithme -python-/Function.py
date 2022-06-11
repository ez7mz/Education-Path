# made by : MESRAR Hamza - ez7mz -

# this function helps us to get the key of a value in a dictionary
def get_key(val,my_dict):
    for key, value in my_dict.items():
        if val == value:
            return key

# LZW coding function 
def coddingLZW(S,dic):
    # S : the text we're trying to
    # dic : the working dictionary

    # this if condition solve an unexpected problem that if the first index is 1
    # when you add a value to the dictionary it duplicates the last key instead of increasing it. 
    if list(dic.values())[0] == 0:
        last = len(dic)
    else:
        last = len(dic)+1
    # follow TP1 LZW algorithme instruction
    w = ""
    Final = []
    for k in S:
        wk = w + k
        if wk in dic:
            w = wk
        else:
            Final.append(dic[w])
            dic[wk] = last
            last += 1
            w = k
    if w != "":
        Final.append(dic[w])
    return Final, dic

# LZW decoding function
def decoddingLZW(L,dic):
    # L: The list we're trying to decode
    # the working dictionary
    """
        the Principe is to initialize an empty 'Final' string then search the indexes from the list in the dictionary
        if it exists, append the letter corresponding to this index 
        if it does not exist we code the Final Sting we get then search in the new dictionary
            and append the corresponding letter
        And finally return our Final String
    """
    Final = ""
    for i in L:
        if i in dic.values():
            Final += get_key(i,dic)
        else:
            t, dic = coddingLZW(Final, dic)
            Final += get_key(i,dic)
    
    return Final