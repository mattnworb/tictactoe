package brown.games.tictac;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import brown.games.GameScoring;

/**
 * @author Matt Brown msbcode@gmail.com
 * @date Jun 3, 2010
 */
public class TicTacGameScoreTest {

	private GameScoring scoring;

	private TicTacGameState state;

	private TicTacPlayer playerX;

	private TicTacPlayer playerO;

	@Before
	public void setUp() {
		scoring = new TicTacGameScore();
		state = new TicTacGameState();

		playerX = new TicTacPlayer(Tile.X);
		playerO = new TicTacPlayer(Tile.O);
	}

	@Test
	public void testSomeoneWins() {

		state.board[0] = new Tile[]{ Tile.O, null, Tile.X };
		state.board[1] = new Tile[]{ null, Tile.O, Tile.X };
		state.board[2] = new Tile[]{ Tile.X, Tile.O, Tile.X };

		assertEquals(Integer.MAX_VALUE, scoring.score(state, playerX));
		assertEquals(Integer.MIN_VALUE, scoring.score(state, playerO));
	}

	@Test
	public void testEmptyBoard() {
		// score should be zero for both players as they have the same
		// possibilities
		assertEquals(0, scoring.score(state, playerO));
		assertEquals(0, scoring.score(state, playerX));
	}

	@Test
	public void testUnevenPossibilities() {

		state.board[0] = new Tile[]{ Tile.O, null, null };
		state.board[1] = new Tile[]{ null, null, null };
		state.board[2] = new Tile[]{ null, null, null };

		// O has eight tuples, X has five tuples

		assertEquals(-3, scoring.score(state, playerX));
		assertEquals(3, scoring.score(state, playerO));
	}
}
