package brown.games.tictac;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import brown.games.Player;
import brown.games.algos.MiniMaxEvaluation;

/**
 * @author Matt Brown msbcode@gmail.com
 * @date Jun 6, 2010
 */
public class TicTacMiniMaxEvaluationTest {

	private static final Logger log = LoggerFactory.getLogger(TicTacMiniMaxEvaluationTest.class);

	private MiniMaxEvaluation eval;

	private Player player;

	private TicTacGameState state;

	@Before
	public void setUp() {
		player = new TicTacPlayer(Tile.O);
		state = new TicTacGameState();
		eval = new MiniMaxEvaluation(2);
	}

	@Test
	public void testBlocksWinningMove() {

		state.board.place(Tile.X, 0, 0);
		state.board.place(Tile.X, 0, 1);
		state.board.place(Tile.O, 1, 1);

		TicTacGameMove expected = new TicTacGameMove(Tile.O, 0, 2);

		testReturnsExpectedMove(state, expected);
	}

	/**
	 * @param state
	 * @param expected
	 */
	private void testReturnsExpectedMove(TicTacGameState state, TicTacGameMove expected) {
		for (int ply = 2; ply < 6; ply++) {
			eval = new MiniMaxEvaluation(ply);

			TicTacGameMove move = (TicTacGameMove) eval.bestMove(state, player, new TicTacPlayer(
				Tile.X));

			if (log.isDebugEnabled()) log.debug("testBlocksWinningMove: move is {}", move);

			assertEquals("Minimax did not make expected move at ply=" + ply, expected, move);
		}
	}

	/**
	 * a move should still be returned even when all paths lead to a loss
	 */
	@Test
	public void testMoveIsReturnedWhenAllLeadToLoss() {
		// boards[0] = new Tile[]{ Tile.X, null, Tile.O };
		// boards[1] = new Tile[]{ null, Tile.O, null };
		// boards[2] = new Tile[]{ Tile.X, null, Tile.X };

		state.board.place(Tile.X, 0, 0);
		state.board.place(Tile.O, 0, 2);
		state.board.place(Tile.O, 1, 1);
		state.board.place(Tile.X, 2, 0);
		state.board.place(Tile.X, 2, 2);

		for (int ply = 2; ply < 6; ply++) {
			eval = new MiniMaxEvaluation(ply);
			assertNotNull(eval.bestMove(state, player, new TicTacPlayer(Tile.X)));
		}
	}

	@Test
	public void testBlocksLaterWinningMove() {
		// X | X | O
		// - | O | -
		// X | - | -
		state.board.place(Tile.X, 0, 0);
		state.board.place(Tile.X, 0, 1);
		state.board.place(Tile.X, 2, 0);

		state.board.place(Tile.O, 0, 2);
		state.board.place(Tile.O, 1, 1);

		TicTacGameMove expected = new TicTacGameMove(Tile.O, 1, 0);
		testReturnsExpectedMove(state, expected);
	}
}
