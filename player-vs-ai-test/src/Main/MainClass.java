package Main;

import AI.AI;
import AI.GameTree;
import Game.Game;

import java.io.FileNotFoundException;

public class MainClass {
    public static void main(String[] args) throws FileNotFoundException {
//        test();
        driver();
    }

    public static void driver() throws FileNotFoundException {
        Game game = new Game(true);
        game.play();
        // ok initial local repo again
    }

    public static void test() throws FileNotFoundException {
        int[][] new_board = {
                                {0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 1, 0, 0},
                                {0, 0, 0, 0, 2, 0, 0},
                                {0, 0, 1, 2, 1, 2, 0}
                            };
        GameTree game_tree = new GameTree(new_board, 6);
        AI ai = new AI(12);
        ai.calcEval();
//        game_tree.generateGameTree();
        game_tree.printGameTree();
    }
}
