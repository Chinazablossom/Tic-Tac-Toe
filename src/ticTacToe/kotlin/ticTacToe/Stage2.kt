package ticTacToe

import kotlin.random.Random

/*
--Description:
Our program should be able to display the grid at all stages of the game.
Now we’re going to write a program that allows the user to enter a string representing the game state,
and correctly prints the 3x3 game grid based on this input.
We’ll also add some boundaries around the game grid.

--Objectives:
In this stage, you will write a program that:

Reads a string of 9 symbols from the input and displays them to the user in a 3x3 grid. The grid can contain only X, O and _ symbols.

Outputs a line of dashes --------- above and below the grid, adds a pipe | symbol to the beginning and end of each line of the grid,
and adds a space between all characters in the grid.
Examples
The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.

--Example 1:

> O_OXXO_XX
---------
| O _ O |
| X X O |
| _ X X |
---------
-Example 2:

> OXO__X_OX
---------
| O X O |
| _ _ X |
| _ O X |
---------
-Example 3:

> _XO__X___
---------
| _ X O |
| _ _ X |
| _ _ _ |
---------

 */

private val line = "---------\n"
private val words = readln()
private var grid = mutableListOf(mutableListOf<Char>())

fun main() {
    gridBoard()
}

private fun gridBoard(){
     grid = mutableListOf(
        mutableListOf(words[0],words[1],words[2]),
        mutableListOf(words[3],words[4],words[5]),
        mutableListOf(words[6],words[7],words[8]),
    )
    println(
        line +
        "${grid[0].joinToString(separator = " ", prefix = "| ", postfix = " |")}\n" +
        "${grid[1].joinToString(separator = " ", prefix = "| ", postfix = " |")}\n" +
        "${grid[2].joinToString(separator = " ", prefix = "| ", postfix = " |")}\n" +
        line
    )
}


