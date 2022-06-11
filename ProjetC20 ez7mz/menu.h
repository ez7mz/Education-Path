#include <stdio.h>
#include <windows.h>

void delay(int j)
{   int i,k;
    for(i=0;i<j;i++)
         k=i;
}

//_____________________COLORS ___________________________________


void red(void){
	system("color 0");
	printf("\033[0;31m");
}

void black(void){
	system("color 0");
	printf("\033[0;30m");
}

void green(void){
	system("color 0");
	printf("\033[0;32m");
}

void yellow(void){
	system("color 0");
	printf("\033[0;33m");
}

void blue(void){
	system("color 0");
	printf("\033[0;34m");
}

void purple(void){
	system("color 0");
	printf("\033[0;35m");
}

void cyan(void){
	system("color 0");
	printf("\033[0;36m");
}

void white(void){
	system("color 0");
	printf("\033[0;37m");
}

//________________________________________________________________________

void menu1(void){
	
	cyan();
	printf("\n\n\t\t\t");printf("*************************************************\n");
	printf("\t\t\t");printf("*\t\tPolynome Section\t\t\t*\n");
	printf("\t\t\t");printf("*************************************************\n\n\n");
	printf("\t\t\t\t");printf("1)- Saisir et affichage un polynome.\n");
	printf("\t\t\t\t");printf("2)- L'image d'un X par un polynome.\n");
	printf("\t\t\t\t");printf("3)- Addition de deux polynomes.\n");
	printf("\t\t\t\t");printf("4)- Multiplication de deux Polynomes.\n");
	printf("\t\t\t\t");printf("5)- Developpeur Informations.\n");
	printf("\t\t\t\t");printf("0)- Sortir.!");
	printf("\n\n\n\t\t\t\t");printf("_______Entrer votre choix : ");
	white();
}



void menu2(void){
	
	cyan();
	printf("\n\n\t\t\t");printf("*************************************************\n");
	printf("\t\t\t");printf("*\t\tMesure Section\t\t\t*\n");
	printf("\t\t\t");printf("*************************************************\n\n\n");
	printf("\t\t\t\t");printf(" 1)- saisie et afficher un point.\n");
	printf("\t\t\t\t");printf(" 2)- saisie et afficher un vecteur.\n");
	printf("\t\t\t\t");printf(" 3)- calculer La distance entre deux point.\n");
	printf("\t\t\t\t");printf(" 4)- deplacer un point.\n");
	printf("\t\t\t\t");printf(" 5)- Calculer le produit scalair de deux vecteur.\n");
	printf("\t\t\t\t");printf(" 6)- verifie si trois points est colenair.\n");
	printf("\t\t\t\t");printf(" 7)- L'Equation cartesienne d'une droit.\n");
	printf("\t\t\t\t");printf(" 8)- verifie si un triangle est Rectangle.\n");
	printf("\t\t\t\t");printf(" 9)- verifie si un triangle est Isocele.\n");
	printf("\t\t\t\t");printf("10)- Accedez a un sous programme complimentaire.\n");
	printf("\t\t\t\t");printf("11)- Devloppeur Informations.\n");
	printf("\t\t\t\t");printf("00)- Exit.");
	printf("\n\n\n\t\t\t\t");printf("_______Entrer votre choix : ");
	white();
}


void menu3(void){
	
	cyan();
	printf("\n\n\t\t\t");printf("*************************************************\n");
	printf("\t\t\t");printf("*\t\tsous programme\t\t\t*\n");
	printf("\t\t\t");printf("*************************************************\n\n\n");
	printf("\t\t\t\t");printf(" 1)- Calculer La surface du triangle EGF.\n");
	printf("\t\t\t\t");printf(" 2)- Trier un tableau de 100 pts par distance a l'origine.\n");
	printf("\t\t\t\t");printf(" 3)- Tracer Le shema de figure.\n");
	printf("\t\t\t\t");printf(" 0)- exit.");
	printf("\n\n\n\t\t\t\t");printf("_______Entrer votre choix : ");
	white();
}



