package Main;

import AI.GameTree;
import Game.Game;

public class MainClass {
    public static void main(String[] args) {
        test();
//        driver();
    }

    public static void driver() {
        Game game = new Game();
        game.play();
    }

    public static void test() {
        GameTree game_tree = new GameTree();
        game_tree.generateGameTree();
    }
}
