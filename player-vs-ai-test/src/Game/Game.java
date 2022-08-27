package Game;

import AI.AI;
import java.util.Scanner;

public class Game {
    private final boolean with_ai;
    private final AI ai = new AI(8);
    private final int[][] board = new int[6][7];

    public Game(boolean with_ai) {
        this.with_ai = with_ai;
    }

    public void play (){
        boolean play_game = true;
        int player_instance = 2;

        while (play_game) {
            showBoard();

            int winner = hasWinner();
            if(winner != -1) {
                System.out.println(winner + " is the winner.");
                play_game = false;
                continue;
            }

            if(checkForDraw()) {
                System.out.println("Draw.");
                play_game = false;
                continue;
            }

            if(player_instance == 1) player_instance = 2;
            else player_instance = 1;

            turn(player_instance);
        }
    }

    private void turn (int player){
        int place_tile;
        if(with_ai && player == 2){
            long start = System.nanoTime();
            place_tile = ai.playBoard(board);
            long end = System.nanoTime();
            System.out.println("Time taken: " + (end - start));
        }
        else        place_tile = inputPrompt(player);
        for(int i=0; i<6; i++) {
            if (i == 5) {
                if(board[i][place_tile] == 0) {
                    board[i][place_tile] = player;
                    break;
                }
            }

            if(board[i][place_tile] != 0) {
                board[i-1][place_tile] = player;
                break;
            }
        }
    }

    private int inputPrompt (int player) {
        int place_tile = -1;
        Scanner scan_input = new Scanner(System.in);

        System.out.print("Move for player-" + player +": ");
        place_tile = scan_input.nextInt();

        while (!checkInputValidity(place_tile)) {
            System.out.println("Invalid move. Please enter a valid move.");
            System.out.print("Move for player" + player +": ");
            place_tile = scan_input.nextInt();
        }

        return place_tile;
    }

    private boolean checkForDraw() {
        boolean is_draw = true;
        for(int j=0; j<7; j++) {
            if(checkInputValidity(j)) {
                is_draw = false;
                break;
            }
        }
        return is_draw;
    }

    private boolean checkInputValidity (int input) {
        boolean is_valid_input = true;
        if(input < 0 || input > 6)
            is_valid_input = false;
        else if (board[0][input] != 0)
            is_valid_input = false;
        return is_valid_input;
    }

    private int hasWinner () {
        if(checkBoardHorizontally() != -1)
            return checkBoardHorizontally();
        else if (checkBordVertically() != -1)
            return checkBordVertically();
        else if (checkBordDiagonallyPrimary() != -1)
            return checkBordDiagonallyPrimary();
        else if (checkBordDiagonallySecondary() != -1)
            return checkBordDiagonallySecondary();
        else
            return -1;
    }

    private int checkBoardHorizontally() {
        for(int i=0; i<6; i++) {
            int match_count = 0;
            for (int j=0; j<7; j++) {
                if(board[i][j] != 0 && j > 0 && board[i][j] == board[i][j-1]) {
                    match_count++;
                    if(match_count >= 3) {
                        return board[i][j];
                    }
                } else {
                    match_count = 0;
                }
            }
        }
        return -1;
    }

    private int checkBordVertically() {
        for(int j=0; j<7; j++) {
            int match_count = 0;
            for (int i=0; i<6; i++) {
                if(board[i][j] != 0 && i > 0 && board[i][j] == board[i-1][j]) {
                    match_count++;
                    if(match_count >= 3) {
                        return board[i][j];
                    }
                } else {
                    match_count = 0;
                }
            }
        }
        return -1;
    }

    private int checkBordDiagonallyPrimary() {
        for(int itr=-2; itr<4; itr++) {
            int row, col;
            if(itr < 1) {
                row = -1 * itr;
                col = 0;
            } else {
                row = 0;
                col = itr;
            }
            int match_count = 0;
            while(row < 6 && col < 7) {
                if(board[row][col] != 0 && row > 0 && col > 0 && board[row][col] == board[row-1][col-1]) {
                    match_count++;
                    if(match_count >= 3){
                        return board[row][col];
                    }
                } else {
                    match_count = 0;
                }
                col++;
                row++;
            }
        }
        return -1;
    }

    private int checkBordDiagonallySecondary() {
        for(int itr=-2; itr<4; itr++) {
            int row, col;
            if(itr < 1) {
                row = -1 * itr;
                col = 6;
            } else {
                row = 0;
                col = 7 - itr - 1;
            }
            int match_count = 0;
            while(row < 6 && col > -1) {
                if(board[row][col] != 0 && row > 0 && col < 6 && board[row][col] == board[row-1][col+1]) {
                    match_count++;
                    if(match_count >= 3){
                        return board[row][col];
                    }
                } else {
                    match_count = 0;
                }
                col--;
                row++;
            }
        }
        return -1;
    }

    public void showBoard() {
        StringBuilder output = new StringBuilder();

        System.out.println("4-Connect");
        System.out.println("=========");

        for(int i=0; i<7; i++) {
            if(i == 0) output.append("|");
            output.append(i).append("|");
        }   output.append("\n");
        output.append("=".repeat(15));
        output.append("\n");

        for (int i=0; i<6; ++i) {
            StringBuilder line = new StringBuilder();
            for (int j=0; j<7; ++j) {
                if(j == 0) line.append("|");
                if(board[i][j] != 0)    line.append(board[i][j]).append("|");
                else                    line.append("_" + "|");
            }
            output.append(line).append("\n");
        }
        System.out.println(output);
    }
}
