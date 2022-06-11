import numpy as np

# - ez7mz -

# definiton du fonctions phi(x) et nabla(phi(x))
def phi(A, b, x):
    return 0.5 * np.dot(np.dot(np.transpose(x), A), x) - np.dot(np.transpose(b), x)


def Dphi(A, b, x):
    return np.dot(A, x) - b


# =====================================================================

# parametre d'exercice
A = np.array([[4, 4, 2, 0], [4, 5, 0, 0], [2, 0, 6, 1], [0, 0, 1, 2]])
B = np.array([1, -1, 0, 1])

n = len(A)
Alpha = 0.01  # le pas
epsilon = 1e-9  # tolerance de convergence
max_iters = 1e6  # maximum   iterations

x0 = np.array([1, 0, 0, 1])

# ===========================================================

# Fonction de methode de gradient
def gradient_methode(A, b, x0):
    # initialisation du  vectore de depart
    next_x = x0
    # aficher la vecteur initiale
    print("i = 0 ; phi(x)= " + str(phi(A, b, next_x)))
    i = 1
    # convergence flag
    cvg = False
    print("demarage de recherche\n")
    while i <= max_iters:
        curr_x = next_x
        next_x = curr_x - Alpha * Dphi(
            A, b, curr_x
        )  # formule de X(k+1) sous la methode de gradient

        diff = next_x - curr_x
        # convergence test
        if (
            np.linalg.norm(diff, 2) / (np.linalg.norm(next_x, 2) + np.finfo(float).eps)
            <= epsilon
        ):
            cvg = True
            break
        # afficher la valeur Aproximative de phi(x) durant la recherche du minimum
        # print("i = " + str(i) + " ; phi(x)= " + str(phi(A, b, next_x)))
        i += 1

    # si on a la convergence on affiche la solution comme resultat si non on affiche un message
    # que la suite est diverge
    if cvg:
        print("Le Min obtenue dans " + str(i) + " iterations.")
        print("x_sol =", next_x)
    else:
        print("la suite est diverge.")

    return next_x


# =================================================================================================

# NB : n'est pas un boucle infini mais la recherche de minumum prend beaucoup d'iteration
x_sol = gradient_methode(A, B, x0)
print()
print("La solution par la methode de gradient : ")
print("X =  ", x_sol)
print()

print("La solution par la methode classique : ")
x_sol2 = np.linalg.solve(A, B)
print(x_sol2)
print()

print("Comparaison : True si son approche False si non")
Flag = np.allclose(x_sol, x_sol2)
print(Flag)
