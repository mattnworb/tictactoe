package brown.games.tictac;

import brown.games.GameState;

public class TicTacGameState implements GameState {

	private final static int DEFAULT_SIZE = 3;

	protected final Tile[][] board;

	private final int size;

	/**
	 * Constructs state with tic-tac-toe board of default size
	 */
	public TicTacGameState() {
		this(DEFAULT_SIZE);
	}

	public TicTacGameState(int size) {
		this.size = size;
		board = new Tile[size][size];
	}

	public TicTacGameState(Tile[][] board) {
		this.size = board.length;
		this.board = board;
	}

	protected void place(Tile tile, int x, int y) {
		board[x][y] = tile;
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

		BoardTest test = new BoardTest() {

			@Override
			public boolean test(Tile[] tuple) {
				boolean xFound = false;
				boolean oFound = false;
				for (int i = 0; i < tuple.length; i++) {
					if (tuple[i] == Tile.X) xFound = true;
					if (tuple[i] == Tile.O) oFound = true;

					if (xFound && oFound) return true;
				}
				return false;
			}
		};

		// every row must be a draw for the board to be a draw
		boolean nonDrawFound = false;
		for (int i = 0; i < size; i++) {
			if (!test.test(board[i])) nonDrawFound = true;
		}

		return !nonDrawFound;
	}

	@Override
	public boolean isWin() {
		// we have a winning board if any tuple has all of the same element
		return testBoard(new BoardTest() {

			@Override
			public boolean test(Tile[] tuple) {
				Tile start = tuple[0];
				if (start == null) return false;

				for (int i = 1; i < tuple.length; i++) {
					if (start != tuple[i]) return false;
				}
				return true;
			}
		});
	}

	/**
	 * Executes the BoardTest against each possible tuple in the board
	 * vertically, horizontally, and diagonally. Returns true as soon as the
	 * BoardTest passes in any direction.
	 * 
	 * @param test
	 * @return
	 */
	private boolean testBoard(BoardTest test) {
		return testBoardVert(test) || testBoardHoriz(test) || testDiagonals(test);
	}

	private boolean testBoardVert(BoardTest tester) {
		for (int i = 0; i < size; i++) {
			if (tester.test(board[i])) return true;
		}
		return false;
	}

	private boolean testBoardHoriz(BoardTest tester) {
		for (int i = 0; i < size; i++) {
			Tile[] row = new Tile[size];
			for (int c = 0; c < size; c++) {
				row[c] = board[c][i];
			}
			if (tester.test(row)) return true;
		}
		return false;
	}

	private boolean testDiagonals(BoardTest tester) {
		Tile[] diag = new Tile[size];
		for (int i = 0; i < size; i++) {
			diag[i] = board[i][i];
		}
		if (tester.test(diag)) return true;

		diag = new Tile[size];
		for (int i = 0; i < size; i++) {
			diag[i] = board[size - i - 1][i];
		}
		if (tester.test(diag)) return true;

		return false;
	}

	protected boolean isWinner(final Tile playerTile) {
		if (!isWin()) return false;

		// return true if any tuple on the board has all tiles equal to
		// playerTile
		return testBoard(new BoardTest() {

			@Override
			public boolean test(Tile[] tuple) {
				for (int i = 0; i < tuple.length; i++) {
					if (tuple[i] != playerTile) return false;
				}
				return true;
			}
		});
	}

	/**
	 * Strategy for testing a board - i.e. tests if tuple is a "win" or a
	 * "draw".
	 * 
	 * @author Matt Brown msbcode@gmail.com
	 * @date Jun 2, 2010
	 */
	private interface BoardTest {

		boolean test(Tile[] tuple);
	}

}
