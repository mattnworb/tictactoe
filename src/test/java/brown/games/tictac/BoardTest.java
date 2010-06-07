package brown.games.tictac;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;
import org.junit.Test;
import brown.games.tictac.Board.Tuple;

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

	@Test
	public void testTuples() {
		Board board = new Board(3);
		board.place(Tile.X, 0);
		board.place(Tile.X, 1);
		board.place(Tile.X, 2);
		board.place(Tile.O, 3);
		board.place(Tile.O, 4);
		board.place(Tile.O, 5);

		Collection<Tuple> tuples = board.toTuples();
		assertEquals(8, tuples.size());
		assertTrue(tuples.contains(new Tuple(new Tile[]{ Tile.X, Tile.X, Tile.X })));
		assertTrue(tuples.contains(new Tuple(new Tile[]{ Tile.O, Tile.O, Tile.O })));
		assertTrue(tuples.contains(new Tuple(new Tile[3])));
		// this board contains this tupe 5 times: 3 columns, 2 diagonals
		assertTrue(tuples.contains(new Tuple(new Tile[]{ Tile.X, Tile.O, null })));
	}

	@Test
	public void testIterator() {
		Tuple tuple = new Tuple(new Tile[]{ Tile.X, Tile.O, null });

		Iterator<Tile> it = tuple.iterator();
		assertTrue(it.hasNext());
		assertEquals(Tile.X, it.next());

		assertTrue(it.hasNext());
		assertEquals(Tile.O, it.next());

		assertTrue(it.hasNext());
		assertNull(it.next());

		assertFalse(it.hasNext());
	}
}
