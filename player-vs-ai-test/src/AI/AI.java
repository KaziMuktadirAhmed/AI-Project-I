package AI;

public class AI {
    private int MinMaxTreeTraverse() {
        int move = -2;

        return move;
    }

    private int compareBord(int[][] original, int[][] after_mm) {
        int diff_col = -1;
        int diff_count = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if(original[j][i] != after_mm[j][i]) {
                    diff_col = i;
                    diff_count++;
                }
            }
        }
        System.out.println(diff_count);
        return diff_col;
    }
}
