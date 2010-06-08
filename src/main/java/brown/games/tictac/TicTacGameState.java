package brown.games.tictac;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import brown.games.GameState;
import brown.games.tictac.Board.Tuple;

public class TicTacGameState implements GameState {

	private static final Logger log = LoggerFactory.getLogger(TicTacGameState.class);

	private final static int DEFAULT_SIZE = 3;

	protected final Board board;

	private final int size;

	/**
	 * Constructs state with tic-tac-toe board of default size
	 */
	public TicTacGameState() {
		this(DEFAULT_SIZE);
	}

	public TicTacGameState(int size) {
		this.size = size;
		board = new Board(size);
	}

	public TicTacGameState(Board board) {
		this.size = board.getSize();
		this.board = board;
	}

	public int getSize() {
		return this.size;
	}

	/**
	 * Places tile at row r and column c.
	 * 
	 * @param tile
	 * @param r
	 * @param c
	 */
	protected void place(Tile tile, int r, int c) {
		board.place(tile, r, c);
	}

	@Override
	public GameState copy() {
		return new TicTacGameState(board);
	}

	@Override
	public boolean isDraw() {

		if (isWin()) return false;

		// A row, column or diagonal is defined as a draw if it contains at
		// least one of each tile

		BoardTest testForDraw = new BoardTest() {

			@Override
			public boolean test(Tuple tuple) {

				if (log.isDebugEnabled()) log.debug("test: testing tuple {}", tuple);
				final boolean containsX = tuple.contains(Tile.X);
				final boolean containsY = tuple.contains(Tile.O);
				return containsX && containsY;
			}
		};

		boolean isDraw = true;
		for (Tuple tuple : board.toTuples()) {
			if (!testForDraw.test(tuple)) {
				isDraw = false;
				break;
			}
		}
		return isDraw;
	}

	@Override
	public boolean isWin() {

		// we have a winning board if any tuple has all of the same element
		return testEveryTuple(new BoardTest() {

			@Override
			public boolean test(Tuple tuple) {
				Tile start = tuple.getValue(0);
				if (start == null) return false;

				for (Tile t : tuple) {
					if (t != start) return false;
				}
				return true;
			}
		});
	}

	/**
	 * Executes the BoardTest against each possible tuple in the board
	 * vertically, horizontally, and diagonally.
	 * <p>
	 * Returns true as soon as the first Tuple passed the BoardTest.
	 * 
	 * @param test
	 * @return
	 */
	private boolean testEveryTuple(BoardTest boardTest) {
		for (Tuple tuple : board.toTuples()) {
			if (boardTest.test(tuple)) return true;
		}
		return false;
	}

	public boolean isWinner(final TicTacPlayer player) {
		if (!isWin()) return false;

		// return true if any tuple on the board has all tiles equal to
		// playerTile
		return testEveryTuple(new BoardTest() {

			@Override
			public boolean test(Tuple tuple) {
				for (Tile tile : tuple) {
					if (tile != player.getTile()) return false;
				}
				return true;
			}
		});
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString() + "[board=");

		for (int i = 0; i < board.getNumTiles(); i++) {
			sb.append(i + "=" + board.getValue(i));
			if (i < size * size - 1) sb.append(",");
		}

		return sb.append("]").toString();
	}

	/**
	 * Strategy for testing a board - i.e. tests if tuple is a "win" or a
	 * "draw".
	 * 
	 * @author Matt Brown msbcode@gmail.com
	 * @date Jun 2, 2010
	 */
	private interface BoardTest {

		boolean test(Tuple tuple);
	}

}
