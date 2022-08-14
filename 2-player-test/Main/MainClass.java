package Main;

import Game.Game;

public class MainClass {
    public static void main(String[] args) {
//        System.out.println("hello world");
//        test();
        driver();
    }

    public static void driver() {
        Game game = new Game();
        game.play();
    }

    public static void test() {
        int board[][] = new int[6][7];

        for(int itr=-2; itr<4; itr++) {
            int row, col;
            if(itr < 1) {
                row = -1 * itr;
                col = 0;
            } else {
                row = 0;
                col = itr;
            }
            while(row < 6 && col < 7) {
                System.out.println("row: " + row + " col: " + col);
//               checking the iteration index for the loop
                col++;
                row++;
            }
        }
    }
}
