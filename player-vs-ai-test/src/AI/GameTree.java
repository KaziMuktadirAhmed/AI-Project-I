package AI;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class GameTree {
    private final GameLogic game_logic = new GameLogic();
    private int cut_off_depth;
    public TreeNode Root;

    public GameTree(int[][] board, int max_move) throws FileNotFoundException {
        setRoot(board);
        setCutOffDepth(max_move);
        this.Root.max_or_min = false;
        generateGameTree();
    }

    private void setRoot(int[][] board) {
        this.Root = new TreeNode(board, 0);
    }

    private void setCutOffDepth(int level) {
        this.cut_off_depth = level;
    }

    private void generateGameTree() throws FileNotFoundException {
        PrintStream output_file = new PrintStream(new FileOutputStream("output.txt"));
        System.setOut(output_file);
        generateChildren(Root);
        System.out.println("end");
    }

    private void generateChildren(TreeNode node) {
        int[][] new_board = new int[6][7];
        if (!canExpand(node.getBoard()) || node.level == cut_off_depth) {
            node.is_leaf = true;
            //  evaluate utility score
            node.setUtilityScore(evaluateUtility(node.getBoard()));
            return;
        }
        for (int i = 0; i < 7; i++) {
            if(game_logic.checkInputValidity(i, node.getBoard())) {
                game_logic.copyBoard(node.getBoard(), new_board);
                game_logic.turn(i, ((node.level+2)%2)+1, new_board);

                TreeNode child = new TreeNode(new_board, node.level+1);
                child.max_or_min = !node.max_or_min;

                generateChildren(child);
                node.children.add(child);
            }
        }
    }

    private boolean canExpand(int[][] board) {
        if (game_logic.hasWinner(board) != -1)
            return false;
        else return !game_logic.checkForDraw(board);
    }

    private int evaluateUtility(int[][] board) {
        return -1;
    }

    public void printGameTree() {
        printNode(Root, "Root");
    }

    public void printNode(TreeNode node, String overhead) {
//        String output = overhead;
        System.out.println(overhead+" "+node);
        System.out.println(node.level + " leaf:" + node.is_leaf + " is max:" + node.max_or_min);
        game_logic.showBoard(node.getBoard());
        for (TreeNode child: node.children) {
            printNode(child, overhead+"child");
        }
    }
}

