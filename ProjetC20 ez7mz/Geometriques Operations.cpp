#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <conio.h>
#include <windows.h>
#include "menu.h"   // Toutes les fonctions menu sont prédéfinies sur cette en-tete
#define Max_Pts 100   // recommandé d'utiliser un nombre plus petit pour l'exemple car la saisie de 100 points prendra beaucoup de temps!

//____________ Déclaration de structure et prototype des fonctions ____________

struct point {char Nom ; float x ; float y ;} ;
typedef struct point spt ;

struct vecteur {float x ; float y ;} ;				//Déclaration de structure utilisée
typedef struct vecteur svect ;		
														
struct Distance {char Nom ; float dis ;} T1,tab;
typedef struct Distance sdis ;
								

spt O ={'O',0,0}; // Preinisialisation de point O L'origine du repère



spt saisie(void);					//prototype de fonction de saisie d'un point
void afficher(spt P);				//prototype de fonction d'affichage d'un point
float distance(spt p1, spt p2);		//prototype de fonction distance
void Deplacer(spt* p, float dx, float dy);  //prototype de fonction Déplacer

svect ss(void);    //prototype de fonction de saisie d'un vecteur
void aff(svect v); //prototype de fonction d'affichage d'un vecteur
float ProdScal(svect v1, svect v2);  //prototype de fonction qui calcule le produit scalaire

int Colineaires (spt p1, spt p2, spt p3);         //
void AffichEquatCartesienne (spt p1, spt p2);	  //
int triangleRrectangle(spt p1, spt p2, spt p3);	  //      //prototype des fonctions du programme
int triangleIsocele(spt p1, spt p2, spt p3);	  //
float SurfaceGEF(spt Z, spt Q, float r);		  //

void tableau_dis(sdis T1[]);			//
void tri_pts(sdis tab[]);				//		//prototype des fonctions du sous-programme
void sous_prog(void);					//

//_______________________________ main code ________________________________________________

int main(){
	
	spt ptr,ptr1,ptr2;
	svect e1,e2;
	int choix,freq;
	float dxx,dyy;
	
	menu2();		//Procédure pour afficher le menu (prédéfinit dans "menu h"
	scanf("%d",&choix);
	system("cls");
	
	switch(choix){
		case 1:			// case 1 pour saisie et afficher un point
			printf("Saisir et affichage d'un point : \n");
			ptr = saisie();
			printf("\n");
			afficher(ptr);
			break;
		case 2:			// case 2 pour saisie et afficher un vecteur
			printf("Saisie et affichage d'un vecteur : \n");
			e1 = ss();
			printf("\n");
			aff(e1);
			break;
		case 3:			// case 3 pour calcul la distance entre deux points
			printf("Calculation des Distance entre deux points : \n");
			ptr1 = saisie();
			afficher(ptr1);
			printf("\n\n");
			ptr2 = saisie();
			afficher(ptr2);
			printf("\n\n");
			printf("La distance Entre %c et %c est : %.2f",ptr1.Nom,ptr2.Nom,distance(ptr1,ptr2));
			break;
		case 4:			// case 4 pour déplacer un point
			printf("Deplacement des point : \n");
			printf("Entrer Les cordonner de point : \n");
			ptr = saisie();
			printf("\n");
			afficher(ptr);
			printf("\n");
			printf("\tentrer la valeur que vous allez deplacez Dans l'axe Ox : ");
			scanf("%f",&dxx);
			printf("\tentrer la valeur que vous allez deplacez Dans l'axe Oy : ");
			scanf("%f",&dyy);
			printf("\n\n\t");
			Deplacer(&ptr,dxx,dyy);
			break;
		case 5:			// case 5 pour calcul le produite scalaire de deux vecteurs
			printf("Calculation de produit scalaire des vecteur : \n");
			printf("Entrer Les cordonner du 1er Vecteur : \n");
			e1 = ss();
			printf("\n");
			aff(e1);
			printf("\nEntrer Les cordonner du 1eme Vecteur : \n");
			e2 = ss();
			printf("\n");
			aff(e2);
			printf("\n\t");
			printf("Le produit scalair de deux vecteur est : %.2f",ProdScal(e1, e2));
			break;
		case 6:			// case 6 pour vérifier si trois points est colénaire
			printf("Entrer Les cordonner du 1er point : \n");
			ptr = saisie();
			printf("\n");
			afficher(ptr);
			printf("\n");
			printf("Entrer Les cordonner du 2eme point : \n");
			ptr1 = saisie();
			printf("\n");
			afficher(ptr1);
			printf("\n");
			printf("Entrer Les cordonner du 3eme point : \n");
			ptr2 = saisie();
			printf("\n");
			afficher(ptr2);
			printf("\n");
			freq = Colineaires(ptr,ptr1,ptr2);
			if(freq == 1){
				printf("Les Trois points est colineaires!");
			}else if(freq == 0){
				printf("Les Trois points n'est pas colineaires!");
			}
			break;
		case 7:			// case 7 pour donne l'équation cartésienne d'un droit
			printf("Entrer Les cordonner du 1er point : \n");
			ptr1 = saisie();
			printf("\n");
			afficher(ptr1);
			printf("\n");
			printf("Entrer Les cordonner du 2eme point : \n");
			ptr2 = saisie();
			printf("\n");
			afficher(ptr2);
			printf("\n");
			AffichEquatCartesienne (ptr1, ptr2);
			break;
		case 8:		// case 8 pour vérifier si un triangle est Rectangle
			printf("Entrer Les cordonner du 1er point : \n");
			ptr = saisie();
			printf("\n");
			afficher(ptr);
			printf("\n");
			printf("Entrer Les cordonner du 2eme point : \n");
			ptr1 = saisie();
			printf("\n");
			afficher(ptr1);
			printf("\n");
			printf("Entrer Les cordonner du 3eme point : \n");
			ptr2 = saisie();
			printf("\n");
			afficher(ptr2);
			printf("\n");
			freq = triangleRrectangle(ptr,ptr1,ptr2);
			if(freq == 1){
				printf("Le Triangle est Rectangle!");
			}else if(freq == 0){
				printf("Le Triangle n'est pas Rectangle!");
			}
			break;
		case 9:		// case 9 pour vérifier si un triangle est Isocèle
			printf("Entrer Les cordonner du 1er point : \n");
			ptr = saisie();
			printf("\n");
			afficher(ptr);
			printf("\n");
			printf("Entrer Les cordonner du 2eme point : \n");
			ptr1 = saisie();
			printf("\n");
			afficher(ptr1);
			printf("\n");
			printf("Entrer Les cordonner du 3eme point : \n");
			ptr2 = saisie();
			printf("\n");
			afficher(ptr2);
			printf("\n");
			freq = triangleIsocele(ptr,ptr1,ptr2);
			if(freq == 1){
				printf("Le Triangle est Isocele!");
			}else if(freq == 0){
				printf("Le Triangle n'est pas isocele!");
			}
			break;
		case 10:			// case 10 pour accéder au sous-programme ( Q9 à Q12 )
			printf("Votre demande sera transfere sur quel'que instant!\n\n");
			delay(1000000000);		// fonction pour faire le retard avec la effacer d'ecrant (prédéfinit dans "menu.h")
			system("cls");
			green();printf("votre demande est maintenant disponible!\n\t\tApuyez any touch pour continue!");
			getch();			// pour lire une clique au clavier
			system("cls");		// pour vider l'écrant
			sous_prog();
			break;
		case 11:			// case 11 pour Devloppeur information
			printf("\n\n\n\t\t\t\tHAMZA MESRAR\tAPI-2\tGRP 5 - 2020 - ez7mz");
			getch();
			break;
		case 0:			// case 0 pour sortir et fermer le programme
			printf("\n\n\t\t__________ MESRAR HAMZA vous remercie de votre visite! __________\n\n");
			getch();
			break;
		default :			// Juste pour Incorrect choix
			red();printf("\n\n\t\tInvalid choix!\t\ttry again...");
			delay(100000000);
			system("cls");
			cyan();
			main();	// Exécuté la main à nouveau
	}
	
	return 0;
}

//_______________________________________________________________________________________________________


//__________________________________________________ Les Fonctions de Programme _____________________________________

//____________ finction de saisie d'une point ____________

spt saisie(void){
	spt P;
	
	printf("Donnez le nom de point : ");
	scanf("%1s",&P.Nom);			// %c peut être fait une erreur (qui lisaient la valeur la première fois seulement)
	//scanf ("%*[^\n]");
	printf("Donnez X : ");
	scanf("%f",&P.x);
	printf("Donnez Y : ");			// P.membre pour accéder à chaque variable de structure spt
	scanf("%f",&P.y);
	printf("\n");
	
	return P;			// la fonction return une variable du type structeur spt
}

//____________ fonction (procedure) d'affichage d'une point ___________________

void afficher(spt P){
	printf("Le point %c = ( %.2f , %.2f )\n",P.Nom,P.x,P.y); //Juste un printf avec la variable de structure spt
}

//_________________ fonction qui permet de calculer la distance entre deux points __________________

float distance(spt p1, spt p2){
	
	return (sqrt(pow((p1.x-p2.x),2)+pow((p1.y-p2.y),2)));  // on sait que la distance entre deux points A et B est : |AB| = sqrt((Xb-Xa)^2 + (Yb-Ya)^2)
															// pow = puissance et sqrt = racine carre les deux prédéfinis dans <math.h>
}

//_________________ fonction qui permet de déplacer le point par dx et dy  __________________

void Deplacer(spt* p, float dx, float dy){
	
	p->x+=dx;
	p->y+=dy;						// pour déplacer un point il se fait qu'ajouter Le déplacement de X à dx et à dy
	
	printf("Les nouveau cordonners de %c sont : ( %.2f , %.2f )",p->Nom,p->x,p->y);
}

//____________ finction de saisie d'un vecteur ____________

svect ss(void){
	svect V;
	
	printf("Donnez X : ");
	scanf("%f",&V.x);
	printf("Donnez Y : ");				// Le même pour ce qui fait pour les points justes en change la structure spt à svect
	scanf("%f",&V.y);
	
	return V;		// return une variable du type svect
}

//____________ fonction (procédure) d'affichage d'une vecteur ___________________

void aff(svect v){
	printf("Les cordonner de vecteur sont : ( %.2f , %.2f )\n",v.x,v.y); //Juste un printf avec la variable de structure svect
}

//___________ fonction qui permet de calculer le produit scalaire de deux vecteurs __________________

float ProdScal(svect v1, svect v2){
	
	return ((v1.x*v2.x) + (v1.y*v2.y));  // la relation Appliquer est : V1. V2 = (xV1*xV2)+(yV1*yV2) V1. V2 = produit scalaire(V1,V2)
}

//____________ fonction de vérification si trois points est Colinéaires ________________

int Colineaires (spt p1, spt p2, spt p3){
	
	svect u1,u2;
	int bol=0;
	
	u1.x = p2.x-p1.x;							// On a trois points A, B, et C'est Colinéaire si :
	u1.y = p2.y-p1.y;							//Soit les vecteurs BA et CA compris par les points A, B, et C
	u2.x = p3.x-p1.x;							// si BAx * CAy = BAy * CAx alors les trois points est colinéaire
	u2.y = p3.y-p1.y;
	
	if((u1.x*u2.y) == (u1.y*u2.x))			
		bol = 1;
	
	return bol;					// Enfin Si les points sont colinéaires la fonction return 1 sinon il est return 0
	
}

//____________ fonction (procédure) d'affichage de l'équation Cartésienne d'un droit ___________________

void AffichEquatCartesienne (spt p1, spt p2){
	float a,b;
												//Equation d'un droit (AB) est : Y = a * X + b    Tq a = (By-Ay)/(Bx-Ax)
	a = (p2.y-p1.y)/(p2.x-p1.x);				// et b = Ay - (a * Ax)  Ou  b = By - (a * Bx)  sont les mêmes
	b = p1.y - (a*p1.x);
	
	printf("L'equation cartesienne de la droite (%c%c) est : Y = %.2f*X + %.2f",p1.Nom,p2.Nom,a,b);
}

//____________ fonction de vérification si un triangle est Rrectangle ________________

int triangleRrectangle(spt p1, spt p2, spt p3){
	int bol=0;
	float a,b,c;
													// Le principe est d'applique le théorème de Pythagore
	a = distance(p1,p2);							// BC^2 = AB^2 + AC^2
	b = distance(p2,p3);
	c = distance(p1,p3);				// mais on applique ce théorème sire chaque point par ce que ne connaît pas la tete de triangle
	//printf("%f\t%f\t%f\n",a,b,c);
	if( pow(b,2) <= (pow(a,2)+pow(c,2)+0.1) && pow(b,2) >= (pow(a,2)+pow(c,2)-0.1) ){
		bol=1;
	}else if(pow(c,2) == (pow(a,2)+pow(b,2))){
		bol=1;
	}else if(pow(a,2) == (pow(b,2)+pow(c,2))){
		bol=1;
	}
	
	return bol;			// Enfin Si le triangle est Rrectangle la fonction return 1 sinon il est return 0
	
}

//____________ fonction de vérification si un triangle est Isocèle ________________

int triangleIsocele(spt p1, spt p2, spt p3){
	int bol=0;
	float P2P1,P2P3;						// Triangle P1P2P3 est Isocèle en P2 Si:
											// P2P1 = P2P3
	P2P1 = distance(p2,p1);
	P2P3 = distance(p2,p3);
	
	if(P2P1 == P2P3)
		bol=1;
	
	return bol;				// Enfin Si le triangle est Isocèle la fonction return 1 sinon il est return 0
}


//____________________________________ Sous programme qui trie - Q9 à Q12 - ____________________________________________________________________

//___________ fonction qui permet de calculer la Surface du triangle GEF __________________

float SurfaceGEF (spt Z, spt Q, float r){
	float S;						// NB : la démonstration on Le rapport.
	spt F;							//mais en conclusion on obtient que : le point F ait l'abscisse de Q (E) et l'ordonné de Z (X) [Q = E et Z = X ]
									//Alors en peut calculer la distance EF il rest La distance GF qui égale le diamètre du grand cercle, ce dernier
	F.Nom = 'F';					//est égale à la diagonale du carré. D'où GF = 2* sqrt(2)*r 
	F.x = Q.x;						//puis en calcule la surface qui égale à : S = ( GF * EF )/2
	F.y = Z.y;
	
	S = (distance(F,Q)*(2*sqrt(2)*r)) / 2;
	
	return S;				//return la surface S avec un type float
}

//_______________ fonction pour stocker les distances entre des points et L'origine sur un tableau _______________________________

void tableau_dis(sdis T1[]){
	
	spt T[Max_Pts+1];			// Déclaration d'un tableau de 100 points
	int i=0,j;
	
	
	for(i=0;i<Max_Pts;i++){
		
		printf("Entrer les cordonner de point %d : \n",i+1);
		printf("Entrer Le nom : ");
		scanf("%1s",&T[i].Nom);
		printf("Entrer X : ");									// cette boucle pour remplir le tableau avec des points (Le tableau est de type spt)
		scanf("%f",&T[i].x);
		printf("Entrer Y : ");
		scanf("%f",&T[i].y);
	}
	
	i=0;
	for(i=0;i<Max_Pts;i++){
		T1[i].dis = distance(T[i],O);
		T1[i].Nom = T[i].Nom;
	}																// cette boucle pour remplir un tableau des floats par
	i=0;															// la distance de chaque point de tableau précédent
	printf("L'order initiale du points : \n\n");
	printf("Nom     distance\n");																// à l'origine O du repère
	printf("==========================\n");						
	for(i=0;i<Max_Pts;i++){											
		printf("%c\t%.2f\n",T1[i].Nom,T1[i].dis);
	}
							//N.B : Le tableau qui donne en paramètre il va sortir et tu peux utiliser si vous voulez
							
}

//___________ fonction qui permet de trier ce tableau selon la distance des points à l’origine O __________________________________________

void tri_pts(sdis tab[]){
	
	int i, j, index;						// cette fonction qui prend un tableau des distances est qui trier
	float tmp;
	char tmp1;							//ce dernier selon la distance des points à l’origine O du repère
	
	for (i=0; i < (Max_Pts-1); i++){
		index = i;								
		for (j=i + 1; j < Max_Pts; j++){		 
			if (tab[index].dis > tab[j].dis)			
			index = j;							//Dans ces boucles de trie, nous cherchons la case du tableau qui contient le plus petit
		}										// distance et on le met au bon endroit.
												// Nous échangeons la case en cours avec la prochaine case qui contient la plus petite distance.
		if (index != i){
			tmp = tab[i].dis;
			tmp1 = tab[i].Nom;
			tab[i].dis = tab[index].dis;
			tab[i].Nom = tab[index].Nom;
			tab[index].dis = tmp;
			tab[index].Nom = tmp1;
		}
	}
	
	printf("\n\n");
	printf("L'order du points par distance au l'origine O du repere :\n");
	for (i=0; i < Max_Pts; i++)							// affichage du tableau en l'ordre correct des distances
		printf("%c\t%.2f\n", tab[i].Nom,tab[i].dis);
	
}

//__________________ fonction main de sous programme ( espace d'exécution des fonctions de sous programme ) ___________________________

void sous_prog(void){
	
	sdis M[Max_Pts+1];
	spt Z1,Q1;
	float rayon,surface;
	int chx;
	
	again :		// fixe un point pour le revenir après
	menu3();			//Procédure pour afficher le menu (prédéfinit dans "menu.h")
	scanf("%d",&chx);
	switch(chx){
		case 1:			// case 1 Pour calculer la surface du triangle GEF dans Le schéma
			system("cls");	// pour vider L'écrant (fonction prédéfinit dans <Windows.h>)
			printf("Calculation Du surface de Triangle GEF : \n\t");
			printf("Entrer Les cordonner du point Z : \n");
			Z1 = saisie();
			printf("\n");
			printf("Entrer Les cordonner du point Q : \n");
			Q1 = saisie();
			printf("\n");
			printf("Entrer Le Rayon R : ");
			scanf("%f",&rayon);
			printf("\n");
			surface = SurfaceGEF (Z1, Q1, rayon);
			printf("\n\t\tLa surface du triangle GEF est : S = %.2f",surface);
			break;
		case 2:			// case 2 pour remplir est trie le tableau de 100 points
			system("cls");
			tableau_dis(M);
			tri_pts(M);
			break;
		case 3:			// case 3 pour tracer le schéma
			system("cls");
			printf("\t\t--------------________________-------------\n\n");
			break;
		case 0:			// case 0 pour Devloppeur information
			system("cls");
			printf("\n\n\t\t__________ MESRAR HAMZA vous remercie de votre visite! __________\n\n");
			getch();
			break;
		default :			// Juste pour incorrect choix
			red();printf("\n\n\t\tInvalid choix!\t\ttry again...");
			delay(100000000);
			system("cls");
			cyan();
			goto again;			// pour routeur à un point déjà fixé
	}
	
	
}	
