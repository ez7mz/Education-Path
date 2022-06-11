#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <math.h>
#include <windows.h>
#include "menu.h"    // Toutes les fonctions menu sont prédéfinies sur cette en-tete
#define DegreMax 20


//_______ fonction puissance ou utilisé pow(double x, double y) dans la bibliothèque math.h ________________________
float puis(float n,int m){
	float p=1;
	int i;
	for(i=0;i<m;i++){
		p*=n;
	}
}
//_____________________________________________

//____________ Déclaration de structure et prototype des fonctions ____________

typedef struct {
	int degre;
	float coeff[DegreMax+1];	//Déclaration de structure utilisé
} pol;

void NulPoly(pol* p);
void SaisiePoly(pol *p);                    //prototype de fonction de saisie
void voirPoly(pol p);					   //prototype de fonction d'affichage
void voirPoly_2(pol p,float x);			  //prototype de fonction de calculationde l'image de X par un Polynome
void AddPoly(pol p1, pol p2);			 //prototype de fonction d'addition des Polynomes
void MultPoly(pol p1, pol p2);			//prototype de fonction de la produit des Polynomes
      			   //prototype de la Procédure de la polynome NULL
int verif(pol p);           	      //prototype de fonction de verification Poltnome NULL

//_______________________________ main code ________________________________________________

int main(){
	
	pol A,B;
	int choix;
	float x;
	
	
	menu1();			   //procedure pour afficher Le menu (prédifinit dans "menu.h")
	scanf("%d",&choix);   //lire un choix entrer l'utilisateur
	system("cls");         // fonction définie dans windows.h> pour videz l'écrant
	switch(choix){
		case 1:        // case 1 Pour saisie et affichage des Polynomes
			printf("La saisie et l'affichage d'un Polynome : \n");
			printf("Le Polynome sera de type : A0*X^0 + A1*X^1 + ....... + An*X^n .!\n\n");
			SaisiePoly(&A);
			printf("\n");
			voirPoly(A);
			break;
		case 2:			// case 2 qui permet de calculer P(x) d'un certain X
			printf("Calculation des images de X : \n\n");
			SaisiePoly(&A);
			printf("\n");
			voirPoly(A);
			printf("\n\n\t\t");
			printf("entre un nomber X : ");
			scanf("%f",&x);
			voirPoly_2(A,x);
			break;
		case 3:			// case 3 pour l'addition des polynomes
			printf("L'addition des Polynome : \n\n");
			SaisiePoly(&A);
			printf("\n");
			voirPoly(A);
			printf("\n\n");
			SaisiePoly(&B);
			printf("\n");
			voirPoly(B);
			printf("\n\nLa polynome Somme est : \n\t\t");
			AddPoly(A,B);
			break;
		case 4:    // case 4 pour la multiplication des polynomes
			printf("Le Produit des Polynome : \n\n");
			SaisiePoly(&A);
			printf("\n");
			voirPoly(A);
			printf("\n\n");
			SaisiePoly(&B);
			printf("\n");
			voirPoly(B);
			printf("\n\nLa polynome Produit est : \n\t\t");
			MultPoly(A,B);
			break;
		case 5:		// case 5 Pour Les informations personnel
			printf("\n\n\n\t\t\t\tHAMZA MESRAR\tAPI-2\tGRP 5 - 2020 - ez7mz");
			getch();
			break;
		case 0:    // case 0 pour Sortir est fermer Le Program
			printf("\n\n\t\t__________ MESRAR HAMZA vous remercie de votre visite! __________\n\n");
			getch();
			break;
		default :	// pour incorrect case
			red();printf("\n\n\t\tInvalid choix!\t\ttry again...");
			delay(1000000000);
			system("cls");
			blue();
			main();  // exécution fonction main a nouveau
	}
	
	return 0;
}

//_______________________________________________________________________________________________________


//__________________________________________________ Les Fonctions de Programme _____________________________________

	
//____________ fonction (procedure) de saisie d'une polynome ____________
void SaisiePoly(pol *p){
	
	int i;
	
	printf("entrer le degre de polynome (max 20) : ");
	scanf("%d",&p->degre);
	if(p->degre == -1)
		printf("Le polynome est NUL!");
	for(i=0;i<p->degre+1;i++){							// p est un pointeur du type strict poli alors nous utilisons utilisé p->membre
		printf("entrer l'coeff A%d : ",i);
		scanf("%f",&p->coeff[i]);
	}
	
}

//____________ fonction (procedure) d'affichage d'une polynome ___________________
void voirPoly(pol p){
	int i;
	printf("P(X) = ");
	for(i=0;i<p.degre+1;i++){
		printf("%.2f * X^%d ",p.coeff[i],i);
		if(i<p.degre)
			printf(" + ");  // Juste pour séparait le printf pour rendre plus lisible
	}
}

//_________________ fonction (procedure) qui permet de calculer P(x) d'un certain X __________________
void voirPoly_2(pol p,float x){
	
	int i;
	float s=p.coeff[0];
	
	for(i=1;i<=p.degre;i++){
		s = s + (p.coeff[i] * pow(x,i));    //on applique la relation de la somme de Aix^i avec i à 0 jusqu'à degré de P
		//printf("%f\t",s);                            Tq : Ai et le coefficient  -----  et : i le degré Du coefficient Ai
	}
	
	printf("P(%.2f) = %.2f",x,s);
}


//___________ fonction de calcule l'addition de deux polynome _____________________
void AddPoly(pol p1, pol p2){
	
	pol p;
	int i;					//pour la somme on a trois cas possibles si deg(P) > deg (Q) ou le contraire et si le deg(P) = deg(Q)
	
	if(p1.degre > p2.degre){   //          et finalement on applique La relation qui dit que l'addition est égale à :
		p.degre = p1.degre;    //                 LA somme des coefficients de même degré * X ^degré C -à-d :
		p.coeff[p1.degre] = p1.coeff[p1.degre]; //     Ai = (Pi+Qi) * X^i  de i=0 ---> i<degre de sup(deg(P),deg(Q)) 
		for(i=0;i<p.degre;i++){					//
			p.coeff[i] = (p1.coeff[i]+p2.coeff[i]);
		}
	}else if(p1.degre == p2.degre){
		p.degre = p1.degre;
		for(i=0;i<p.degre+1;i++){
			p.coeff[i] = (p1.coeff[i]+p2.coeff[i]);
		}
	}else{
		p.degre = p2.degre;
		p.coeff[p2.degre] = p2.coeff[p2.degre];
		for(i=0;i<p.degre;i++){
			p.coeff[i] = (p1.coeff[i]+p2.coeff[i]);
		}
	}
	voirPoly(p);   // pour affichage *par la fonction d'affichage*
	
}


//______________ fonction de calcule la multiplcation de deux polynome ______________
void MultPoly(pol p1, pol p2){
	pol p;
	int i,j,d=p1.degre+p2.degre;      // pour la multiplication on a un seul cas est : degré de la produit égale à la somme des degrés deg(A)=deg(P)+deg(Q)
	float C[d],s;
								// et la rolation utilisée est : La produite est La somme de Si * X^i avec k = 0 -------------> K deg(P)+deg(Q)
	for(i=0;i<d+1;i++){        // avec Ci = la somme de Pj * Qi-J de j = 0 ------------> j = i
		s=0;								// Le principe est de stocker tout les Ci sur un tableau
		for(j=0;j<i+1;j++){					// et après ceci sera l'Ecoefficien de La polynôme produit
			s+=(p1.coeff[j]*p2.coeff[i-j]); // Ensuite afficher la Polynôme par la fonction d'affichage
		}									//		  |________________________________________|_____|											
		C[i] = s;							//													|
	}										//													|
											//													|
	printf("P(X) = ");						//													|
	for(i=0;i<d+1;i++){						//													|
		printf("%.2f * X^%d",C[i],i);		//												    |
		if(i<d)								//<------------------------------------------------|
			printf(" + ");					//
	}
	
}

//____________ fonction (procedure) de polynome NULL ______________
void NulPoly(pol* p){
	int i;
	p->degre = -1;
	for(i=0;i<DegreMax+1;i++){    // on affecte à tous les coefficients la valeur 0
		p->coeff[i] = 0;
	}
}

//___________________ fonction de vérification si un polynome est NULL ________________
int verif(pol p){
	int i,bol=1;
	for(i=0;i<p.degre+1;i++){
		if(p.coeff[i] != 0){     // Le principe est : si on a un polynôme la fonction ça return 1 si non-ca return 0
			bol = 0;			// et La fonction permet de vérifier s'il existe au moins un coefficient non nul
		break;                 // il affecte La valeur 0 et break a au variable bol et on est return bol
		}
	}
	return bol;
}
