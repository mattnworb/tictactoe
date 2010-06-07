package brown.games.tictac;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import brown.games.GameMove;



/**
 * @author Matt Brown msbcode@gmail.com
 * @date Jun 3, 2010
 *
 */
public class TicTacGameMoveTest {

	private TicTacGameState state;

	@Before
	public void setUp() {
		state = new TicTacGameState();
	}

	@Test
	public void testIsValidOnEmptyBoard() {
		//every move should be valid
		for (int r = 0; r < state.getSize(); r++ ) {
			for (int c = 0; c < state.getSize(); c++ ) {
				GameMove move1 = new TicTacGameMove(Tile.O, r, c);
				assertTrue(move1.isValid(state));
				GameMove move2 = new TicTacGameMove(Tile.X, r, c);
				assertTrue(move2.isValid(state));
			}
		}
	}

	@Test
	public void testIsNotValid() {
		state.board.place(Tile.O, 0);
		assertFalse(new TicTacGameMove(Tile.O, 0, 0).isValid(state));
		assertFalse(new TicTacGameMove(Tile.X, 0, 0).isValid(state));
	}

	@Test
	public void testExecute() {
		GameMove move = new TicTacGameMove(Tile.O, 0, 0);
		move.execute(state);
		assertEquals(Tile.O, state.board.getValue(0));
		assertFalse(move.isValid(state));
	}

	@Test
	public void testUndo() {
		GameMove move = new TicTacGameMove(Tile.O, 0, 0);
		move.execute(state);
		assertEquals(Tile.O, state.board.getValue(0));
		assertFalse(move.isValid(state));

		move.undo(state);
		assertNull(state.board.getValue(0));
		assertTrue(move.isValid(state));
	}
}
