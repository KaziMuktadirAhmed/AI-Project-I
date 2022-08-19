package AI;

import java.util.ArrayList;

public class TreeNode {
    public boolean is_leaf = false;
    public boolean max_or_min;
    public ArrayList<TreeNode> childs = new ArrayList<>();

    private int board[][];
    private int alpha = Integer.MIN_VALUE;
    private int beta = Integer.MAX_VALUE;
    private int utility_score;

    public TreeNode(int[][] board) {
        this.board = board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int alpha() {
        return alpha;
    }

    public int beta() {
        return beta;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public void setBeta(int beta) {
        this.beta = beta;
    }

    public int utility_score() {
        return utility_score;
    }

    public void setUtilityScore(int utility_score) {
        this.utility_score = utility_score;
    }

    public void showBoard() {
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
}
