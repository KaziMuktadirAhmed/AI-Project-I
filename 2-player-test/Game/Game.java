package Game;

public class Game {
    private int board[][] = new int[6][7];

    private static final int player_1 = 1;
    private static final int player_2 = 2;

    public void play () {}

    private int hasWinner () {
        int winner = -1;

        // horizontal 4 tile check
        winner = checkBoardHorizontally();
//        if (board1 != null) return board1;
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
                }
                else {
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
