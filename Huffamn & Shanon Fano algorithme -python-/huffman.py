# In this file, we will see Huffman's algorithm for lossless data compression.
# Made by:
#      * MESRAR Hamza


# Node of a Huffman Tree  

class Nodes:  

    def __init__(self, probability, symbol, left = None, right = None):  

        # probability of the symbol  
        self.probability = probability
        # the symbol  
        self.symbol = symbol
        # the left node  
        self.left = left  
        # the right node  
        self.right = right
        # the tree direction (0 or 1)  
        self.code = ''  
  
""" print the codes of symbols by travelling a Huffman Tree """  

the_codes = {}
def CalculateCodes(node, value = ''):
    # a huffman code for current node  
    newValue = value + str(node.code)  
    if(node.left):  
        CalculateCodes(node.left, newValue)  
    if(node.right):  
        CalculateCodes(node.right, newValue)  
  
    if(not node.left and not node.right):  
        the_codes[node.symbol] = newValue  
           
    return the_codes
  

def HuffmanEncoding(freq):
    symbolWithProbs = freq
    print(symbolWithProbs) 
    the_symbols = symbolWithProbs.keys()  
    the_probabilities = symbolWithProbs.values()  
    print("symbols: ", the_symbols)  
    print("probabilities: ", the_probabilities)  
      
    the_nodes = []  
      
    # converting symbols and probabilities into huffman tree nodes  
    for symbol in the_symbols:  
        the_nodes.append(Nodes(symbolWithProbs.get(symbol), symbol)) 

    while len(the_nodes) > 1:  
        # sorting all the nodes in ascending order based on their probability  
        the_nodes = sorted(the_nodes, key = lambda x: x.probability)  
        # for node in nodes:    
        #       print(node.symbol, node.prob)  
        # picking two smallest nodes  
        right = the_nodes[0]  
        left = the_nodes[1]  
        left.code = 1
        right.code = 0 

        # combining the 2 smallest nodes to create new node  
        newNode = Nodes(left.probability + right.probability, left.symbol + right.symbol, left, right)  
        the_nodes.remove(left)  
        the_nodes.remove(right)  
        the_nodes.append(newNode)  

    huffmanEncoding = CalculateCodes(the_nodes[0])  
    print("symbols with codes", huffmanEncoding)      

    return the_nodes[0]  
   
  
# main
if __name__ == "__main__":
    print("="*20+" TD2 EXERCICE 2 "+"="*20) 
    frequence = {'7':0.2,'4':0.15,'2' : 0.11,'3' : 0.11,'1' : 0.1,'6':0.08,'8':0.07,'9':0.07,'5':0.06,'0':0.05}
    print(frequence)
    HuffmanEncoding(frequence)

