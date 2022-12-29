package ticTacToe

/*
--Description:
Our game is almost ready!
Now let's combine what we’ve learned in the previous stages to make a game of tic-tac-toe
that two players can play from the beginning (with an empty grid) through to the end (until there is a draw, or one of the players wins).

The first player has to play as X and their opponent plays as O.

--Objectives:
In this stage, you should write a program that:

Prints an empty grid at the beginning of the game.
Creates a game loop where the program asks the user to enter the cell coordinates,
analyzes the move for correctness and shows a grid with the changes if everything is okay.
Ends the game when someone wins or there is a draw.
You need to output the final result at the end of the game. Good luck!

--Example:
The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.

-Example 1.
---------
|       |
|       |
|       |
---------
> 2 2
---------
|       |
|   X   |
|       |
---------
> 2 2
This cell is occupied! Choose another one!
> two two
You should enter numbers!
> 1 4
Coordinates should be from 1 to 3!
> 1 1
---------
| O     |
|   X   |
|       |
---------
> 3 3
---------
| O     |
|   X   |
|     X |
---------
> 2 1
---------
| O     |
| O X   |
|     X |
---------
> 3 1
---------
| O     |
| O X   |
| X   X |
---------
> 2 3
---------
| O     |
| O X O |
| X   X |
---------
> 3 2
---------
| O     |
| O X O |
| X X X |
---------
X wins
 */
private const val line = "---------\n"
private var words = MutableList(9) { ' ' }
private var grid = mutableListOf(mutableListOf<Char>())
private const val playerX = 'X'
private const val playerO = 'O'
private const val empty = ' '
private var numberOfX = 0
private var numberOfO = 0
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
    if (winnerX > 0 && winnerO == 0) {
        println("X wins")
    }
    if (winnerO > 0 && winnerX == 0) {
        println("O wins")
    }
    if (numberOfX + numberOfO == 9 && winnerO == 0 && winnerX == 0) {
        println("Draw")
    }
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

    while (winnerX == 0 && winnerO == 0 && numberOfX + numberOfO != 9) {
        try {
            val (row, index) = readln().split(" ").map { it.toInt() }
            if (grid[row - 1][index - 1] == playerX || grid[row - 1][index - 1] == playerO) {
                println("This cell is occupied! Choose another one!")
                return xPlay()
            }

            if (grid[row - 1][index - 1] == empty && xWins(grid) == 0) {
                grid[row - 1][index - 1] = playerX
                winnerX = xWins(grid)
                numberOfX++
                showGrid()
                return oPlays()
            }
        } catch (e: NumberFormatException) {
            println("You should enter numbers!")
            return xPlay()
        } catch (e: IndexOutOfBoundsException) {
            println("Coordinates should be from 1 to 3!")
            return xPlay()
        }
    }

}

private fun oPlays() {
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

    while (winnerO == 0 && winnerX == 0  && numberOfX + numberOfO != 9 ) {
    try {
        val (row, index) = readln().split(" ").map { it.toInt() }
        if (grid[row - 1][index - 1] == playerX || grid[row - 1][index - 1] == playerO) {
            println("This cell is occupied! Choose another one!")
            return oPlays()
        }

        if (grid[row - 1][index - 1] == empty && oWins(grid) == 0) {
            grid[row - 1][index - 1] = playerO
            winnerO = oWins(grid)
            numberOfO++
            showGrid()
            return xPlay()
        }

    } catch (e: NumberFormatException) {
        println("You should enter numbers!")
        return oPlays()
    } catch (e: IndexOutOfBoundsException) {
        println("Coordinates should be from 1 to 3!")
        return oPlays()
    }
 }
}
