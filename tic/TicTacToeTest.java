import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TicTacToeTest {

    @Test
    public void newGameShouldBeInProgress() {
        TicTacToe game = new TicTacToe();
        assertEquals(GameStatus.IN_PROGRESS, game.evaluate());
    }

    @Test
    public void xShouldWinOnRow() {
        TicTacToe game = new TicTacToe();
        game.set(0, 0, 'X');
        game.set(0, 1, 'X');
        game.set(0, 2, 'X');
        assertEquals(GameStatus.X_WINS, game.evaluate());
    }

    @Test
    public void oShouldWinOnColumn() {
        TicTacToe game = new TicTacToe();
        game.set(0, 1, 'O');
        game.set(1, 1, 'O');
        game.set(2, 1, 'O');
        assertEquals(GameStatus.O_WINS, game.evaluate());
    }

    @Test
    public void shouldBeDrawWhenBoardIsFullWithoutWinner() {
        TicTacToe game = new TicTacToe();

        // X O X
        // X O O
        // O X X
        game.set(0, 0, 'X');
        game.set(0, 1, 'O');
        game.set(0, 2, 'X');

        game.set(1, 0, 'X');
        game.set(1, 1, 'O');
        game.set(1, 2, 'O');

        game.set(2, 0, 'O');
        game.set(2, 1, 'X');
        game.set(2, 2, 'X');

        assertEquals(GameStatus.DRAW, game.evaluate());
    }
}
