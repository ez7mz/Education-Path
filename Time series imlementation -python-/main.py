#Importation du fichier qui contienne la Class
from outils import *

if __name__ == '__main__':
    
    t = [x for x in range(0,16)]
    Y = [1020,1505,1400,805,1210,1790,1700,980,1480,2010,1800,1000,1400,2100,1820,1100]
    serie1 = Serie(t, Y)
    print(20*"="+" La Tendance "+20*"="+"\n",serie1.Trend(16),"\n")
    print(20*"="+" Les Coefficient Sesoniare "+20*"="+"\n",serie1.CoeS(16),"\n")
    print(20*"="+" Les Coefficient Sesoniare Courigee "+20*"="+"\n",serie1.CoeSAju(16),"\n")
    print(20*"="+" Le MMC : "+20*"="+"\n",serie1.MMC(4),"\n")
    print(20*"="+" Les Coefficient Sesoniare a l'aide de MMC "+20*"="+"\n",serie1.CoefS_MMC(4),"\n")
    print(20*"="+" Les Coefficient Sesoniare Courigee a l'aide de MMC "+20*"="+"\n",serie1.Coefs_MMC_Corr(4),"\n")
    print(20*"="+" La Serie CVS : "+20*"="+"\n",serie1.CVS_MMC(4),"\n")
    print(20*"="+" Les prevision de l'annee suivante "+20*"="+"\n",serie1.Prevision(),"\n")
    # Representation Graphique
    serie1.PlostSerie(len(t))