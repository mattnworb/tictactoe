package brown.games.tictac;

/**
 * @author Matt Brown msbcode@gmail.com
 * @date Jun 2, 2010
 */
public enum Tile {
	X,
	O;

	@Override
	public String toString() {
		return name();
	}
}
