package brown.games;

/**
 * Represents the current state of the game/board/position.
 * 
 * @author Matt Brown msbcode@gmail.com
 * @date Jun 2, 2010
 */
public interface GameState {

	/**
	 * Determines if the game is concluded with neither player winning.
	 * 
	 * @return
	 */
	boolean isDraw();

	/**
	 * Determines if the game has been won.
	 * 
	 * @return
	 */
	boolean isWin();

	GameState copy();
}
