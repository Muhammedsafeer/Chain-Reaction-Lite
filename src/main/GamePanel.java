package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public final int cellSize = 50;
    public final int unitSize = 20;
    public final int cols = 6;
    public final int rows = 10;
    public final int boardWidth = cols * cellSize;
    public final int boardHeight = rows * cellSize;

    public final int SCREEN_WIDTH = boardWidth + (cellSize * 2);
    public final int SCREEN_HEIGHT = boardHeight + (cellSize * 2);

    private MouseInputs mouseInputs = new MouseInputs();
    private Reaction reaction = new Reaction(this);

    Color backgroundColor = new Color(51, 51, 51);

    int mouseX;
    int mouseY;

    boolean turnRed;
    boolean turnGreen;

    public int[][] board;
    public int[][] preBoard;

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(backgroundColor);
        this.setDoubleBuffered(true);
        this.addMouseListener(mouseInputs);
        this.setFocusable(true);
        Run();
    }
    public void Run() {
        GameLoop gl = new GameLoop(this);
        gl.start();

        turnRed = true;
        turnGreen = false;

        board = new int[cols][rows];
    }

    public void update() {
        if (mouseInputs.mousePressed) {
            System.out.println("Mouse Pressed");
            mouseX = mouseInputs.mouseX;
            mouseY = mouseInputs.mouseY;

            // Check if mouse is in the board
            if (mouseX > cellSize && mouseX < cellSize + boardWidth && mouseY > cellSize && mouseY < cellSize + boardHeight) {
                System.out.println("Mouse is in the board");
                // Check which cell the mouse is in
                int cellX = (mouseX - cellSize) / cellSize;
                int cellY = (mouseY - cellSize) / cellSize;

                if (turnRed) {
                    if (board[cellX][cellY] == 0 || board[cellX][cellY] == 1 || board[cellX][cellY] == 2 || board[cellX][cellY] == 3) {
                        board[cellX][cellY]++;
                    }
                }
                if (turnGreen) {
                    turnRed = true;
                }
            }
        }
        board = reaction.update(board, cols, rows);


        reset();
    }

    public void reset() {
        mouseInputs.mousePressed = false;
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;

        // Set the color to red or green depending on whose turn it is
        if (turnRed) {
            g2d.setColor(Color.RED);
        } else if (turnGreen) {
            g2d.setColor(Color.GREEN);
        } else {
            g2d.setColor(Color.WHITE);
        }

        // Draw the board
        g2d.drawRect(cellSize, cellSize, boardWidth, boardHeight);

        // Draw the grid
        for (int i = 0; i < cols; i++) {
            g2d.drawLine(cellSize + (i * cellSize), cellSize, cellSize + (i * cellSize), cellSize + boardHeight);
        }
        for (int i = 0; i < rows; i++) {
            g2d.drawLine(cellSize, cellSize + (i * cellSize), cellSize + boardWidth, cellSize + (i * cellSize));
        }

        // Draw the pieces
        int col = 0;
        int row = 0;
        while (col < cols && row < rows) {

            if (board[col][row] == 1) {
                // draw a red piece
                g2d.setColor(Color.RED);
                g2d.fillOval(cellSize + (col * cellSize) + (cellSize / 2) - (unitSize / 2), cellSize + (row * cellSize) + (cellSize / 2) - (unitSize / 2), unitSize, unitSize);
            }
            if (board[col][row] == 2) {
                // draw two red pieces
                g2d.setColor(Color.RED);
                g2d.fillOval(cellSize + (col * cellSize) + (cellSize / 2) - (unitSize / 2) - (unitSize / 2), cellSize + (row * cellSize) + (cellSize / 2) - (unitSize / 2), unitSize, unitSize);
                g2d.fillOval(cellSize + (col * cellSize) + (cellSize / 2) - (unitSize / 2) + (unitSize / 2), cellSize + (row * cellSize) + (cellSize / 2) - (unitSize / 2), unitSize, unitSize);
            }
            if (board[col][row] == 3) {
                // draw three red pieces
                g2d.setColor(Color.RED);
                g2d.fillOval(cellSize + (col * cellSize) + (cellSize / 2) - (unitSize / 2) - (unitSize / 2), cellSize + (row * cellSize) + (cellSize / 2) - (unitSize / 2) - (unitSize / 2), unitSize, unitSize);
                g2d.fillOval(cellSize + (col * cellSize) + (cellSize / 2) - (unitSize / 2) + (unitSize / 2), cellSize + (row * cellSize) + (cellSize / 2) - (unitSize / 2) - (unitSize / 2), unitSize, unitSize);
                g2d.fillOval(cellSize + (col * cellSize) + (cellSize / 2) - (unitSize / 2), cellSize + (row * cellSize) + (cellSize / 2) - (unitSize / 2) + (unitSize / 2), unitSize, unitSize);
            }

            if (board[col][row] == 5) {
                // draw a green piece
                g2d.setColor(Color.GREEN);
                g2d.fillOval(cellSize + (col * cellSize) + (cellSize / 2) - (unitSize / 2), cellSize + (row * cellSize) + (cellSize / 2) - (unitSize / 2), unitSize, unitSize);
            }
            if (board[col][row] == 6) {
                // draw two green pieces
                g2d.setColor(Color.GREEN);
                g2d.fillOval(cellSize + (col * cellSize) + (cellSize / 2) - (unitSize / 2) - (unitSize / 2), cellSize + (row * cellSize) + (cellSize / 2) - (unitSize / 2), unitSize, unitSize);
                g2d.fillOval(cellSize + (col * cellSize) + (cellSize / 2) - (unitSize / 2) + (unitSize / 2), cellSize + (row * cellSize) + (cellSize / 2) - (unitSize / 2), unitSize, unitSize);
            }
            if (board[col][row] == 7) {
                // draw three green pieces
                g2d.setColor(Color.GREEN);
                g2d.fillOval(cellSize + (col * cellSize) + (cellSize / 2) - (unitSize / 2) - (unitSize / 2), cellSize + (row * cellSize) + (cellSize / 2) - (unitSize / 2) - (unitSize / 2), unitSize, unitSize);
                g2d.fillOval(cellSize + (col * cellSize) + (cellSize / 2) - (unitSize / 2) + (unitSize / 2), cellSize + (row * cellSize) + (cellSize / 2) - (unitSize / 2) - (unitSize / 2), unitSize, unitSize);
                g2d.fillOval(cellSize + (col * cellSize) + (cellSize / 2) - (unitSize / 2), cellSize + (row * cellSize) + (cellSize / 2) - (unitSize / 2) + (unitSize / 2), unitSize, unitSize);
            }

            col++;
            if (col == cols) {
                col = 0;
                row++;
            }
        }


        g2d.dispose();
    }
}
