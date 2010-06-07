package brown.games.tictac;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import brown.games.GameState;
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

	private Board board;

	private GameState state;

	@Before
	public void setUp() {
		eval = new MiniMaxEvaluation(2);
		board = new Board(3);
		state = new TicTacGameState(board);
		player = new TicTacPlayer(Tile.O);
	}

	@Test
	public void testBlocksWinningMoveAtPly2() {
		testBlocksWinningMove(2);
	}

	@Test
	public void testBlocksWinningMoveAtPly3() {
		testBlocksWinningMove(3);
	}

	/**
	 * @param ply
	 */
	private void testBlocksWinningMove(int ply) {
		eval = new MiniMaxEvaluation(ply);

		board.place(Tile.X, 0, 0);
		board.place(Tile.X, 0, 1);
		board.place(Tile.O, 1, 1);

		TicTacGameMove move = (TicTacGameMove) eval.bestMove(state, player,
			new TicTacPlayer(Tile.X));

		if (log.isDebugEnabled()) log.debug("testBlocksWinningMove: move is {}", move);

		TicTacGameMove expected = new TicTacGameMove(Tile.O, 0, 2);
		assertEquals(expected, move);
	}

	/**
	 * a move should still be returned even when all paths lead to a loss
	 */
	@Test
	public void testMoveIsReturnedWhenAllLeadToLoss() {
		// boards[0] = new Tile[]{ Tile.X, null, Tile.O };
		// boards[1] = new Tile[]{ null, Tile.O, null };
		// boards[2] = new Tile[]{ Tile.X, null, Tile.X };
		board.place(Tile.X, 0, 0);
		board.place(Tile.O, 0, 2);
		board.place(Tile.O, 1, 1);
		board.place(Tile.X, 2, 0);
		board.place(Tile.X, 2, 2);

		TicTacGameMove move = (TicTacGameMove) eval.bestMove(state, player,
			new TicTacPlayer(Tile.X));

		assertNotNull(move);
	}

	@Test
	public void testBlocksLaterWinningMove() {
		// X | X | O
		// - | O | -
		// X | - | -
		board.place(Tile.X, 0, 0);
		board.place(Tile.X, 0, 1);
		board.place(Tile.X, 2, 0);

		board.place(Tile.O, 0, 2);
		board.place(Tile.O, 1, 1);

		TicTacGameMove expected = new TicTacGameMove(Tile.O, 1, 0);

		TicTacGameMove move = (TicTacGameMove) eval.bestMove(state, player,
			new TicTacPlayer(Tile.X));

		assertEquals(expected, move);
	}
}
