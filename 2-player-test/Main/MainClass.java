package Main;

import Game.Game;

public class MainClass {
    public static void main(String[] args) {
//        System.out.println("hello world");
        test();
        driver();
    }

    public static void driver() {
        Game game = new Game();
        game.play();
    }

    public static void test() {

    }
}
