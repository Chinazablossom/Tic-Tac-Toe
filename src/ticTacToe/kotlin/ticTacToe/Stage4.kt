package ticTacToe

/*
--Description:
It's time to make our game interactive! Now we're going to add the ability for a user to make a move.
 To do this, we need to divide the grid into cells.
 Suppose the top left cell has the coordinates (1, 1) and the bottom right cell has the coordinates (3, 3):

(1, 1) (1, 2) (1, 3)
(2, 1) (2, 2) (2, 3)
(3, 1) (3, 2) (3, 3)

The program should ask the user to enter the coordinates of the cell where they want to make a move.

In this stage, the user plays as X, not O.
Keep in mind that the first coordinate goes from top to bottom and the second coordinate goes from left to right.
The coordinates can include the numbers 1, 2, or 3.

What happens if the user enters incorrect coordinates?
The user could enter symbols instead of numbers,
or enter coordinates representing occupied cells or cells that aren't even on the grid.
You need to check the user's input and catch possible exceptions.

--Objectives:
The program should work as follows:

1. Get the initial 3x3 grid from the input as in the previous stages.
Here the user should input 9 symbols representing the field, for example, _XXOO_OX_.
Output this 3x3 grid as in the previous stages.
2.Prompt the user to make a move.
The user should input 2 coordinate numbers that represent the cell where they want to place their X, for example, 1 1.
Analyze user input. If the input is incorrect, inform the user why their input is wrong:
Print This cell is occupied! Choose another one! if the cell is not empty.
Print You should enter numbers! if the user enters non-numeric symbols in the coordinates input.
Print Coordinates should be from 1 to 3! if the user enters coordinates outside the game grid.
Keep prompting the user to enter the coordinates until the user input is valid.
If the input is correct, update the grid to include the user's move and print the updated grid to the console.
To summarize, you need to output the field 2 times:
once before the user's move (based on the first line of input) and once after the user has entered valid coordinates
(then you need to update the grid to include that move).

Do not delete the code you wrote in the previous stage! You will need it in the final stage of this project,
so now you can just comment out a part of it.

--Examples:
The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.

-Example 1:

> X_X_O____
---------
| X   X |
|   O   |
|       |
---------
> 3 1
---------
| X   X |
|   O   |
| X     |
---------
-Example 2:

> _XXOO_OX_
---------
|   X X |
| O O   |
| O X   |
---------
> 1 1
---------
| X X X |
| O O   |
| O X   |
---------
-Example 3:

> _XXOO_OX_
---------
|   X X |
| O O   |
| O X   |
---------
> 3 3
---------
|   X X |
| O O   |
| O X X |
---------
-Example 4:

> _XXOO_OX_
---------
|   X X |
| O O   |
| O X   |
---------
> 2 3
---------
|   X X |
| O O X |
| O X   |
---------
-Example 5:

> _XXOO_OX_
---------
|   X X |
| O O   |
| O X   |
---------
> 3 1
This cell is occupied! Choose another one!
> 1 1
---------
| X X X |
| O O   |
| O X   |
---------
-Example 6:

> _XXOO_OX_
---------
|   X X |
| O O   |
| O X   |
---------
> one
You should enter numbers!
> one one
You should enter numbers!
> 1 1
---------
| X X X |
| O O   |
| O X   |
---------
-Example 7:

> _XXOO_OX_
---------
|   X X |
| O O   |
| O X   |
---------
> 4 1
Coordinates should be from 1 to 3!
> 1 4
Coordinates should be from 1 to 3!
> 1 1
---------
| X X X |
| O O   |
| O X   |
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
    showGrid()
    xPlay()
}

private fun showGrid() {
    println(
        line +
        "${grid[0].joinToString(separator = " ", prefix = "| ", postfix = " |")}\n" +
        "${grid[1].joinToString(separator = " ", prefix = "| ", postfix = " |")}\n" +
        "${grid[2].joinToString(separator = " ", prefix = "| ", postfix = " |")}\n" +
        line
    )
}

private fun xPlay() {
    do {
        try {
            val (row, index) = readln().split(" ").map { it.toInt() }
            if (grid[row - 1][index - 1] == playerX || grid[row - 1][index - 1] == playerO) {
                println("This cell is occupied! Choose another one!")
                return xPlay()
            } else if (grid[row - 1][index - 1] == empty) {
                grid[row - 1][index - 1] = playerX
                showGrid()
                break
            }
        } catch (e: NumberFormatException) {
            println("You should enter numbers!")
            return xPlay()
        } catch (e: IndexOutOfBoundsException) {
            println("Coordinates should be from 1 to 3!")
            return xPlay()
        }
    } while (true)

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



