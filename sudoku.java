package Sudoku;

import java.util.Scanner;

public class Sudoku {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] board = new int[3][3];
        int attempts = 3;
        printBoard(board);
        boolean isPlaying = true;
        Long startTime = System.nanoTime();
        while (isPlaying) {
            System.out.print("\nPlease enter the row number you want to make a move (1-3): ");
            int row = scanner.nextInt();
            System.out.print("\nPlease enter the column number you want to make a move (1-3): ");
            int column = scanner.nextInt();
            System.out.print("\nPlease enter the number you want to place (1-9): ");
            int move = scanner.nextInt();
            System.out.println(
                    "\n\n----------------------------------------------------------------------------------\n\n");

            if (row < 1 || row > 3 || column < 1 || column > 3 || move < 1 || move > 9) {
                System.out.println(
                        "\nInvalid input. Row and column numbers must be between 1 and 3, and the move must be between 1 and 9.");
                attempts--;
                System.out.println("\nRemaining attempts: " + attempts);
                System.out.println();
                printBoard(board);
                if (attempts == 0) {
                    System.out.println("\nYou have run out of attempts. The game is over.");
                    Long endTime = System.nanoTime();
                    System.out.println("\nTime elapsed: " + ((endTime - startTime) / 1_000_000_000.0) + " Seconds");
                    break;
                }
                continue;
            }

            row--;
            column--;

            if (isValidMove(board, row, column, move)) {
                board[row][column] = move;
                System.out.println();
                printBoard(board);

                if (isGameWon(board)) {
                    System.out.println("\nThe game is over! Congratulations, you solved the sudoku!");
                    Long endTime = System.nanoTime();
                    System.out.println("\nTime taken to solve the game: " + ((endTime - startTime) / 1_000_000_000.0)
                            + " Seconds");
                    isPlaying = false;
                }
            } else {
                attempts--;
                System.out.println("Remaining attempts: " + attempts);
                System.out.println();
                if (attempts == 0) {
                    System.out.println("You have run out of attempts. The game is over.");
                    Long endTime = System.nanoTime();
                    System.out.println("\nTime elapsed: " + ((endTime - startTime) / 1_000_000_000.0) + " Seconds");
                    break;
                }
                printBoard(board);
            }
        }
    }

    public static void printBoard(int[][] board) {
        System.out.print("\t");
        for (int i = 1; i < 4; i++) {
            System.out.print(i + "   ");
        }
        System.out.println();
        System.out.print("\t");
        for (int i = 1; i < 4; i++) {
            System.out.print("-   ");
            
        }
        System.out.println();

        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + "-)" + "\t");
            for (int j = 0; j < 3; j++) {
                System.out.print((board[i][j] == 0 ? "_" : board[i][j]) + "   ");
            }
            System.out.println();
        }
    }

    public static boolean isValidMove(int[][] board, int row, int column, int move) {
        if (board[row][column] != 0) {
            System.out.println("\nInvalid move: The specified row and column are already filled.\n");
            return false;
        }

        for (int j = 0; j < 3; j++) {
            if (board[row][j] == move) {
                System.out.println("\nInvalid move. You have already used this number.\n");
                return false;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[i][column] == move) {
                System.out.println("\nInvalid move. You have already used this number.\n");
                return false;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int number = board[i][j];
                if (number == move) {
                    System.out.println("\nInvalid move. You have already used this number.\n");
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isGameWon(int[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            if (!isRowValid(board, i) || !isColumnValid(board, i)) {
                return false;
            }
        }

        if (!isBlockValid(board)) {
            return false;
        }

        return true;
    }

    public static boolean isRowValid(int[][] board, int row) {
        boolean[] isValid = new boolean[10];
        for (int j = 0; j < 3; j++) {
            int number = board[row][j];
            if (number == 0 || isValid[number]) {
                return false;
            }
            isValid[number] = true;
        }
        return true;
    }

    public static boolean isColumnValid(int[][] board, int column) {
        boolean[] isValid = new boolean[10];
        for (int i = 0; i < 3; i++) {
            int number = board[i][column];
            if (number == 0 || isValid[number]) {
                return false;
            }
            isValid[number] = true;
        }
        return true;
    }

    public static boolean isBlockValid(int[][] board) {
        boolean[] isValid = new boolean[10];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int number = board[i][j];
                if (number == 0 || isValid[number]) {
                    return false;
                }
                isValid[number] = true;
            }
        }
        return true;
    }
}
