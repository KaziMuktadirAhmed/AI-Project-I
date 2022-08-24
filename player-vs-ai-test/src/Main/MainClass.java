package Main;

import AI.GameTree;
import Game.Game;

import java.io.FileNotFoundException;

public class MainClass {
    public static void main(String[] args) throws FileNotFoundException {
        test();
//        driver();
    }

    public static void driver() throws FileNotFoundException {
        Game game = new Game(false);
        game.play();
        // ok initial local repo again
    }

    public static void test() throws FileNotFoundException {
        GameTree game_tree = new GameTree(new int[6][7], 6);
//        game_tree.generateGameTree();
        game_tree.printGameTree();
    }
}
