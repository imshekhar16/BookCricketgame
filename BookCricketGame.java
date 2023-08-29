package com.aurionpro.test;

import java.util.Random;

import java.util.Scanner;

public class BookCricketGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Book Cricket Game!");
        displayGameRules();

        System.out.print("Enter 1 to Start Game and 0 to Exit: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.print("Enter Player 1 name: ");
            String player1Name = scanner.next();

            System.out.print("Enter Player 2 name: ");
            String player2Name = scanner.next();

            int totalPages = 300;
            Random random = new Random();

            int player1Score = playGame(random, totalPages, player1Name, false,0);
            System.out.println("\n" + player1Name + "'s total score: " + player1Score);
            System.out.println("Rounds taken by " + player1Name + ": " + calculateTurns(player1Score));

            int player2Score = playGame(random, totalPages, player2Name, true,player1Score);
            System.out.println("\n" + player2Name + "'s total score: " + player2Score);

            int player2Target = player1Score + 1;

            if (player2Score < player2Target) {
                int player2TurnsToWin = calculateTurns(player2Score, player2Target);
                System.out.println("\n" + player2Name + " needs " + player2Target + " points to win in " +
                                   player2TurnsToWin + " turns.");
            } else {
                System.out.println("\n" + player2Name + " reaches the target score. Stopping the game.");
            }

            String winner = determineWinner(player1Name, player1Score, player2Name, player2Score, player2Target);
            System.out.println("\n" + winner + " wins the game!");

        } else {
            System.out.println("Exiting the game.");
        }
    }

    public static void displayGameRules() {
        System.out.println("\nGame Rules:");
        System.out.println("1. Player 1 opens the book and reads a page number.");
        System.out.println("2. Player's score = page number % 7 (always between 0 - 6).");
        System.out.println("3. Continue until the page number results in a 0 score.");
        System.out.println("4. Player 2 then plays and tries to beat Player 1's score.");
        System.out.println("5. In case of a tie, the player with fewer turns wins.");
        System.out.println();
    }




    public static int playGame(Random random, int totalPages, String playerName, boolean showRoundsLeft, int player1Score) {
        System.out.println("\n" + playerName + ", it's your turn!");

        int score = 0;
        int rounds = 0;  // Initialize rounds counter
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter 1 to open book: ");
            int openBook = scanner.nextInt();

            if (openBook == 1) {
                int pageNumber = random.nextInt(totalPages) + 1;
                int currentScore = pageNumber % 7;

                System.out.print("Page number: " + pageNumber + ", Points: " + currentScore);

                if (currentScore == 0) {
                    break;
                }

                score += currentScore;
                rounds++;

                if (showRoundsLeft) {
                    System.out.print(", " + playerName + "'s score: " + score + ", Rounds taken: " + rounds);  // Display rounds taken
                } else {
                    System.out.print(", " + playerName + "'s score: " + score);
                }

                System.out.println();

                if (showRoundsLeft && score >= player1Score) {
                    System.out.println("\n" + playerName + " reaches the target score. Stopping the game.");
                    break;
                }
            }
        }

        return score;
    }




    public static int calculateTurns(int score) {
        if (score == 0) {
            return 0;
        }

        return (score / 7) + 1;
    }

    public static int calculateTurns(int score, int target) {
        int remainingScore = target - score;

        if (remainingScore <= 0) {
            return 0;
        }

        return (remainingScore / 7) + 1;
    }

    public static String determineWinner(String player1Name, int player1Score, String player2Name, int player2Score, int player2Target) {
        if (player1Score > player2Score) {
            return player1Name;
        } else if (player2Score > player1Score) {
            return player2Name;
        } else {
            int player1Turns = calculateTurns(player1Score);
            int player2Turns = calculateTurns(player2Score);

            if (player1Turns < player2Turns) {
                return player1Name;
            } else if (player2Turns < player1Turns && player2Turns!=-1) {
                return player2Name;
            } else {
                return "It's a tie!";
            }
        }
    }
}

