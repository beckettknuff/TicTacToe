import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class TicTacToeViewer extends JFrame {
    // TODO: Complete this class
    // 2D Array of Square
    private Square[][] board;
    private static final int    WINDOW_WIDTH = 800,
                                WINDOW_HEIGHT = 600;
    // Images
    private static final int OFFSET = 50;
    private static final int SQUARE_SIZE = 150; // Size of each square in the grid
    private static final int GRID_ORIGIN_X = 200; // X coordinate for the grid's origin
    private static final int GRID_ORIGIN_Y = 100; // Y coordinate for the grid's origin
    private static final int GRID_SIZE = SQUARE_SIZE * 3; // Total size of the grid
    private Image Ximage;
    private Image Oimage;

    private TicTacToe game;

    public TicTacToeViewer(TicTacToe game) {
        this.game = game;
        this.board = game.getBoard();

        Ximage = new ImageIcon("Resources/X.png").getImage();
        Oimage = new ImageIcon("Resources/O.png").getImage();

        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawGrid(g);
        if (game.getGameOver()) {
            winningCombination(g);
        }
        drawBoxes(g);
        if (game.getGameOver()) {
            announceWinner(g);
        }
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.BLACK);
        for (int i = 0; i <= 3; i++) {
            // Vertical lines
            g.drawLine(GRID_ORIGIN_X + i * SQUARE_SIZE, GRID_ORIGIN_Y,
                    GRID_ORIGIN_X + i * SQUARE_SIZE, GRID_ORIGIN_Y + GRID_SIZE);
            // Horizontal lines
            g.drawLine(GRID_ORIGIN_X, GRID_ORIGIN_Y + i * SQUARE_SIZE,
                    GRID_ORIGIN_X + GRID_SIZE, GRID_ORIGIN_Y + i * SQUARE_SIZE);
        }

        g.setColor(Color.BLACK);
        for (int i = 0; i < 3; i++) {
            // Row labels (0, 1, 2)
            g.drawString(Integer.toString(i), GRID_ORIGIN_X - OFFSET / 2, GRID_ORIGIN_Y + i * SQUARE_SIZE + SQUARE_SIZE / 2);
            // Column labels (0, 1, 2)
            g.drawString(Character.toString((char)('0' + i)), GRID_ORIGIN_X + i * SQUARE_SIZE + SQUARE_SIZE / 2, GRID_ORIGIN_Y - OFFSET / 2);
        }
    }


    private void drawBoxes(Graphics g) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                Square square = board[row][col];
                int x = GRID_ORIGIN_X + col * SQUARE_SIZE;
                int y = GRID_ORIGIN_Y + row * SQUARE_SIZE;
                if (square.getMarker().equals(TicTacToe.X_MARKER)) {
                    g.drawImage(Ximage, x, y, SQUARE_SIZE, SQUARE_SIZE, this);
                } else if (square.getMarker().equals(TicTacToe.O_MARKER)) {
                    g.drawImage(Oimage, x, y, SQUARE_SIZE, SQUARE_SIZE, this);
                }
            }
        }
    }

    private void winningCombination(Graphics g) {
        Square[] winningSquares = game.getWinningSquares();
        if (winningSquares != null) {
            g.setColor(Color.GREEN);
            for (Square square : winningSquares) {
                int x = GRID_ORIGIN_X + square.getCol() * SQUARE_SIZE;
                int y = GRID_ORIGIN_Y + square.getRow() * SQUARE_SIZE;
                g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    private void announceWinner(Graphics g) {
        String winnerText = game.getWinner() + " Wins!";
        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 24));
        int textY = GRID_ORIGIN_Y + GRID_SIZE + OFFSET;
        FontMetrics metrics = g.getFontMetrics();
        int textX = (WINDOW_WIDTH - metrics.stringWidth(winnerText)) / 2;
        g.drawString(winnerText, textX, textY);
    }

    private void drawImage(Graphics g, String imageName, int x, int y) {
        Image image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources" + "O.png"))).getImage();
        g.drawImage(Oimage, x, y, SQUARE_SIZE, SQUARE_SIZE, this);
    }


}
