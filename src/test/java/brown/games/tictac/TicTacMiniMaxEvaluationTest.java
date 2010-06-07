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
import brown.games.tictac.TicTacGameMove;
import brown.games.tictac.TicTacGameState;
import brown.games.tictac.TicTacPlayer;
import brown.games.tictac.Tile;



/**
 * @author Matt Brown msbcode@gmail.com
 * @date Jun 6, 2010
 *
 */
public class TicTacMiniMaxEvaluationTest {

	private static final Logger log = LoggerFactory.getLogger(TicTacMiniMaxEvaluationTest.class);

	private MiniMaxEvaluation eval;

	private Player player;

	private Tile[][] boards;

	private GameState state;

	@Before
	public void setUp() {
		eval = new MiniMaxEvaluation(2);
		boards = new Tile[3][];
		state = new TicTacGameState(boards);
		player = new TicTacPlayer(Tile.O);
	}

	@Test
	public void testBlocksWinningMoveAtPly2() {

		boards[0] = new Tile[]{ Tile.X, Tile.X, null };
		boards[1] = new Tile[]{ null, Tile.O, null };
		boards[2] = new Tile[]{ null, null, null };

		TicTacGameMove move = (TicTacGameMove) eval.bestMove(state, player,
			new TicTacPlayer(Tile.X));

		if (log.isDebugEnabled()) log.debug("testBlocksWinningMove: move is {}", move);

		TicTacGameMove expected = new TicTacGameMove(Tile.O, 2, 0);
		assertEquals(expected, move);
	}

	@Test
	public void testBlocksWinningMoveAtPly3() {

		eval = new MiniMaxEvaluation(3);

		boards[0] = new Tile[]{ Tile.X, Tile.X, null };
		boards[1] = new Tile[]{ null, Tile.O, null };
		boards[2] = new Tile[]{ null, null, null };

		TicTacGameMove move = (TicTacGameMove) eval.bestMove(state, player,
			new TicTacPlayer(Tile.X));

		if (log.isDebugEnabled()) log.debug("testBlocksWinningMove: move is {}", move);

		TicTacGameMove expected = new TicTacGameMove(Tile.O, 2, 0);
		assertEquals(expected, move);
	}

	/**
	 * a move should still be returned even when all paths lead to a loss
	 */
	@Test
	public void testMoveIsReturnedWhenAllLeadToLoss() {
		boards[0] = new Tile[]{ Tile.X, null, Tile.O };
		boards[1] = new Tile[]{ null, Tile.O, null };
		boards[2] = new Tile[]{ Tile.X, null, Tile.X };

		TicTacGameMove move = (TicTacGameMove) eval.bestMove(state, player,
			new TicTacPlayer(Tile.X));

		assertNotNull(move);
	}
}
