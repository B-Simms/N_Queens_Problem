// Blake Simmons
// November 7, 2018
// N Queens Solution

package com.blake_simmons;


//This is a program which will attempt to find an arrangement of n number of queens
//on a chessboard that is n by n size where no queen in danger from attack by another queen
 //ex. for 4 queens
public class Main {

    static int count = 0;
    static int nodesCount = 1;

    public static void main(String[] args) {

        // declare necessary variables
        int numberOfQueens = 12; //number of Queens to place and number of rows/columns
        int chessBoard[][] = new int[numberOfQueens][numberOfQueens]; //create "chess board"

        //populate board with 0 (no queen placed) as default
        for (int i = 0; i < numberOfQueens; i++) {
            for (int j = 0; j < numberOfQueens; j++) {
                chessBoard[i][j] = 0;
            }
        }

        //perform the backtracking method to find solution and print
        placeQueens(chessBoard, 0);

        //if solutions found, print how many. if none found, print.
        if(count > 0) {
            System.out.println("\nTotal solutions found: " + count);
        }
        else{
            System.out.println("No solutions found.");
        }
        System.out.println("Total nodes generated: " + nodesCount);
    }

    public static boolean placeQueens(int chessBoard[][], int col){

        //if the column index has surpassed the last column
        // a solution has been found.
        //print the first solution as a grid, and also the coordinates
        if(col == (chessBoard.length)){
            //increment the number of solutions found
            count++;
            if(count == 1) {
                System.out.println("First solution found: ");
                printBoard(chessBoard);
                printCoordinates(chessBoard);
            }
        }

        //for this column, test each row for safe placement
        for(int i = 0; i < chessBoard.length; i++){

            //if a safe placement is found, place queen (set value to 1)
            if( promising(chessBoard, i, col) ){
                chessBoard[i][col] = 1;
                nodesCount++;

                //recursively test the next column
                if( placeQueens(chessBoard, (col + 1))){
                    return true;
                }

                //if no safe placement is found in the next column, remove queen (reset value to 0)
                //and continue to next row.
                //backtracking occurs here
                chessBoard[i][col] = 0;
            }
        }

        //no safe placement found for this column
        return false;
    }

    public static boolean promising(int chessBoard[][], int row, int col){

        //coordinates to test for conflict against input coordinates (row, col)
        int x, y;

        //test for horizontal conflict
        //if any other values in this row = 1, this is not a safe placement
        for( x = 0; x < col; x++){
            if(chessBoard[row][x] == 1){
                return false;
            }
        }

        //test for all locations behind this column and diagonally upwards
        for(x = row, y = col; x >= 0 && y >= 0; x--, y--){
            if(chessBoard[x][y] == 1){
                return false;
            }
        }

        //test for all locations behind this column and diagonally downwards
        for(x = row, y = col; x < (chessBoard.length) && y >= 0; x++, y--){
            if(chessBoard[x][y] == 1){
                return false;
            }
        }

        //if no conflicts found, location is safe
        return true;
    }

    //this method prints the 'chess board' matrix, with 1's representing queen placement.
    public static void printBoard( int chessBoard[][]){
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard.length; j++) {
                System.out.print(chessBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    //this method prints all of the coordinates at which a queen has been placed in this solution
    public static void printCoordinates( int chessBoard[][]){
        System.out.println("\nQueens placed at coordinates: ");
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard.length; j++) {
                if(chessBoard[i][j] == 1){
                    System.out.print("( " + i + ", " + j + " )");
                }
            }
            System.out.print(", ");
        }
        System.out.println();
    }

}
