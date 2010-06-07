package brown.games.tictac;

import brown.games.GameMove;
import brown.games.GameState;

/**
 * @author Matt Brown msbcode@gmail.com
 * @date Jun 2, 2010
 */
public class TicTacGameMove implements GameMove {

	private final Tile tile;

	private final int r;

	private final int c;

	public TicTacGameMove(Tile tile, int r, int c) {
		this.tile = tile;
		this.r = r;
		this.c = c;
	}

	public Tile getTile() {
		return tile;
	}

	public int getRow() {
		return r;
	}

	public int getColumn() {
		return c;
	}

	@Override
	public void execute(GameState state) {
		((TicTacGameState) state).place(tile, r, c);
	}

	@Override
	public boolean isValid(GameState state) {

		// valid if tile is not already taken
		return ((TicTacGameState) state).board.isEmpty(r, c);
	}

	@Override
	public void undo(GameState state) {
		((TicTacGameState) state).place(null, r, c);
	}

	@Override
	public String toString() {
		return super.toString() + "[tile=" + tile + ", r=" + r + ", c=" + c + "]";
	}
}
