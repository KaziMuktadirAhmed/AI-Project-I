package AI;

public class GameTree {
    GameLogic game_logic = new GameLogic();
    public TreeNode Root = new TreeNode(new int[6][7]);

    public GameTree() {
        generateGameTree();
    }

    private void generateGameTree() {
        int[][] new_board = new int[6][7];
        for (int i = 0; i < 7; i++) {
            if(game_logic.checkInputValidity(i, Root.getBoard())) {
                game_logic.copyBoard(Root.getBoard(), new_board);
                game_logic.turn(i, 1, new_board);
                Root.childs.add(new TreeNode(new_board));
            }
        }
    }

    private void generateChildNode(TreeNode node) {
        int[][] new_board = new int[6][7];

        if (canExpand(node.getBoard())) {
            
        }

        for (int i = 0; i < 7; i++) {

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
        for (TreeNode child: node.childs) {
            printNode(child, overhead+"child");
        }
    }
}

