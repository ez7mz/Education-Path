from numpy import *

# - ez7mz -
# ===================================== Main- Fonction Pour calculée la decomposition QR ============================

# *** Fonction pour Ajouter les ligne et les colonnes de matrice Id à la matrice de House-Holder Obtenue:
def Add_Id(Matrix):
    """
    Matrix : la matrice donnée
    n : la taille de matrice
    B et D : deux vecteurs ligne de Zeros
    on fait la concatenation de matrice donnée avec un ligne et un colone de Zeros
    puis on remplace l'élement de diagonale par  1
    """
    n = len(Matrix)
    B = array([zeros(n)])
    D = array([zeros(n + 1)])
    c = concatenate((B, Matrix), axis=0)
    c = concatenate((D.T, c), axis=1)
    c[0, 0] = 1
    return c


# *** Fonction pour calculée la matrice de House-Holder :
def HS(mat):
    """
    mat : c'est la matrice donnée
    n : la taille de matrice
    Id : la matrice identité de taille n
    e1 : premiere vecteur de base canonique
    u : un vecteur obtenu par la methode au dessus
    H : c'est la matrice de Hous-Holder
    HA : la produit  H*A
    """
    n = len(mat)
    Id = eye(n)

    e1 = array([Id[:, 0]])
    a = array([mat[:, 0]])
    v = a - linalg.norm(a, 2) * e1
    u = v / linalg.norm(v, 2)

    H = eye(n) - 2 * transpose(u).dot(u)
    HA = H.dot(mat)

    return H, HA


# *** Fonction pour calculée la Decomposition QR :
def QR_decomp(A):
    """
    A : la matrice donnée
    Hmax : liste pour stocker les matrice de Hous-Holder
    HAmax : liste pour stocker les matrice les H*A
    M : est un Matrice temporaire
    @ : operateur pour la produit matriciel
    R : la matrice R de decomposition QR (valeur de return)
    Q : la matrice Q de decomposition QR (valeur de rerurn)
    """
    n = len(A)
    Hmax = []
    HAmax = []

    H1, HA1 = HS(A)
    M = HA1[1:, 1:]
    Hmax.append(H1)
    HAmax.append(HA1)

    for i in range(1, n - 1):
        H, HA = HS(M)
        HAmax.append(HA)
        M = HA[1:, 1:]
        k = i
        while k > 0:
            H = Add_Id(H)
            k -= 1
        Hmax.append(H)

    R = eye(n)
    for i in range(n - 1):
        R = Hmax[i] @ R

    Q = transpose(R)
    R = R.dot(A)

    return Q, R


# ==========================================================================================================

# ====================================== Q1 fonction ===============================================================
def valeurP_avec_QR(A, Maxiter=500):
    """
    A : la matrice donnée
    Maxiter : nomber maximum d'iteration
    val_Propre : liste pour stocker les valeur propre
    next_A : A a la iteration suivante
    la dernier boucle est pour extraire les element de diagonale de la matrice A
    a la n'eme iteration
    """
    val_Propre = []
    next_A = A
    for i in range(Maxiter):
        Q, R = QR_decomp(next_A)
        next_A = R @ Q

    print("La matrice A a l'eteration K :\n")
    print(next_A)
    print()

    for i in range(len(A)):
        val_Propre.append(next_A[i, i])

    return val_Propre


# ===========================================================================================

# main execution
A = array([[4, 4, 2, 0], [4, 5, 0, 0], [2, 0, 6, 1], [0, 0, 1, 2]])

valeur = valeurP_avec_QR(A)

print("Les valeurs propres de la matrice A (initiale) sont : \n")
print(valeur)
