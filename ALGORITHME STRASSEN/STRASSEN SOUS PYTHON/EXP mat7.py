from algoFct import *
import numpy as np

# Test avec Matrice de taille 7 * 7

# Creation de deux Matrices
G = np.array(
    [
        [1, 2, 3, 5, 4, 7, 8],
        [0, 4, 6, 7, 8, 1, 2],
        [1, 5, 1, 7, 7, 5, 4],
        [0, 8, 4, 6, 2, 1, 5],
        [1, 4, 2, 5, 6, 1, 7],
        [0, 0, 4, 1, 7, 5, 4],
        [1, 5, 0, 1, 4, 6, 1],
    ]
)

H = np.array(
    [
        [5, 2, 1, 7, 6, 2, 7],
        [1, 3, 9, 8, 1, 4, 5],
        [9, 3, 4, 5, 2, 4, 1],
        [9, 2, 0, 1, 4, 8, 2],
        [5, 1, 2, 3, 8, 6, 1],
        [2, 0, 3, 5, 9, 6, 4],
        [0, 9, 7, 2, 6, 1, 0],
    ]
)

print("\nLa Matrice A :\n")
print(G, "\n")
print("La Matrice B :\n")
print(H, "\n")
print("Le Produit A * B avec A'lgo Strassen : \n")
R = Strassen(G, H)
print(R, "\n")
print("Le Produit A*B avec Méthode Classique : \n")
print(np.dot(G, H))
