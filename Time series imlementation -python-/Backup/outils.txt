from cmath import nan
import matplotlib.pyplot as pt

class Serie:
    
    #la fonction __init__ definit notre constructeur qui nous permet a instancier l'object de la serie,
        # t : la liste du Temps
        # y : la liste des valeurs
    def __init__(self, t, y):
        self.t = t
        self.y = y
        
    #Calcule simple de la moyenne
     
    def Ybar(self, n):
        return sum(self.y)/n
    
    def tbar(self, n):
        return sum(self.t)/n

    #Calculons la covariance 

    def Cov(self, n):
        term1 = 0
        for i in range(n):
            term1 = term1 + (self.y[i] * self.t[i])
        return term1 -  n * self.Ybar(n) * self.tbar(n)

    #Calculons la variance 

    def Var(self, n):
        term1= 0
        for i in range(n):
            term1 = term1 + (self.t[i] * self.t[i])
        return term1 - n * (self.tbar(n) * self.tbar(n))
    
    #Pour calculer le trend on doit determiner d'abord le a et le b de notre fonction : y = a*t+b
    
    def a(self,n):
        return self.Cov(n) / self.Var(n)

    def b(self, n):
        return self.Ybar(n) - (self.a(n) * self.tbar(n))


    def Trend(self, n):
        T = []
        a = self.a(n)
        b = self.b(n)
        for i in range(n):
            T.append( a * self.t[i] + b )
        return T

    #Les Coefficient Sesoniare

    def CoeS(self, n):
        CS = []
        T = self.Trend(n)
        for i in range(n):
            CS.append(self.y[i] - T[i])
        return CS

    #Les Coefficient Sesoniare Courigee

    def CoeSAju(self, n):
        Temp = []
        l = self.CoeS(n)
        moy = sum(l)/n
        for i in range(n):
            Temp.append(l[i] - moy)
        return Temp
    
    #La Serie CVS

    def CVS(self, n):
        Temp = []
        for i in range(n):
            Temp.append(self.y[i] - self.CoeSAju(n)[i])
        return Temp
    
    # La Tendance avec MMC

    def MMC(self,n):
        temp = []
        res = 0
        if n%2!=0:
            k = int((n-1)/2)
            for t in range(k,len(self.y)-k):
                res = 0 
                for i in range (-k,k+1):
                    res = res + self.y[t+i]
                res = res / n
                temp.append(res)
        elif n%2==0:
            k = int(n/2)
            for t in range(k,len(self.y)-k):
                res = 0
                for j in range(-k+1,k):
                    res = res + self.y[t+j]
                res = (res + (self.y[t-k]+self.y[t+k])*0.5)/n
                temp.append(res)
        for j in range(k):
            temp.insert(j,0)
            temp.append(0)
        return temp,k
    
    #Les Coefficient Sesoniare Courigee a l'aide de MMC

    def Coefs_MMC_Corr(self,n):
        temp = []
        res , _ = self.MMC(n)
        moy = sum(res)/len(res)
        for i in range(len(res)):
            temp.append(res[i]-moy)
        return temp
    
    # La Serie CVS avec MMC

    def CVS_MMC(self,n):
        temp = []
        res = self.Coefs_MMC_Corr(n)
        for i in range(len(res)):
            temp.append(self.y[i]-res[i])
        return temp

    # Coefficient Sesoniare a l'aide de MMC

    def CoefS_MMC(self,n):
        final = []
        res , _ = self.MMC(n)
        for i in range(0,int(len(self.y)/4)):
            temp = []
            for j in range(i,len(self.y),4):
                temp.append(self.y[j] - res[j])
            final.append(sum(temp)/len(temp))
        return final

    # methode pour voir les prevision

    def Prevision(self):
        S = self.CoefS_MMC(4)
        t1 = [17,18,19,20]
        T = []
        prev = []
        a = self.a(16)
        b = self.b(16)
        for i in t1:
            T.append(a * i + b)
        for i in range(4):
            prev.append(T[i] + S[i])
        return t1,prev,T
    
    # methode pour le tracage des graphes

    def PlostSerie(self,n):
        pt.title("Serie Chronologique Additif")
        pt.xlabel("Temp en Trimister")
        pt.ylabel("Ventes")
        X = self.t
        Y = self.y
        t = self.Trend(n)
        mmc , _ = self.MMC(4)
        mmc[:] = [x if x != 0 else nan for x in mmc]
        t2,previs,T = self.Prevision()
        X1 = X + t2
        Y2 = Y + previs
        t1 = t + T
        pt.grid()
        pt.plot(X1,Y2,"o-",color="brown",label="Prevision")
        pt.plot(X,Y,"o-",color="blue",label="Original")
        pt.plot(X1,t1,"-", color="orange",label="tendence previs")
        pt.plot(X,t,"-", color="red",label="tendence")
        pt.plot(X,mmc,"o-",color="green",label="MMC")
        pt.legend()
        pt.show()
        
        
        
if __name__ == 'outils':
    print("Utilities file loaded successfuly ! \n")
    print("Made By :   # MESRAR Hamza\n")