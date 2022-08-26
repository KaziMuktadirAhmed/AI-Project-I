package AI;

import java.util.ArrayList;

public class TreeNode {
    public boolean is_leaf = false;
    public final int level;
    public final int next_player_instance;
    public boolean max_or_min;
    public ArrayList<TreeNode> children = new ArrayList<>();

    private final int[][] board = new int[6][7];
    private int alpha = Integer.MIN_VALUE;
    private int beta = Integer.MAX_VALUE;
    private int utility_score = -1;

    public TreeNode(int[][] board, int level, int next_player) {
        setBoard(board);
        this.level = level;
        this.next_player_instance = next_player;
    }

    public int[][] getBoard() {
        return this.board;
    }

    public void setBoard(int[][] board) {
        for (int i = 0; i < 6; i++) {
            System.arraycopy(board[i], 0, this.board[i], 0, 7);
        }
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
}
