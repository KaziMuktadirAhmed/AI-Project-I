package AI;

public class GameTree {
    private final int cut_off_depth = 12;
    GameLogic game_logic = new GameLogic();
    public TreeNode Root = new TreeNode(new int[6][7], 0);

    public GameTree() {
        generateGameTree();
    }

    private void generateGameTree() {
        generateChildNode(Root);
    }

    private void generateChildNode(TreeNode node) {
        int[][] new_board = new int[6][7];
        if (!canExpand(node.getBoard()) || node.level == cut_off_depth) {
            node.is_leaf = true;
            // evaluate utility score
            return;
        }
        for (int i = 0; i < 7; i++) {
            if(game_logic.checkInputValidity(i, node.getBoard())) {
                game_logic.copyBoard(node.getBoard(), new_board);
                game_logic.turn(i, 1, new_board);
                node.children.add(new TreeNode(new_board, node.level+1));
            }
        }
    }

    private boolean canExpand(int[][] board) {
        if (game_logic.hasWinner(board) != -1)
            return false;
        else return !game_logic.checkForDraw(board);
    }

    public void printGameTree() {
        printNode(Root, "Root");
    }

    public void printNode(TreeNode node, String overhead) {
//        String output = overhead;
        System.out.println(overhead+" "+node);
        game_logic.showBoard(node.getBoard());
        for (TreeNode child: node.children) {
            printNode(child, overhead+"child");
        }
    }
}

