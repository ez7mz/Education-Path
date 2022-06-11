import numpy as np
import math
import time

# MESRAR HAMZA - ez7mz -
# ============================================ Definition des fonction ========================================
def pow(x, y):

    return x ** y


# definition de la 1er fonctione du systeme
def fonc1(x, y):

    return (pow(x, 2) + 1) * (pow(x + y, 2)) - 16 * pow(x, 2)


# definition de la 2eme fonctione du systeme
def fonc2(x, y):

    return (pow(y, 2) + 1) * (pow(x + y, 2)) - 9 * pow(y, 2)


# definition de la la deriver de F1 par rapport a x
def f1primX(x, y):

    return (
        4 * pow(x, 3) + 2 * x * pow(y, 2) + 6 * pow(x, 2) * y + 2 * x + 2 * y - 32 * x
    )


# definition de la la deriver de F1 par rapport a y
def f1primY(x, y):

    return (2 * pow(x, 2) * y) + (2 * pow(x, 3)) + 2 * y + 2 * x


# definition de la la deriver de F2 par rapport a x
def f2primX(x, y):

    return (2 * x * pow(y, 2)) + (2 * pow(y, 3)) + 2 * x + 2 * y


# definition de la la deriver de F2 par rapport a y
def f2primY(x, y):

    return (
        (4 * pow(y, 3))
        + (2 * y * pow(x, 2))
        + (6 * pow(y, 2) * x)
        + 2 * y
        + 2 * x
        - 18 * y
    )


# ================================================================================================

# ====================================== Main ======================================================
def func_vect(x, y):

    return [
        (-1) * fonc1(x, y),
        (-1) * fonc2(x, y),
    ]


def Fjacobienne(x, y):
    return np.array([[f1primX(x, y), f1primY(x, y)], [f2primX(x, y), f2primY(x, y)]])


def gauss(A, b):

    return np.linalg.solve(A, b)


def newton_method(Xinit):

    Xi = Xinit[0]

    Yi = Xinit[1]

    jacobian = Fjacobienne(Xi, Yi)

    F_Xn = func_vect(Xi, Yi)

    Xnitr = gauss(jacobian, F_Xn) + Xinit

    return Xnitr


def methode_newton_iterie(x_int, epsilon, maxitr=100):

    cntr = 0

    old_x = x_int
    new_x = newton_method(x_int)
    diff = np.linalg.norm(old_x - new_x)
    print(f"{old_x} FOR X0")
    while diff > epsilon or cntr > maxitr:
        cntr += 1
        new_x = newton_method(old_x)
        diff = np.linalg.norm(new_x - old_x)
        old_x = new_x
        print(f"iteration [{cntr}] : {old_x}")
        time.sleep(0.01)

    CV = new_x
    return CV


# ===========================================================================================================

eps = 0.0001  # valeur de toléronce
X0 = [1, 1]  # vecteur de départ
print(
    f"la solution converge vers : {methode_newton_iterie(X0, eps)} avec un erreur de {eps}"
)
