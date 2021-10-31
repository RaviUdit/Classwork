/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raviudit.javamilestoneone;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author raviu
 */
public class RockPaperScissors {
    
     // Declaring global variables;         

        
    public static void main(String[] args) {
        
        //Setting up boolean for do-while play loop.
        String playGame = "Y";
        
        // Declaring scanner used for player input.
        Scanner userInputRounds = new Scanner(System.in);
        Scanner userInput = new Scanner(System.in);
        Scanner userPlay = new Scanner(System.in);
        
        // Declaring variables used for the game
        int numberOfRounds = 0;
        
        int gamesPlayed = 0;
        int gamesTied = 0;
        int gamesWon = 0;
        int gamesLost = 0;
        
        int playerChoice = 0;
        
        // Welcome to Rock Paper Scissors.
        System.out.println("Welcome to Rock Paper Scissors!");
        
        // Start do-while game loop. 
        do{
            System.out.println("Choose a number of rounds between 1 and 10");

            // Determining Number of Rounds
            numberOfRounds = userInputRounds.nextInt();
            userInputRounds.nextLine();

            if (numberOfRounds <= 0 || numberOfRounds >= 11){
                System.out.println("Illegal Number of Rounds! How about next time you play nice!");
                System.exit(0);          
            } else {

                //Begin For Loop to play for user Determined number of rounds.         
                for (int i = 0; i < numberOfRounds; i++){
                    // Ask user for RPS choice
                    System.out.println("Do you choose Rock (1), Paper (2) or Scisors (3)? Please enter the associated numeric value.");
                    playerChoice = userInput.nextInt();
                    userInput.nextLine();

                    /* Calling the CompareHands Function in a switch statement to determine winner. 
                    ** Case 1 for Ties; Case 2 for Losses; Case 3 for Wins. 
                    */
                    switch(CompareHands(playerChoice)){
                        case 1:
                            gamesPlayed++;
                            gamesTied++;
                            break;
                        case 2:
                            gamesPlayed++;
                            gamesLost++;
                            break;
                        case 3:
                            gamesPlayed++;
                            gamesWon++;
                            break;
                    } 
                }

                //Calling PrintWinner Function to output game scores and determine the winner
                PrintWinner(gamesPlayed, gamesTied, gamesWon, gamesLost);
                
                gamesPlayed = 0;
                gamesTied = 0;
                gamesWon = 0;
                gamesLost = 0;

                System.out.println("Would you like to Play again? (Y/N)");
                playGame = userPlay.next();
                userPlay.nextLine();
            }
        } while (playGame.equalsIgnoreCase("y") || playGame.equalsIgnoreCase("yes") );
        
        System.out.println("Thank you for playing Rock-Paper-Scissors! Hope to see you again!");
         
    }
    
    /*
    ** Function Name: CompareHands
    ** Return Type: Int
    ** Purpose: Generates the Computer's Hand for RockPaperScissors and Determines the Winner
    */
    public static int CompareHands(int playerHand){
        
        // Declaring needed variables.
        int compChoice= 0;
        Random hand = new Random();
        
        compChoice = hand.nextInt(3)+1;
        
        // Switch used to determine Winner
        switch(playerHand){
            case 1:
                if (compChoice == playerHand){
                    System.out.println("Tie!");
                    return 1;
                } else if (compChoice == 2){
                    System.out.println("Loss!");
                    return 2;
                } else {
                    System.out.println("Win!");
                    return 3;
                }
            case 2:
                if (compChoice == playerHand){
                    System.out.println("Tie!");
                    return 1;
                } else if (compChoice == 3){
                    System.out.println("Loss!");
                    return 2;
                } else {
                    System.out.println("Win!");
                    return 3;
                }
            case 3:
                if (compChoice == playerHand){
                    System.out.println("Tie!");
                    return 1;
                } else if (compChoice == 1){
                    System.out.println("Loss!");
                    return 2;
                } else {
                    System.out.println("Win!");
                    return 3;
                }
        }
        
        return 0;
    }
    
    /*
    ** Function Name: PrintWinner
    ** Return Type: Void
    ** Purpose: Prints the game's score stats and determines the winner.
    */
    public static void PrintWinner(int played, int tied, int won, int lost){
        System.out.println("You played "  + played + " games");
        System.out.println("You tied " + tied + " times" );
        System.out.println("You won " + won + " times" );
        System.out.println("You lost " + lost + " times" );
        
        // Determining the overall winner.
        if (tied > won && tied > lost ){
            System.out.println("You tied the computer!");
        } else if ( won == lost){
            System.out.println("You tied the computer!");
        }  else if (lost > won){
            System.out.println("You lost to the computer.");
        } else {
            System.out.println("You won against the computer!");
        }
    }
}
