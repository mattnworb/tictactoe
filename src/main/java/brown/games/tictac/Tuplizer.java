package brown.games.tictac;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Matt Brown msbcode@gmail.com
 * @date Jun 3, 2010
 */
public class Tuplizer {

	static Set<Tile[]> getTuples(Tile[][] board) {

		final Set<Tile[]> set = new HashSet<Tile[]>();
		final int size = board.length;

		// verts
		for (int i = 0; i < size; i++) {
			set.add(board[i]);
		}

		// horizs
		for (int i = 0; i < size; i++) {
			Tile[] row = new Tile[size];
			for (int c = 0; c < size; c++) {
				row[c] = board[c][i];
			}
			set.add(row);
		}

		// diagonals
		Tile[] diag = new Tile[size];
		for (int i = 0; i < size; i++) {
			diag[i] = board[i][i];
		}
		set.add(diag);

		diag = new Tile[size];
		for (int i = 0; i < size; i++) {
			diag[i] = board[size - i - 1][i];
		}
		set.add(diag);

		return set;
	}
}
