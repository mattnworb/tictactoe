package brown.games.tictac;

import brown.games.GameMove;
import brown.games.GameState;

/**
 * @author Matt Brown msbcode@gmail.com
 * @date Jun 2, 2010
 */
public class TicTacGameMove implements GameMove {

	private final Tile tile;

	protected final int x;

	protected final int y;

	public TicTacGameMove(Tile tile, int x, int y) {
		this.tile = tile;
		this.x = x;
		this.y = y;
	}

	@Override
	public void execute(GameState state) {
		((TicTacGameState) state).place(tile, x, y);
	}

	@Override
	public boolean isValid(GameState state) {

		// valid if tile is not already taken
		return ((TicTacGameState) state).board[x][y] == null;
	}

	@Override
	public void undo(GameState state) {
		((TicTacGameState) state).place(null, x, y);
	}

}
