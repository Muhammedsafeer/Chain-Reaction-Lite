package main;

public class Reaction {

    GamePanel gp;

    public Reaction(GamePanel gp) {
        this.gp = gp;
    }

    public int[][] update(int[][] board, int cols, int rows) {
        int col = 0;
        int row = 0;
        while (col < cols && row < rows) {

            corners(board, col, row);
            edges(board, col, row);
            middle(board, col, row);

            col++;
            if (col == cols) {
                col = 0;
                row++;
            }
        }
        return board;
    }

    private int[][] corners(int[][] board, int col, int row) {
        if (col == 0 && row == 0) {
            if (board[col][row] == 2) {
                board[col][row + 1]++;
                board[col + 1][row]++;
                board[col][row] = 0;
            }
            if (board[col][row] == 5) {
                board[col][row + 1]++;
                board[col + 1][row]++;
                board[col][row] = 0;
            }
        }
        if (col == 5 && row == 0) {
            if (board[col][row] == 2) {
                board[col][row + 1]++;
                board[col - 1][row]++;
                board[col][row] = 0;
            }
            if (board[col][row] == 5) {
                board[col][row + 1]++;
                board[col - 1][row]++;
                board[col][row] = 0;
            }
        }
        if (col == 0 && row == 9) {
            if (board[col][row] == 2) {
                board[col][row - 1]++;
                board[col + 1][row]++;
                board[col][row] = 0;
            }
            if (board[col][row] == 5) {
                board[col][row - 1]++;
                board[col + 1][row]++;
                board[col][row] = 0;
            }
        }
        if (col == 5 && row == 9) {
            if (board[col][row] == 2) {
                board[col][row - 1]++;
                board[col - 1][row]++;
                board[col][row] = 0;
            }
            if (board[col][row] == 5) {
                board[col][row - 1]++;
                board[col - 1][row]++;
                board[col][row] = 0;
            }
        }

        return board;
    }
    private int[][] edges(int[][] board, int col, int row) {

        if (col == 0) {
            if (board[col][row] == 3) {
                board[col][row + 1]++;
                board[col + 1][row]++;
                board[col][row - 1]++;
                board[col][row] = 0;
            }
            if (board[col][row] == 6) {
                board[col][row + 1]++;
                board[col + 1][row]++;
                board[col][row - 1]++;
                board[col][row] = 0;
            }
        }
        if (col == 5) {
            if (board[col][row] == 3) {
                board[col][row + 1]++;
                board[col - 1][row]++;
                board[col][row - 1]++;
                board[col][row] = 0;
            }
            if (board[col][row] == 6) {
                board[col][row + 1]++;
                board[col - 1][row]++;
                board[col][row - 1]++;
                board[col][row] = 0;
            }
        }
        if (row == 0) {
            if (board[col][row] == 3) {
                board[col][row + 1]++;
                board[col + 1][row]++;
                board[col - 1][row]++;
                board[col][row] = 0;
            }
            if (board[col][row] == 6) {
                board[col][row + 1]++;
                board[col + 1][row]++;
                board[col - 1][row]++;
                board[col][row] = 0;
            }
        }
        if (row == 9) {
            if (board[col][row] == 3) {
                board[col][row - 1]++;
                board[col + 1][row]++;
                board[col - 1][row]++;
                board[col][row] = 0;
            }
            if (board[col][row] == 6) {
                board[col][row - 1]++;
                board[col + 1][row]++;
                board[col - 1][row]++;
                board[col][row] = 0;
            }
        }

        return board;
    }
    private int[][] middle(int[][] board,int col, int row) {
        if (board[col][row] == 4) {
            board[col][row + 1]++;
            board[col + 1][row]++;
            board[col][row - 1]++;
            board[col - 1][row]++;
            board[col][row] = 0;
        }
        if (board[col][row] == 7) {
            board[col][row + 1]++;
            board[col + 1][row]++;
            board[col][row - 1]++;
            board[col - 1][row]++;
            board[col][row] = 0;
        }

        return board;
    }
}
