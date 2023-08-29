package com.example.tictactoe

import java.lang.Exception
import kotlin.random.Random

var board = ArrayList<ArrayList<String>>()
fun main(){
    for(i in 0..2){
        val row= ArrayList<String>()
        for(j in 0..2){
            row.add("")
        }
        board.add(row)
    }
    printBoard()

    var continueGame=true
    do{
        println("Please enter a position (e.g. 1,1)")
        val input= readLine()?:""
        var x=0
        var y=0
        try{
            var positions=input.split(",")
            x=positions[0].trim().toInt()
            y=positions[1].trim().toInt()
            var skipRound=false

            if(board[x-1][y-1]!=""){
                println("This position is take, enter another position")
                skipRound=true
            }
            else{
                board[x-1][y-1]="X"
                //printBoard()
            }
            if(!skipRound){
                val playerWon=checkWinner(true)
                if(playerWon){
                    println("COngratulations,You won")
                    continueGame=false
                }
                val boardFull= boardFull()
                if(!playerWon&&boardFull){
                    print("It's a tie")
                    continueGame=false
                }
                if(continueGame){
                    placeComputerMove()
                    printBoard()
                    var computerWon= checkWinner(false)
                    if(computerWon){
                        print("Computer won")
                        continueGame=false
                    }
                }
            }
        }catch (e:Exception){
            print("Invalid input")
        }
    }while(continueGame)
}

fun printBoard(){
    for(i in 0..2){
        for(j in 0..2){
            when(board[i][j]){
                "X"->print("| X ")
                "0"->print("| 0 ")
                else->print("|   ")

            }
        }
        println("|")
        }
}

fun checkWinner(player:Boolean):Boolean{
    var won=false
    val checkSymbol=if(player) "X" else "0"
    for(i in 0..2){
        if(board[i][0]==checkSymbol && board[i][1]==checkSymbol && board[i][2]==checkSymbol){
            won=true
            break
        }else if(board[0][i]==checkSymbol && board[1][i]==checkSymbol && board[2][i]==checkSymbol){
            won=true
            break
        }
    }
    if(board[0][0]==checkSymbol && board[1][1]==checkSymbol && board[2][2]==checkSymbol){
        won=true
    }
    if(board[2][0]==checkSymbol && board[1][1]==checkSymbol && board[0][2]==checkSymbol){
        won=true
    }
    return won
}

fun boardFull():Boolean{
    var bFull=true
    for(i in 0..2){
        for(j in 0..2){
            if(board[i][j]==""){
                bFull=false
            }
        }
    }
    return bFull
}

fun placeComputerMove(){
    var i=0
    var j=0
    do {
        i= Random.nextInt(3)
        j=Random.nextInt(3)
    }while(board[i][j]!="")
    board[i][j]="0"
}