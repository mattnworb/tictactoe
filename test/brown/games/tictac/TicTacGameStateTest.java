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
		state.board[0] = new Tile[]{ Tile.O, null, Tile.X };
		state.board[1] = new Tile[]{ null, Tile.O, Tile.O };
		state.board[2] = new Tile[]{ Tile.X, Tile.X, Tile.X };
		assertTrue(state.isWin());
		assertFalse(state.isDraw());
	}

	@Test
	public void testIsVerticalWin() {
		state.board[0] = new Tile[]{ Tile.O, null, Tile.X };
		state.board[1] = new Tile[]{ null, Tile.O, Tile.X };
		state.board[2] = new Tile[]{ Tile.X, Tile.O, Tile.X };
		assertTrue(state.isWin());
		assertFalse(state.isDraw());
	}

	@Test
	public void testIsDiagonalWin1() {
		state.board[0] = new Tile[]{ Tile.O, null, Tile.X };
		state.board[1] = new Tile[]{ null, Tile.O, Tile.O };
		state.board[2] = new Tile[]{ Tile.X, Tile.X, Tile.O };
		assertTrue(state.isWin());
		assertFalse(state.isDraw());
	}

	@Test
	public void testIsDiagonalWin2() {
		state.board[0] = new Tile[]{ Tile.O, null, Tile.X };
		state.board[1] = new Tile[]{ null, Tile.X, Tile.O };
		state.board[2] = new Tile[]{ Tile.X, Tile.X, Tile.O };
		assertTrue(state.isWin());
		assertFalse(state.isDraw());
	}

	@Test
	public void testIsDraw() {
		state.board[0] = new Tile[]{ Tile.O, null, Tile.X };
		state.board[1] = new Tile[]{ null, Tile.X, Tile.O };
		state.board[2] = new Tile[]{ null, Tile.X, Tile.O };
		assertTrue(state.isDraw());
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
