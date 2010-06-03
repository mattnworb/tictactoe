package brown.games.tictac;

import java.util.Collection;
import brown.games.GameMove;
import brown.games.GameScoring;
import brown.games.GameState;
import brown.games.Player;

/**
 * @author Matt Brown msbcode@gmail.com
 * @date Jun 3, 2010
 */
public class TicTacPlayer implements Player {

	private final Tile tile;

	private GameScoring scoring;

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
		// TODO Auto-generated method stub
		return null;
	}

}
