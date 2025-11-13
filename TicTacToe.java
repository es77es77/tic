public class TicTacToe {

    private static final int SIZE = 3;
    private final char[][] board;

    public TicTacToe() {
        board = new char[SIZE][SIZE];
        // 將全部格子設為空白
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                board[r][c] = ' ';
            }
        }
    }

    /**
     * 在指定位置下子
     * @param row    0~2
     * @param col    0~2
     * @param player 'X' 或 'O'
     */
    public void set(int row, int col, char player) {
        if (player != 'X' && player != 'O') {
            throw new IllegalArgumentException("player must be X or O");
        }
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new IllegalArgumentException("row/col out of range");
        }
        if (board[row][col] != ' ') {
            throw new IllegalArgumentException("cell already occupied");
        }
        board[row][col] = player;
    }

    /**
     * 讀取某格的內容（測試用）
     */
    public char getCell(int row, int col) {
        return board[row][col];
    }

    /**
     * 判斷目前遊戲狀態：
     * X_WINS, O_WINS, DRAW, IN_PROGRESS
     */
    public GameStatus evaluate() {
        // 檢查三條橫排
        for (int r = 0; r < SIZE; r++) {
            char winner = checkLine(board[r][0], board[r][1], board[r][2]);
            if (winner == 'X') return GameStatus.X_WINS;
            if (winner == 'O') return GameStatus.O_WINS;
        }

        // 檢查三條直行
        for (int c = 0; c < SIZE; c++) {
            char winner = checkLine(board[0][c], board[1][c], board[2][c]);
            if (winner == 'X') return GameStatus.X_WINS;
            if (winner == 'O') return GameStatus.O_WINS;
        }

        // 檢查兩條對角線
        char winnerDiag1 = checkLine(board[0][0], board[1][1], board[2][2]);
        if (winnerDiag1 == 'X') return GameStatus.X_WINS;
        if (winnerDiag1 == 'O') return GameStatus.O_WINS;

        char winnerDiag2 = checkLine(board[0][2], board[1][1], board[2][0]);
        if (winnerDiag2 == 'X') return GameStatus.X_WINS;
        if (winnerDiag2 == 'O') return GameStatus.O_WINS;

        // 沒人贏 → 判斷是平手還是仍有空格
        boolean hasEmpty = false;
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (board[r][c] == ' ') {
                    hasEmpty = true;
                    break;
                }
            }
            if (hasEmpty) break;
        }

        if (hasEmpty) {
            return GameStatus.IN_PROGRESS;
        } else {
            return GameStatus.DRAW;
        }
    }

    // 三格一線是否形成勝利
    private char checkLine(char a, char b, char c) {
        if (a != ' ' && a == b && b == c) {
            return a;
        }
        return ' ';
    }

    // 你如果想自己玩玩看，可以加個 main 做簡單測試
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.set(0, 0, 'X');
        game.set(1, 1, 'X');
        game.set(2, 2, 'X');
        System.out.println(game.evaluate()); // 會印出 X_WINS
    }
}

// 遊戲狀態列舉
enum GameStatus {
    IN_PROGRESS,
    X_WINS,
    O_WINS,
    DRAW
}
