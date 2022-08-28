package AI;

public class AI {
    private final int max_depth;
    private final GameLogic logic = new GameLogic();
    private final int[][] evaluation_table = {
                                                {3, 4, 5, 7, 5, 4, 3},
                                                {4, 6, 8, 10, 8, 6, 4},
                                                {5, 8, 11, 13, 11, 8, 5},
                                                {5, 8, 11, 13, 11, 8, 5},
                                                {4, 6, 8, 10, 8, 6, 4},
                                                {3, 4, 5, 7, 5, 4, 3}
                                            };
    public AI(int max) {
        this.max_depth = max;
    }

    public int playBoard(int[][] board){
        int move;
        GameTree tree = new GameTree(board, max_depth);
        move = MinMaxTreeTraverse(tree);
        return move;
    }

    private int MinMaxTreeTraverse(GameTree tree) {
        int move = -2;
//        int max_util = dfsTraverse(tree.Root);
        int max_util = dfsTraverseWithPruning(tree.Root);
        for (TreeNode child : tree.Root.children) {
            System.out.print(child.utility_score() + " ");
        }
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
        } else {
            int util = evaluateBoard(node.getBoard(), node.level);
            node.setUtilityScore(util);
        }
        return node.utility_score();
    }

    private int dfsTraverseWithPruning(TreeNode node) {
        if(!node.is_leaf){
            for (TreeNode child: node.children) {
                child.setAlpha(node.alpha());
                child.setBeta(node.beta());

                // pruning condition
                int temp_util;
                if(node.alpha() <= node.beta()) temp_util = dfsTraverseWithPruning(child);
                else                            break;

                if(node.utility_score() == -1)
                    node.setUtilityScore(temp_util);
                else if(node.max_or_min) {
                    if(temp_util > node.utility_score()){
                        node.setUtilityScore(temp_util);
                        node.setAlpha(temp_util);
                    }
                } else {
                    if(temp_util < node.utility_score()) {
                        node.setUtilityScore(temp_util);
                        node.setBeta(temp_util);
                    }
                }
            }
        } else {
            int util = evaluateBoard(node.getBoard(), node.level);
            node.setUtilityScore(util);
            if(node.max_or_min)     node.setAlpha(util);
            else                    node.setBeta(util);
        }
        return node.utility_score();
    }

    private int compareBord(int[][] original, int[][] after_mm) {
        int diff_col = -1;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (original[j][i] != after_mm[j][i]) {
                    diff_col = i;
                    break;
                }
            }
        }
        return diff_col;
    }

    // maybe good enough
    private int evaluateBoard(int[][]  board, int level) {
        int sum = 0, winner;

        // check for possible win condition

        if((winner = logic.hasWinner(board)) != -1) {
            if(winner == 1)         return -9999 * ((max_depth+1) - level);
            else if(winner == 2)    return 9999 * ((max_depth+1) - level);
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if(board[i][j] == 2) {
                    sum += evaluation_table[i][j];
                } else if (board[i][j] == 1) {
                    sum -= evaluation_table[i][j];
                }
            }
        }
        return sum * ((max_depth+1) - level);
    }
}
