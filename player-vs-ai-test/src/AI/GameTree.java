package AI;

import java.util.Arrays;

public class GameTree {
    public TreeNode Root = new TreeNode(new int[6][7]);

    public void generateGameTree() {
        GameLogic game_logic = new GameLogic();
        int board[][] = new int[6][7];
        int[][] new_board = new int[6][7];
        for (int i = 0; i < 7; i++) {
            if(game_logic.checkInputValidity(i, board)) {
                game_logic.copyBoard(board, new_board);
                game_logic.turn(i, 1, new_board);
                game_logic.showBoard(new_board);
            }
        }
    }
}

