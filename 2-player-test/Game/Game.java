package Game;

import java.util.Scanner;

public class Game {
    private final int[][] board = new int[6][7];

    private static final int player_1 = 1;
    private static final int player_2 = 2;

    public void play () {

    }

    private void turn (int player) {
        int place_tile = -1;
        Scanner scan_input = new Scanner(System.in);
        place_tile = scan_input.nextInt();

        while (!checkInputValidity(place_tile))
            place_tile = scan_input.nextInt();

        for(int i=0; i<6; i++) {
            if (i == 5) {
                if(board[i][place_tile] == 0)
                    board[i][place_tile] = player;
            } else {
                if(board[i][place_tile] != 0)
                    board[i][place_tile-1] = player;
            }
        }
    }

    private boolean chekForDraw () {
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
        int winner = -1;

        // horizontal 4 tile check
        winner = checkBoardHorizontally();
        return winner;
    }

    private int checkBoardHorizontally() {
        for(int i=0; i<6; i++) {
            int match_count = 0;
            for (int j=0; j<7; j++) {
                if(board[i][j] != 0 && j > 0 && board[i][j] == board[i][j-1]) {
                    match_count++;
                    if(match_count == 4) {
                        return board[i][j];
                    }
                } else {
                    match_count = 0;
                }
            }
        }
        return -1;
    }

    public void showBoard() {
        String output = "";
        for (int i=0; i<6; ++i) {
            String line = "";
            for (int j=0; j<7; ++j) {
                line += (board[i][j] + " ");
            }
            output += (line + "\n");
        }
        System.out.println(output);
    }
}
