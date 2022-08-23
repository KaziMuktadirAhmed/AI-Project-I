package Main;

import AI.GameTree;
import Game.Game;

import java.io.FileNotFoundException;

public class MainClass {
    public static void main(String[] args) throws FileNotFoundException {
        test();
//        driver();
    }

    public static void driver() {
        Game game = new Game();
        game.play();
    }

    public static void test() throws FileNotFoundException {
        GameTree game_tree = new GameTree();
//        game_tree.generateGameTree();
        game_tree.printGameTree();
    }
}
