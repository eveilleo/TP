#def puissance(a,b):
#   if a < 0 and b == 0:
#      return -1
#   else:
#      return a ** b
      
def puissance(a, b):

    if a < 0 and b == 0:
        return -1
       
    if a == 0 and b < 0:
        return "Erreur"
        
    elif b == 0:
        return 1
    
    else:
        resultat = 1
        for _ in range(abs(b)):
            resultat *= a 
        if b < 0:
            return 1 / resultat
        else:
            return resultat

