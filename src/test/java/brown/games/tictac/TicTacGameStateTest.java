package brown.games.tictac;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Matt Brown msbcode@gmail.com
 * @date Jun 2, 2010
 */
public class TicTacGameStateTest {

	private TicTacGameState state;

	@Before
	public void setUp() {
		state = new TicTacGameState();
	}

	@Test
	public void testIsHorizontalWin() {
		// state.board[0] = new Tile[]{ Tile.O, null, Tile.X };
		// state.board[1] = new Tile[]{ null, Tile.O, Tile.O };
		// state.board[2] = new Tile[]{ Tile.X, Tile.X, Tile.X };
		state.board.place(Tile.O, 0);
		state.board.place(Tile.X, 2);
		state.board.place(Tile.O, 4);
		state.board.place(Tile.O, 5);
		state.board.place(Tile.X, 6);
		state.board.place(Tile.X, 7);
		state.board.place(Tile.X, 8);

		assertTrue(state.isWin());
		assertFalse(state.isDraw());
	}

	@Test
	public void testIsVerticalWin() {
		// state.board[0] = new Tile[]{ Tile.O, null, Tile.X };
		// state.board[1] = new Tile[]{ null, Tile.O, Tile.X };
		// state.board[2] = new Tile[]{ Tile.X, Tile.O, Tile.X };
		state.board.place(Tile.O, 0);
		state.board.place(Tile.X, 2);
		state.board.place(Tile.O, 4);
		state.board.place(Tile.X, 5);
		state.board.place(Tile.X, 6);
		state.board.place(Tile.O, 7);
		state.board.place(Tile.X, 8);

		assertTrue(state.isWin());
		assertFalse(state.isDraw());
	}

	@Test
	public void testIsDiagonalWin1() {
		// state.board[0] = new Tile[]{ Tile.O, null, Tile.X };
		// state.board[1] = new Tile[]{ null, Tile.O, Tile.O };
		// state.board[2] = new Tile[]{ Tile.X, Tile.X, Tile.O };
		state.board.place(Tile.O, 0);
		state.board.place(Tile.X, 2);
		state.board.place(Tile.O, 4);
		state.board.place(Tile.O, 5);
		state.board.place(Tile.X, 6);
		state.board.place(Tile.X, 7);
		state.board.place(Tile.O, 8);

		assertTrue(state.isWin());
		assertFalse(state.isDraw());
	}

	@Test
	public void testIsDiagonalWin2() {
		// state.board[0] = new Tile[]{ Tile.O, null, Tile.X };
		// state.board[1] = new Tile[]{ null, Tile.X, Tile.O };
		// state.board[2] = new Tile[]{ Tile.X, Tile.X, Tile.O };
		state.board.place(Tile.O, 0);
		state.board.place(Tile.X, 2);
		state.board.place(Tile.X, 4);
		state.board.place(Tile.O, 5);
		state.board.place(Tile.X, 6);
		state.board.place(Tile.X, 7);
		state.board.place(Tile.O, 8);

		assertTrue(state.isWin());
		assertFalse(state.isDraw());
	}

	@Test
	public void testIsDraw() {
		// X O O
		// O X X
		// O x O
		state.board.place(Tile.X, 0);
		state.board.place(Tile.O, 1);
		state.board.place(Tile.O, 2);
		state.board.place(Tile.O, 3);
		state.board.place(Tile.X, 4);
		state.board.place(Tile.X, 5);
		state.board.place(Tile.O, 6);
		state.board.place(Tile.X, 7);
		state.board.place(Tile.O, 8);

		assertTrue(state.isDraw());
		assertFalse(state.isWin());
	}

	/**
	 * Test situations where the game is neither a draw or a win
	 */
	@Test
	public void testNotADraw() {
		state.board.place(Tile.X, 0);
		assertFalse(state.isDraw());
		assertFalse(state.isWin());

		state.board.place(Tile.O, 1);
		assertFalse(state.isDraw());
		assertFalse(state.isWin());
	}

	@Test
	public void testEmptyBoardIsNotWin() {
		assertFalse(state.isWin());
	}

	@Test
	public void testEmptyBoardIsNotDraw() {
		assertFalse(state.isDraw());
	}
}
