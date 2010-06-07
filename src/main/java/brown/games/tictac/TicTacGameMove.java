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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + c;
		result = prime * result + r;
		result = prime * result + ((tile == null) ? 0 : tile.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		TicTacGameMove other = (TicTacGameMove) obj;
		if (c != other.c) return false;
		if (r != other.r) return false;
		if (tile == null) {
			if (other.tile != null) return false;
		}
		else if (!tile.equals(other.tile)) return false;
		return true;
	}

}
