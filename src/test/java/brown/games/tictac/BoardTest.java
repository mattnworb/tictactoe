package brown.games.tictac;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;

/**
 * @author Matt Brown msbcode@gmail.com
 * @date Jun 6, 2010
 */
public class BoardTest {

	@Test
	public void testPlace() {
		Board board = new Board(3);

		board.place(Tile.X, 0, 2);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(baos);
		board.prettyPrint(out);

		assertFalse(board.isEmpty(0, 2));
		assertTrue(board.isEmpty(0, 0));

		String rep = baos.toString();
		String endl = System.getProperty("line.separator");
		assertEquals("  |   | X" + endl + "  |   |  " + endl + "  |   |  " + endl, rep);

	}
}
