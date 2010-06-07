package brown.games.tictac;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import brown.games.GameMove;
import brown.games.GameScoring;
import brown.games.GameState;
import brown.games.Player;

/**
 * @author Matt Brown msbcode@gmail.com
 * @date Jun 3, 2010
 */
public class TicTacPlayer implements Player {

	private static final Logger log = LoggerFactory.getLogger(TicTacPlayer.class);

	private final Tile tile;

	private GameScoring scoring = new TicTacGameScore();

	public TicTacPlayer(Tile tile) {
		this.tile = tile;
	}

	public Tile getTile() {
		return this.tile;
	}

	@Override
	public void setScoring(GameScoring scoring) {
		this.scoring = scoring;
	}

	@Override
	public int eval(GameState state) {
		return scoring.score(state, this);
	}

	@Override
	public Collection<GameMove> validMoves(GameState state) {
		// can make a move in any empty square
		List<GameMove> moves = new ArrayList<GameMove>();

		TicTacGameState ttstate = (TicTacGameState) state;
		final int size = ttstate.getSize();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (ttstate.board[i][j] == null) {
					moves.add(new TicTacGameMove(tile, i, j));
				}
			}
		}

		if (log.isDebugEnabled())
			log.debug("validMoves: returning {} moves: {}", moves.size(), moves);

		return moves;
	}

	@Override
	public String toString() {
		return super.toString() + "[tile=" + tile + "]";
	}
}
