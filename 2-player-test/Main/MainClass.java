package Main;

import Game.Game;

public class MainClass {
    public static void main(String[] args) {
//        System.out.println("hello world");
        test();
    }

    public static void test() {
        Game game = new Game();
//        game.showBoard();
        game.play();
//        int count = 1;
//        for (int i=0; i<20; i++) {
//            System.out.print(count);
//            if(count == 1) count = 2;
//            else if (count == 2) {
//                count = 1;
//            }
//        }
    }
}
