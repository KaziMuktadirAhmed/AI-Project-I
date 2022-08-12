package Game;

public class Game {
    private int board[][] = new int[6][7];

    private int hasWinner () {
        int winner = -1;
        
        return winner;
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
