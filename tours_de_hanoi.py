# Réaliser Par MESRAR Hamza IID1 - ez7mz -

# premier appelle de la fonction pour la duxieme déplacement : On déplace les n-1 disques de B vers C
# duxieme appelle de la fonction pour la premier déplacement : On déplace n-1 disques de A vers la tour B


def hanoi(n, debut="A", inter="B", fin="C"):
    if n > 0:
        hanoi(n - 1, debut, fin, inter)
        print("Déplace ", debut, "sur", fin)
        hanoi(n - 1, inter, debut, fin)


print(hanoi(4))
