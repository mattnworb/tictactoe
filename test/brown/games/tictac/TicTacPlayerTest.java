package brown.games.tictac;

import static org.junit.Assert.assertEquals;
import java.util.Collection;
import org.junit.Test;
import brown.games.GameMove;
import brown.games.Player;



/**
 * @author Matt Brown msbcode@gmail.com
 * @date Jun 3, 2010
 *
 */
public class TicTacPlayerTest {

	@Test
	public void testValidMovesEmptyBoard() {
		Player player = new TicTacPlayer(Tile.X);
		TicTacGameState state = new TicTacGameState();

		assertEquals(9, player.validMoves(state).size());
	}

	@Test
	public void testValidMoves() {
		Player player = new TicTacPlayer(Tile.X);
		TicTacGameState state = new TicTacGameState();
		state.board[0] = new Tile[]{ Tile.X, Tile.O, Tile.X };
		state.board[1] = new Tile[]{ Tile.X, null, Tile.X };
		state.board[2] = new Tile[]{ Tile.X, Tile.O, Tile.X };

		Collection<GameMove> moves = player.validMoves(state);
		assertEquals(1, moves.size());

		TicTacGameMove move = (TicTacGameMove) moves.iterator().next();
		assertEquals(1, move.x);
		assertEquals(1, move.y);

	}
}
