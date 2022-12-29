package ticTacToe

import kotlin.math.abs

/*
--Description:
In this stage, we’re going to analyze the game state to determine if either player has already won the game or it is still ongoing, if the game is a draw, or if the user has entered an impossible game state (two winners, or with one player having made too many moves).

--Objectives:
In this stage, your program should:

Take a string entered by the user and print the game grid as in the previous stage.
Analyze the game state and print the result. Possible states:
Game not finished when neither side has three in a row but the grid still has empty cells.
Draw when no side has a three in a row and the grid has no empty cells.
X wins when the grid has three X’s in a row (including diagonals).
O wins when the grid has three O’s in a row (including diagonals).
Impossible when the grid has three X’s in a row as well as three O’s in a row,
or there are a lot more X's than O's or vice versa (the difference should be 1 or 0;
if the difference is 2 or more, then the game state is impossible).
In this stage, we will assume that either X or O can start the game.

You can choose whether to use a space or underscore _ to print empty cells.

--Examples:
The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.

-Example 1:

> XXXOO__O_
---------
| X X X |
| O O _ |
| _ O _ |
---------
X wins
-Example 2:

> XOXOXOXXO
---------
| X O X |
| O X O |
| X X O |
---------
X wins
-Example 3:

> XOOOXOXXO
---------
| X O O |
| O X O |
| X X O |
---------
O wins
-Example 4:

> XOXOOXXXO
---------
| X O X |
| O O X |
| X X O |
---------
Draw
-Example 5:

> XO_OOX_X_
---------
| X O   |
| O O X |
|   X   |
---------
Game not finished
-Example 6:

> XO_XO_XOX
---------
| X O _ |
| X O _ |
| X O X |
---------
Impossible
-Example 7:

> _O_X__X_X
---------
|   O   |
| X     |
| X   X |
---------
Impossible
-Example 8:

> _OOOO_X_X
---------
|   O O |
| O O   |
| X   X |
---------

 */
private const val line = "---------\n"
private val words = readln()
private var grid = mutableListOf(mutableListOf<Char>())
private const val playerX = 'X'
private const val playerO = 'O'
private const val empty = '_'
private var numberOfX = 0
private var numberOfO = 0
private var numberOfemptys = 0
private var winnerX = 0
private var winnerO = 0

fun main() {
    grid = mutableListOf(
        mutableListOf(words[0], words[1], words[2]),
        mutableListOf(words[3], words[4], words[5]),
        mutableListOf(words[6], words[7], words[8])
    )
    println(
        line +
        "${grid[0].joinToString(separator = " ", prefix = "| ", postfix = " |")}\n" +
        "${grid[1].joinToString(separator = " ", prefix = "| ", postfix = " |")}\n" +
        "${grid[2].joinToString(separator = " ", prefix = "| ", postfix = " |")}\n" +
        line
    )

    analyzer(grid)
}

private fun analyzer(board: MutableList<MutableList<Char>>) {

    for (item in words) {
        if (item == playerX) { // number of x's
            numberOfX++
        }
    }
    for (item in words) {
        if (item == playerO) { // number of o's
            numberOfO++
        }
    }

    for (item in words) {
        if (item == empty) { // number of empty Cells
            numberOfemptys++
        }
    }
    // X  Wins
    fun xWins(grid: MutableList<MutableList<Char>>): Int {
        if (grid[0][0] == playerX && grid[0][1] == playerX && grid[0][2] == playerX ||
            grid[1][0] == playerX && grid[1][1] == playerX && grid[1][2] == playerX ||
            grid[2][0] == playerX && grid[2][1] == playerX && grid[2][2] == playerX ||
            grid[0][0] == playerX && grid[1][1] == playerX && grid[2][2] == playerX ||
            grid[0][2] == playerX && grid[1][1] == playerX && grid[2][0] == playerX ||
            grid[0][0] == playerX && grid[1][0] == playerX && grid[2][0] == playerX ||
            grid[0][1] == playerX && grid[1][1] == playerX && grid[2][1] == playerX ||
            grid[0][2] == playerX && grid[1][2] == playerX && grid[2][2] == playerX
        ) {
            winnerX++
        }
        return winnerX
    }

    // O Wins
    fun oWins(grid: MutableList<MutableList<Char>>): Int {
        if (grid[0][0] == playerO && grid[0][1] == playerO && grid[0][2] == playerO ||
            grid[1][0] == playerO && grid[1][1] == playerO && grid[1][2] == playerO ||
            grid[2][0] == playerO && grid[2][1] == playerO && grid[2][2] == playerO ||
            grid[0][0] == playerO && grid[1][1] == playerO && grid[2][2] == playerO ||
            grid[0][2] == playerO && grid[1][1] == playerO && grid[2][0] == playerO ||
            grid[0][0] == playerO && grid[1][0] == playerO && grid[2][0] == playerO ||
            grid[0][1] == playerO && grid[1][1] == playerO && grid[2][1] == playerO ||
            grid[0][2] == playerO && grid[1][2] == playerO && grid[2][2] == playerO
        ) {
            winnerO++
        }
        return winnerO
    }

    if (xWins(grid) == 1 && oWins(grid) == 0) println("X wins")
    if (oWins(grid) == 1 && xWins(grid) == 0) println("O wins")
    // if we have a Draw
    if (numberOfX + numberOfO == 9 && numberOfemptys == 0 && xWins(grid) == 0 && oWins(grid) == 0) println("Draw")
    // Game not finished
    if (numberOfX + numberOfO < 9 && numberOfemptys >= 1 && xWins(grid) == 0 && oWins(grid) == 0) {
        println("Game not finished")
    }
    // impossible
    if (numberOfX > numberOfO + 1 || numberOfO > numberOfX + 1 ||
        xWins(grid) > 1 && oWins(grid) > 1 ||
        (xWins(grid) == 1 && oWins(grid) == 1)) println("Impossible")

}
