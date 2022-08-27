import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Connect4 extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
    private static final int WIDTH, HEIGHT, widthUnit, heightUnit, boardLength, boardHeight;
    private static JFrame frame;
    private static Connect4 instance;
    private static Point p1, p2;

    public static void main(String[] args) {
        instance = new Connect4();
    }

    public Connect4() {
        setBackground(Color.WHITE);

        frame = new JFrame("Connect 4");
        frame.setBounds(50, 50, WIDTH, HEIGHT);
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        javax.swing.Timer timer = new javax.swing.Timer(10, this);
        timer.start();

        frame.addMouseListener(this);
        frame.addMouseMotionListener(this);
    }

    static {
        int initialWidth = 1800;
        int initialHeight = 1000;
        boardLength = 7;
        boardHeight = 6;
        widthUnit = initialWidth / (boardLength + 2);
        WIDTH = widthUnit * (boardLength + 2);
        heightUnit = initialHeight / (boardHeight + 2);
        HEIGHT = heightUnit * (boardHeight + 2);
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Board.draw(g);
    }

    public void mouseMoved(MouseEvent e) {
        Board.hover(e.getX());
    }

    public void mousePressed(MouseEvent e) {
        Board.drop();
    }

    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}

    static class PointPair {
        public Point p1, p2;

        PointPair(int x1, int y1, int x2, int y2) {
            p1 = new Point(x1, y1);
            p2 = new Point(x2, y2);
        }
    }

    static class Board {
        static Color[][] board;
        static Color[] players;
        static int turn;
        static int hoverX, hoverY;
        static boolean gameDone;

        static {
            board = new Color[boardLength][boardHeight];
            for (Color[] colors : board) {
                Arrays.fill(colors, Color.WHITE);
            }
            players = new Color[]{Color.YELLOW, Color.RED};
            turn = 0;
        }

        private static void test(Color[][] board) {
            int[][] board_arr = new int[6][7];
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 6; j++) {
                    if(board[i][j].equals(Color.WHITE))         board_arr[j][i] = 0;
                    else if (board[i][j].equals(Color.YELLOW))  board_arr[j][i] = 1;
                    else if (board[i][j].equals(Color.RED))     board_arr[j][i] = 2;
                }
            }
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    System.out.print(board_arr[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("end");
        }

        public static void draw(Graphics g) {
//            test(board);
            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            ((Graphics2D)(g)).setStroke(new BasicStroke(2.0f));

            for (int i = widthUnit; i <= WIDTH - widthUnit; i += widthUnit) {
                g.setColor(Color.BLACK);
                g.drawLine(i, heightUnit, i, HEIGHT - heightUnit);
                if (i == WIDTH - widthUnit) continue;
                for (int j = heightUnit; j < HEIGHT - heightUnit; j += heightUnit) {
                    g.setColor(board[i/widthUnit - 1][j/heightUnit - 1]);
                    g.fillOval(i + 5, j + 5, widthUnit - 10, heightUnit - 10);
                    g.setColor(Color.BLACK);
                    g.drawOval(i + 5, j + 5, widthUnit - 10, heightUnit - 10);
                }
            }
            g.drawLine(widthUnit, HEIGHT - heightUnit, WIDTH - widthUnit, HEIGHT - heightUnit);

            g.setColor(gameDone ? Color.GREEN : players[turn]);
            g.fillOval(hoverX + 5, hoverY + 5, widthUnit - 10, heightUnit - 10);
            g.setColor(Color.BLACK);
            g.drawOval(hoverX + 5, hoverY + 5, widthUnit - 10, heightUnit - 10);

            g.setColor(Color.BLACK);
            if (p1 != null && p2 != null) {
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }

        }

        public static void hover(int x) {
            x -= x%widthUnit;
            if (x < widthUnit) x = widthUnit;
            if (x >= WIDTH - widthUnit) x = WIDTH - 2*widthUnit;
            hoverX = x;
            hoverY = 0;
        }

        public static void drop() {
            if (board[hoverX/widthUnit - 1][0] != Color.WHITE) return;
            new Thread(() -> {
                Color color = players[turn];
                int x = hoverX;
                int i;
                for (i = 0; i < board[x/widthUnit - 1].length && board[x/widthUnit - 1][i] == Color.WHITE; i++) {
                    board[x/widthUnit - 1][i] = color;
                    try { Thread.currentThread().sleep(200); } catch(Exception ignored) {}
                    board[x/widthUnit - 1][i] = Color.WHITE;
                    if (gameDone) return;
                }
                if (gameDone) return;
                board[x/widthUnit - 1][i - 1] = color;
                checkConnect(x/widthUnit - 1, i - 1);
            }).start();
            try { Thread.currentThread().sleep(100); } catch(Exception ignored) {}
            if (gameDone) return;
            turn = (turn + 1) % players.length;
        }

        public static void checkConnect(int x, int y) {
            if (gameDone) return;

            PointPair pair = search(board, x, y);

            if (pair != null) {
                p1 = new Point((pair.p1.x + 1) * widthUnit + widthUnit / 2, (pair.p1.y + 1) * heightUnit + heightUnit / 2);
                p2 = new Point((pair.p2.x + 1) * widthUnit + widthUnit / 2, (pair.p2.y + 1) * heightUnit + heightUnit / 2);
                frame.removeMouseListener(instance);
                gameDone = true;
            }
        }

        public static PointPair search(Color[][] arr, int i, int j) {
            Color color = arr[i][j];
            int left, right, up, down;

            // check horizontally left to right
            left = right = i;
            while (left >= 0 && arr[left][j] == color) left--;
            left++;
            while (right < arr.length && arr[right][j] == color) right++;
            right--;
            if (right - left >= 3) {
                return new PointPair(left, j, right, j);
            }

            // check vertically top to bottom
            down = j;
            while (down < arr[i].length && arr[i][down] == color) down++;
            down--;
            if (down - j >= 3) {
                return new PointPair(i, j, i, down);
            }

            // check diagonal top left to bottom right
            left = right = i;
            up = down = j;
            while (left >= 0 && up >= 0 && arr[left][up] == color) { left--; up--; }
            left++; up++;
            while (right < arr.length && down < arr[right].length && arr[right][down] == color) { right++; down++; }
            right--; down--;
            if (right - left >= 3 && down - up >= 3) {
                return new PointPair(left, up, right, down);
            }

            // check diagonal top right to bottom left
            left = right = i;
            up = down = j;
            while (left >= 0 && down < arr[left].length && arr[left][down] == color) {left--; down++;}
            left++; down--;
            while (right < arr.length && up >= 0 && arr[right][up] == color) {right++; up--;}
            right--; up++;
            if (right - left >= 3 && down - up >= 3) {
                return new PointPair(left, down, right, up);
            }

            return null;
        }

    }
}


