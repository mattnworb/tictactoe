package brown.games.tictac;

import java.io.PrintStream;

/**
 * @author Matt Brown msbcode@gmail.com
 * @date Jun 6, 2010
 *
 */
public class Board {

	private int size;

	private Tile[] data;

	public Board(int size) {
		this.size = size;
		this.data = new Tile[size * size];
	}

	/**
	 * Places a tile at row <i>r</i> and column <i>c</i>. For example, on the
	 * following board:
	 * <p>
	 * <code>
	 * X | X | O<br/>
	 * - | O | -<br/>
	 * - | - | -
	 * </code>
	 * <p>
	 * X has tiles at positions (r=0,c=0) and (r=0,c=1), and O has tiles at
	 * positions (r=0,c=2), and (r=1,c=1).
	 * 
	 * @param tile
	 * @param r
	 * @param c
	 */
	public void place(Tile tile, int r, int c) {
		data[toIndex(r, c)] = tile;
	}

	private int toIndex(int r, int c) {
		return r * size + c;
	}

	/**
	 * Tests if position at row <i>r</i> and column <i>c</i> is empty.
	 * 
	 * @param r
	 * @param c
	 * @return
	 */
	public boolean isEmpty(int r, int c) {
		return data[toIndex(r, c)] == null;
	}

	/**
	 * Returns the value of the tile at row <i>r</i> and column <i>c</i> is
	 * empty.
	 * 
	 * @param r
	 * @param c
	 * @return
	 */
	public Tile getValue(int r, int c) {
		return data[toIndex(r, c)];
	}
	public void prettyPrint(PrintStream out) {

		for (int i = 0; i < data.length; i++) {
			out.print(data[i] != null ? data[i] : " ");
			if (i % size == size - 1) {
				out.println();
			}
			else {
				out.print(" | ");
			}
		}
	}
}
