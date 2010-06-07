package brown.games.tictac;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import brown.games.GameScoring;
import brown.games.GameState;
import brown.games.Player;

/**
 * BoardEvaluation algorithm.
 * <p>
 * Let <code>nc(gs, p)</code> be the number of rows, columns, or diagonals on a
 * tic-tac game state <code>gs</code> in which player <code>p</code> may still
 * get three in a row.
 * <p>
 * When neither player has yet to win the game, the score method returns
 * <code>nc(gs, player) - nc(gs, opponent)</code>.
 * 
 * @author Matt Brown msbcode@gmail.com
 * @date Jun 2, 2010
 */
public class TicTacGameScore implements GameScoring {


	private static final Logger log = LoggerFactory.getLogger(TicTacGameScore.class);

	/**
	 * Return score for this player at this state. Returns:
	 * <p>
	 * <ul>
	 * <li>Integer.MAX_VALUE if player has won the game</li>
	 * <li>Integer.MIN_VALUE if player's opponent has won the game</li>
	 * <li><code>nc(gs, player) - nc(gs, opponent)</code> as defined above if
	 * neither player has won</li>
	 * </ul>
	 * 
	 * @param state
	 * @param player
	 * @return
	 */
	@Override
	public int score(GameState state, Player player) {
		int score = score((TicTacGameState) state, (TicTacPlayer) player);

		if (log.isDebugEnabled())
			log.debug("score: score for player {} state {} is {}", new Object[]{ player, state,
					score });
		return score;
	}

	private int score(TicTacGameState state, TicTacPlayer player) {
		if (state.isWin()) {
			if (state.isWinner(player)) {
				return Integer.MAX_VALUE;
			}
			// otherwise opponent won
			return Integer.MIN_VALUE;

		}

		Set<Tile[]> tuples = Tuplizer.getTuples(state.board);

		int myscore = 0;
		int oppscore = 0;
		for (Tile[] tuple : tuples) {
			// can i score this?
			myscore += canScore(tuple, player.getTile());
			oppscore += canScore(tuple, getOpponent(player.getTile()));
		}

		return myscore - oppscore;
	}

	private int canScore(Tile[] tuple, Tile tile) {
		Tile opp = getOpponent(tile);
		for (Tile t : tuple) {
			if (t == opp) {
				// this tuple has a tile with the opponent in it, player cannot
				// get three in a row here
				return 0;
			}
		}
		return 1;
	}

	private Tile getOpponent(Tile tile) {
		return tile == Tile.X ? Tile.O : Tile.X;
	}

}
