package AI;

public class GameLogic {
    public void turn(int place_tile, int player, int[][] board) {
        for (int i = 0; i < 6; i++) {
            if (i == 5) {
                if (board[i][place_tile] == 0) {
                    board[i][place_tile] = player;
                    break;
                }
            }

            if (board[i][place_tile] != 0) {
                board[i - 1][place_tile] = player;
                break;
            }
        }
    }

    public boolean checkForDraw(int[][] board) {
        boolean is_draw = true;
        for (int j = 0; j < 7; j++) {
            if (checkInputValidity(j, board)) {
                is_draw = false;
                break;
            }
        }
        return is_draw;
    }

    public boolean checkInputValidity(int input, int[][] board) {
        boolean is_valid_input = true;
        if (input < 0 || input > 6)
            is_valid_input = false;
        else if (board[0][input] != 0)
            is_valid_input = false;
        return is_valid_input;
    }

    public int hasWinner(int[][] board) {
        if (checkBoardHorizontally(board) != -1)
            return checkBoardHorizontally(board);
        else if (checkBordVertically(board) != -1)
            return checkBordVertically(board);
        else if (checkBordDiagonallyPrimary(board) != -1)
            return checkBordDiagonallyPrimary(board);
        else if (checkBordDiagonallySecondary(board) != -1)
            return checkBordDiagonallySecondary(board);
        else
            return -1;
    }

    private int checkBoardHorizontally(int[][] board) {
        for (int i = 0; i < 6; i++) {
            int match_count = 0;
            for (int j = 0; j < 7; j++) {
                if (board[i][j] != 0 && j > 0 && board[i][j] == board[i][j - 1]) {
                    match_count++;
                    if (match_count >= 3) {
                        return board[i][j];
                    }
                } else {
                    match_count = 0;
                }
            }
        }
        return -1;
    }

    private int checkBordVertically(int[][] board) {
        for (int j = 0; j < 7; j++) {
            int match_count = 0;
            for (int i = 0; i < 6; i++) {
                if (board[i][j] != 0 && i > 0 && board[i][j] == board[i - 1][j]) {
                    match_count++;
                    if (match_count >= 3) {
                        return board[i][j];
                    }
                } else {
                    match_count = 0;
                }
            }
        }
        return -1;
    }

    private int checkBordDiagonallyPrimary(int[][] board) {
        for (int itr = -2; itr < 4; itr++) {
            int row, col;
            if (itr < 1) {
                row = -1 * itr;
                col = 0;
            } else {
                row = 0;
                col = itr;
            }
            int match_count = 0;
            while (row < 6 && col < 7) {
                if (board[row][col] != 0 && row > 0 && col > 0 && board[row][col] == board[row - 1][col - 1]) {
                    match_count++;
                    if (match_count >= 3) {
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

    private int checkBordDiagonallySecondary(int[][] board) {
        for (int itr = -2; itr < 4; itr++) {
            int row, col;
            if (itr < 1) {
                row = -1 * itr;
                col = 6;
            } else {
                row = 0;
                col = 7 - itr - 1;
            }
            int match_count = 0;
            while (row < 6 && col > -1) {
                if (board[row][col] != 0 && row > 0 && col < 6 && board[row][col] == board[row - 1][col + 1]) {
                    match_count++;
                    if (match_count >= 3) {
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

    public void showBoard(int[][] board) {
        String output = "";

        System.out.println("4-Connect");
        System.out.println("=========");

        for(int i=0; i<7; i++) output += (i + " ");
        output += "\n";
        for(int i=0; i<7; i++) output += "==";
        output += "\n";

        for (int i=0; i<6; ++i) {
            String line = "";
            for (int j=0; j<7; ++j) {
                line += (board[i][j] + " ");
            }
            output += (line + "\n");
        }
        System.out.println(output);
    }

    public void copyBoard(int[][] source, int[][] dest) {
        for (int i = 0; i < 6; i++) {
            System.arraycopy(source[i], 0, dest[i], 0, 7);
        }
    }
}
