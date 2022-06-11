from algoFct import *
import numpy as np

# Test avec Matrice de taille 3 * 3

# Creation de deux Matrices
G = np.array([[2, 3, 4], [6, 7, 8], [13, 14, 15]])
H = np.array([[1, 2, 3], [5, 6, 7], [9, 10, 11]])


print("\nLa Matrice A :\n")
print(G, "\n")
print("La Matrice B :\n")
print(H, "\n")
print("Le Produit A * B avec A'lgo Strassen : \n")
R = Strassen(G, H)
print(R, "\n")
print("Le Produit A*B avec Méthode Classique : \n")
print(np.dot(G, H))
