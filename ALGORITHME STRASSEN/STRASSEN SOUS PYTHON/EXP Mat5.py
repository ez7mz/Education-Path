from algoFct import *
import numpy as np

# Test avec Matrice de taille 5 * 5

# Creation de deux Matrices
G = np.array(
    [
        [1, 2, 3, 2, 3],
        [0, 4, 6, 2, 3],
        [1, 5, 1, 5, 6],
        [0, 4, 6, 2, 3],
        [9, 10, 11, 6, 7],
    ]
)

H = np.array(
    [
        [5, 2, 1, 6, 7],
        [1, 3, 9, 10, 11],
        [9, 3, 4, 1, 5],
        [1, 5, 1, 5, 6],
        [1, 2, 3, 2, 3],
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
