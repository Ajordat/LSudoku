# Sudoku Solver

Aquest projecte implementa un solucionador de Sudokus de qualsevol mida (amb temps i un PC potent), encara que estigui pensat per a 
resoldre sudokus de 9x9 i 16x16. També resol sudokus de tipus samurai, que és una variant que consta de cinc sudokus que comparteixen
uns quadrats de 3x3 concrets.

Al directori principal s'inclouen sudokus de sobres per a fer les proves, però en cas de voler fer proves amb altres sudokus només s'ha
de seguir el seqüent patró per a que el programa ho pugui interpretar bé:

    Començant per la primera casella, que anirà col·locada a la primera posició de la primera línia, s'ha 
    d'escriure el sudoku separant cada casella amb un espai i escrivint el valor que aquesta conté o un guió 
    si no s'especifica. En el cas dels sudokus samurai s'ha d'utilitzar un asterisc per a cada posició on no 
    hi hagi cap casella (un espai en blanc).
    
Sé que m'explico malament, així que sentiu-vos lliures de mirar les mostres que hi ha.


Pel que fa al programa _per se_, se li ha d'introduir el nom del fitxer que conté el sudoku a resoldre (i que estigui al directori
del projecte) i aquest et preguntarà si vols veure el procés de resolució del sudoku. Un cop comprovades totes les possibilitats
mostra el temps que ha tardat en comprovar les possibilitats (no són tantes, fa servir un algoristme de Backtracking) i el nombre
de solucions que ha trobat.



Àlex Jordà
