package brown.games.tictac;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author Matt Brown msbcode@gmail.com
 * @date Jun 6, 2010
 */
public class Board {

	private int size;

	private Tile[] data;

	public Board(int size) {
		this.size = size;
		this.data = new Tile[size * size];
	}

	/**
	 * Gets the size of the board. The actual size of the board is the square of
	 * the size, i.e. a board with size=3 has 9 positions to place tiles.
	 * 
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Returns the number of positions in which tiles can be placed in this
	 * board. A board with size=3 has 9 positions to place tiles.
	 * <p>
	 * Note: this method is unrelated to determining which tiles are empty.
	 * 
	 * @return
	 */
	public int getNumTiles() {
		return data.length;
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
		place(tile, toIndex(r, c));
	}

	/**
	 * Places a tile at the i-th position, counting from zero horizontally and
	 * then vertically.
	 */
	public void place(Tile tile, int i) {
		data[i] = tile;
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
		return getValue(toIndex(r, c));
	}

	/**
	 * Returns the value of the tile at the i-th position, counting from zero
	 * horizontally and then vertically.
	 * 
	 * @param r
	 * @param c
	 * @return
	 */
	public Tile getValue(int i) {
		return data[i];
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

	/**
	 * Returns the data in this board as a set of Tuples.
	 * <p>
	 * Note: the same Tuple may be contained more than one time in the board.
	 * 
	 * @see Tuple
	 * @return The tuples containd in this board
	 */
	public Collection<Tuple> toTuples() {
		final List<Tuple> list = new ArrayList<Tuple>();

		// Add vertical columns and horizontal rows.
		// In a board of size=3 the columns are at
		// [0, 3, 6], [1, 4, 7], and [2, 5, 8].
		// And the rows are at
		// [0, 1, 2], [3, 4, 5], and [6, 7, 8]
		for (int offset = 0; offset < size; offset++) {
			Tile[] column = new Tile[size];
			Tile[] row = new Tile[size];
			for (int i = 0; i < size; i++) {
				column[i] = data[offset + size * i];
				row[i] = data[offset * size + i];
			}
			list.add(new Tuple(column));
			list.add(new Tuple(row));
		}

		// diagonals
		// in a board of size 3, there are at (0, 4, 8) and (2, 4, 6).
		// in a board of size 4, there are at (0, 5, 10, 15) and (3, 6, 9, 12).
		Tile[] diag1 = new Tile[size];
		Tile[] diag2 = new Tile[size];
		for (int i = 0; i < size; i++) {
			diag1[i] = data[(size + 1) * i];
			diag2[i] = data[(size - 1) * (i + 1)];
		}
		list.add(new Tuple(diag1));
		list.add(new Tuple(diag2));

		return list;
	}

	/**
	 * A tuple represents a row, column, or diagonal in the Board. For example,
	 * in a board of size 3, there are 8 tuples: 3 rows, 3 columns, and two
	 * diagonals.
	 * 
	 * @author Matt Brown msbcode@gmail.com
	 * @date Jun 6, 2010
	 */
	public static class Tuple implements Iterable<Tile> {

		Tile[] data;

		public Tuple(Tile[] data) {
			this.data = data;
		}

		public int getLength() {
			return data.length;
		}

		public Tile getValue(int i) {
			return data[i];
		}

		public boolean contains(Tile tile) {
			for (Tile t : data) {
				if (t == tile) return true;
			}
			return false;
		}

		public Iterator<Tile> iterator() {
			return new Iterator<Tile>() {

				int pos = 0;

				@Override
				public boolean hasNext() {
					return pos < data.length;
				}

				@Override
				public Tile next() {
					return data[pos++];
				}

				@Override
				public void remove() {
					throw new UnsupportedOperationException("Cannot remove values from Tuple");
				}

			};
		}

		@Override
		public String toString() {
			return super.toString() + Arrays.toString(data);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(data);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null) return false;
			if (getClass() != obj.getClass()) return false;
			Tuple other = (Tuple) obj;
			if (!Arrays.equals(data, other.data)) return false;
			return true;
		}

	}
}
