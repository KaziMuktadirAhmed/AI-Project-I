package Main;

import Game.Game;

public class MainClass {
    public static void main(String[] args) {
//        System.out.println("hello world");
        test();
    }

    public static void test() {
        Game game = new Game();
        game.showBoard();
    }
}
