Readme-Tema 1
==========================================================================================
Main:
Incepem prin citirea datelor din fisierul de intrare , memoria principala si cea cache 
vor fi implementate cu arraylist-uri.
Vom citi fiecare comanda in parte , folosind un string tokeneizer preluam comanda si 
parametrii fiecarei comenzi ( daca e cazul, si pentru numarul de subscriptii premium)
verificand daca avem in continuare token uri de citit.
Daca avem o operatie de tipul ADD intai verificam daca nu cumva deja se afla in
memoria principala , daca da( ok == 0) salvam indexul la care se afla in idx(care prin
conventie la inceput e -1) , si apoi adaugam in memoria principala inlocuind elementul 
vechi cu cel nou (suprascrierea) , tinem cont daca avem element de tip basic ( daca
 nr de subscriptii premium ramane 0 la citire) sau premium , de asemenea verificam
daca se afla in cache  si il eliminam eventual de la indexul respectiv( setand 
numarul de utilizari pe 0 pentru LFU).Daca nu se afla deja in memorie atunci elementul
este introdus pe aceleasi principii.
Pentru operatia GET verificam daca elementul se afla in memoria principala si cache 
si retinem cate un index pentru fiecare , pentru cazul LRU actualizam timestamp ul 
elementului daca se afla in cache , si pentru LFU crestem numarul de utilizari , pe baza 
caruia vom elimna un element din cache.
In continuare facem afisarile corespunzatoare , folostin functia de return subscription
care afiseaza in functie de numarul de subscriptii,tipul acesteia.
Mai departe facem adaugarile si daca e cazul eliminarile in functie de FIFO ,LRU LFU
doar daca elementul nu se afla deja in cache.
La sfarsit se prinde eroarea in fileNotFound.
Subscriptie:
Are un nume , timestamp pt LRU , si nrOfUses pt LFU, constructor, setter si getter
pentru toti parmetri de care avem nevoie si definirea unei clase abstracte ce va fi
suprascrisa pentru intoarcerea tipului subscriptiei.
Free:
Se face afisarea tipului de subscriptie prin suprascrierea metodei.
Basic:
Suprascriem metoda astfel incat daca numarul de basic este mai mic decat 0 ne intoarcem
in suprascrierea anterioara ( afisarea Free)
Premium:
Acelasi principiu ca la Basic.
FIFOCache:
Se suprascriu metodele ,add: se adauga la sfarsitul arraylist ului , remove: se elimina
primul element , metoda intoarce o valoare ce va fi utila pt LRU ,in acest caz intoarce
prin conventie -1.
LRUCache:
Pentru add : se adauga elementul la pozitia i intoarsa de remove .
Pentru remove: Gasim elementul cu timestamp ul minim ,apoi returnam index ul de unde
facem eliminarea.(pentru ca vom insera la aceeasi pozitie de unde eliminam)
LFUCache:
Pentru add:acelasi principiu ca la LRU.
Pentru remove: vom gasi numarul minim de utilizari , dar pentru ca mai multe elemente
pot avea acelasi numar de utilizari , vom gasi dintre acestea elementul cu cel mai
mic timestamp , vom elimina acel element ,returnand index ul.
Toate clasele de cache implementeaza interfata Cache