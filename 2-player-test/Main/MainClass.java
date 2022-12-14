package Main;

import Game.Game;

public class MainClass {
    public static void main(String[] args) {
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
                col = 6;
            } else {
                row = 0;
                col = 7 - itr - 1;
            }
            while(row < 6 && col > -1) {
                System.out.println("row: " + row + " col: " + col);
//               checking the iteration index for the loop
                col--;
                row++;
            }
        }
    }
}
