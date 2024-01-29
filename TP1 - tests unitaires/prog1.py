#Q1.3
#print ("Hello, World!")

import fonctions as f

while True:
    a = int(input("Entrez la valeur de a = "))
    b = int(input("Entrez la valeur de b = "))
    res = f.puissance(a, b)
    print(f"{a}^{b} = {res}")

