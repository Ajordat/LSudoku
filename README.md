# Sudoku Solver

This project implements a Sudoku solver of any size (with time and a powerful PC), even if it was originally designed to solve sudokus of 9x9 and 16x16. It also resolves Samurai sudokus, which is a variant consisting of five sudokus that share
some 3x3 squares.

The main directory includes many sudokus to do the tests. In case you want to test it with other sudokus, you just have
to follow the pattern so that the program can interpret it well:

    Separete each field with a space. Write the numbers the program must know, write a dash for the blank boxes.
    In the case of Samurai sudokus, an asterisk must be used for each position where isn't a box.
    
If I haven't explained myself properly, feel free to look at the samples there.


As for the program, you must enter the name of the file containing the sudoku to be resolved and it will ask you if you want to see the sudoku resolution process. Once all the possibilities are checked, it shows how long it took (there are not so many, it uses a Backtracking algorithm) and the number of solutions it has found.



Àlex Jordà
