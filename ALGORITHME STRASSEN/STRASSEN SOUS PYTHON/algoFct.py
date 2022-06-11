import numpy as np # github : ez7mz
import math

# Si la taille de la matrice est impaire les ligne et le colonnes des ZEROS doit apparitre dans la resultat
# Cette fonction est pour supprimer les zeros et returner la matrice produit que nous voulons
def dlt_zero(
    Matrix, x, y
):  # X : La taille de matrice et Y : la taille de matrice apres l'ajoute des zeros
    i = int(math.log2(y) - 1)
    # print(i)
    Matrix = Matrix[
        : (x + 1) - int(math.log2(x + 1) - i),
        : (x + 1) - int(math.log2(x + 1) - i),
    ]
    return Matrix


# Cette fonction est pour deviser les Matrices A et B en sous_matrice de taille N/2


def split(Matrix):
    n = len(Matrix)

    return (
        Matrix[: n // 2, : n // 2],
        Matrix[: n // 2, n // 2 :],
        Matrix[n // 2 :, : n // 2],
        Matrix[n // 2 :, n // 2 :],
    )


# Cette fonction est pour ajouter des lignes et colonnes des ZEROS Si la taille de Matrice est impaire


def Add_Zeros(Matrix):
    n = len(Matrix)
    if n % 2 == 0:
        return Matrix
    else:
        B = np.array([np.zeros(n)])
        D = np.array([np.zeros(n + 1)])
        c = np.concatenate((Matrix, B), axis=0)
        c = np.concatenate((c, D.T), axis=1)
        return c


# La fonction de l'algorithme Strassen ( est une fonction récursive )


def Strassen(A, B):
    n = len(A)
    k = len(A)
    if n % 2 != 0:
        A = Add_Zeros(A)
        B = Add_Zeros(B)
        n = n + 1

    if n <= 2:
        C = np.dot(A, B)
    else:

        A11, A12, A21, A22 = split(A)
        B11, B12, B21, B22 = split(B)

        M1 = Strassen(A11 + A22, B11 + B22)
        M2 = Strassen(A21 + A22, B11)
        M3 = Strassen(A11, B12 - B22)
        M4 = Strassen(A22, B21 - B11)
        M5 = Strassen(A11 + A12, B22)
        M6 = Strassen(A21 - A11, B11 + B12)
        M7 = Strassen(A12 - A22, B21 + B22)

        C11 = M1 + M4 - M5 + M7
        C12 = M3 + M5
        C21 = M2 + M4
        C22 = M1 - M2 + M3 + M6

        C = np.vstack((np.hstack((C11, C12)), np.hstack((C21, C22))))
        C = dlt_zero(C, k, n)
    return C
