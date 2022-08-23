package AI;

import java.io.FileNotFoundException;

public class AI {
    private final int max_depth;

    public AI(int max) {
        this.max_depth = max;
    }

    public int playBoard(int[][] board, int player) throws FileNotFoundException {
        int move;
        GameTree tree = new GameTree(board, max_depth);
        move = MinMaxTreeTraverse(tree);
        return move;
    }

    private int MinMaxTreeTraverse(GameTree tree) {
        int move = -2;
        int max_util = dfsTraverse(tree.Root);
        for (TreeNode child: tree.Root.children) {
            if(child.utility_score() == max_util){
                move = compareBord(tree.Root.getBoard(), child.getBoard());
                break;
            }
        }
        return move;
    }

    private int dfsTraverse(TreeNode node) {
        if(!node.is_leaf){
            for (TreeNode child: node.children) {
                int temp_util = dfsTraverse(child);
                if(node.utility_score() == -1)
                    node.setUtilityScore(temp_util);
                else if(node.max_or_min) {
                    if(temp_util > node.utility_score())
                        node.setUtilityScore(temp_util);
                } else {
                    if(temp_util < node.utility_score())
                        node.setUtilityScore(temp_util);
                }
            }
        }
        return node.utility_score();
    }

    private int compareBord(int[][] original, int[][] after_mm) {
        int diff_col = -1;
        int diff_count = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if(original[j][i] != after_mm[j][i]) {
                    diff_col = i;
                    diff_count++;
                }
            }
        }
        System.out.println(diff_count);
        return diff_col;
    }
}
